#!/usr/bin/env bash
set -euo pipefail

GATEWAY_URL="${GATEWAY_URL:-http://localhost:8080}"

extract_id() {
  sed -n 's/.*"id":\([0-9][0-9]*\).*/\1/p'
}

post_json() {
  local path="$1"
  local payload="$2"
  curl -fsS -H "Content-Type: application/json" -d "$payload" "$GATEWAY_URL$path"
}

echo "Esperando gateway en $GATEWAY_URL ..."
for attempt in $(seq 1 60); do
  if curl -fsS "$GATEWAY_URL/api/notifications" >/dev/null 2>&1; then
    break
  fi

  if [ "$attempt" -eq 60 ]; then
    echo "Gateway no respondio a tiempo."
    exit 1
  fi

  sleep 2
done

echo "Creando clientes ..."
client_1_response="$(post_json /api/clients '{"name":"Ana Torres","email":"ana.torres@example.com"}')"
client_2_response="$(post_json /api/clients '{"name":"Luis Perez","email":"luis.perez@example.com"}')"
client_1_id="$(printf '%s' "$client_1_response" | extract_id)"
client_2_id="$(printf '%s' "$client_2_response" | extract_id)"

echo "Creando polizas asociadas ..."
policy_1_response="$(post_json /api/insurance "{\"policyNumber\":\"POL-AUTO-001\",\"policyType\":\"auto\",\"validityDate\":\"2027-12-31\",\"clientId\":$client_1_id}")"
policy_2_response="$(post_json /api/insurance "{\"policyNumber\":\"POL-HOME-002\",\"policyType\":\"hogar\",\"validityDate\":\"2027-06-30\",\"clientId\":$client_2_id}")"
policy_1_id="$(printf '%s' "$policy_1_response" | extract_id)"
policy_2_id="$(printf '%s' "$policy_2_response" | extract_id)"

echo "Creando claims para disparar eventos Kafka ..."
post_json /api/claims "{\"policyId\":$policy_1_id,\"description\":\"Choque leve en parqueadero\"}" >/dev/null
post_json /api/claims "{\"policyId\":$policy_2_id,\"description\":\"Filtracion de agua en cocina\"}" >/dev/null

echo
echo "Datos cargados:"
echo "- Cliente $client_1_id con poliza $policy_1_id y claim asociado"
echo "- Cliente $client_2_id con poliza $policy_2_id y claim asociado"
echo
echo "Consultas de prueba:"
echo "curl $GATEWAY_URL/api/clients"
echo "curl $GATEWAY_URL/api/insurance/client/$client_1_id"
echo "curl $GATEWAY_URL/api/claims/policy/$policy_1_id"
echo
echo "Para ver notificaciones:"
echo "docker compose logs -f notifications"
