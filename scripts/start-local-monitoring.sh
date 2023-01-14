#bin/bash
GRAFANA_PASSWORD="grafanaloginpw"
MYSQL_DATA_SOURCE_UID="q_R_ywhVk"

# Check if port 9090 or 3000 is already in use
if lsof -Pi :9090 -sTCP:LISTEN -t >/dev/null ; then
    echo "Port 9090 is already in use. Please stop the process that is using it and try again."
    exit 1
fi
if lsof -Pi :3000 -sTCP:LISTEN -t >/dev/null ; then
    echo "Port 3000 is already in use. Please stop the process that is using it and try again."
    exit 1
fi

echo "Starting local monitoring stack"

docker rm -f ibanv-prometheus ibanv-grafana

echo "[1/3] Starting Prometheus"
docker run -d --name ibanv-prometheus --net=host -v $(pwd)/scripts/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus

echo "[2/3] Starting Grafana"
docker run -d --name ibanv-grafana --net=host grafana/grafana

echo "[3/3] Configuring Grafana"
# Wait for Grafana to start
sleep 2
# 1. Create datasource
if echo $(curl -s -X POST -H "Content-Type: application/json" -d '{"name":"Prometheus","type":"prometheus","url":"http://localhost:9090","access":"proxy","isDefault":true}' http://admin:admin@localhost:3000/api/datasources) | grep -q "Datasource added"; 
    then echo "    [✔️] Datasource added"; 
fi

# 2. Change password to $GRAFANA_PASSWORD
if echo $(curl -s -X PUT -H "Content-Type: application/json" -d '{"oldPassword":"admin","newPassword":"'$GRAFANA_PASSWORD'","confirmNew":"'$GRAFANA_PASSWORD'"}' http://admin:admin@localhost:3000/api/user/password) | grep -q "User password changed"; 
    then echo "    [✔️] Password changed"; 
fi

# 3. Import dashboard
GRAFANA_DASHBOARD=$(cat scripts/grafana.json)
if [ -z "$GRAFANA_DASHBOARD" ]; then
    echo "    [❌] Dashboard import is not valid json"
    exit 1
fi

GRAFANA_DASHBOARD=$(echo $GRAFANA_DASHBOARD | sed 's/__MYSQL__/'$MYSQL_DATA_SOURCE_UID'/g')
importResponse=$(curl -s -X POST -H 'Content-Type: application/json' -d "{\"dashboard\": $GRAFANA_DASHBOARD,\"overwrite\":true}" http://admin:$GRAFANA_PASSWORD@localhost:3000/api/dashboards/import)
imported=$(echo $importResponse | jq '.imported')

if [ "$imported" = "true" ]; then
    echo "    [✔️] Dashboard imported"
else
    echo "    [❌] Dashboard import failed"
    echo $importResponse
    exit 1
fi

importedUrl=$(echo $importResponse | jq '.importedUrl' | sed 's/"//g')
echo "  > Grafana is running on http://localhost:3000$importedUrl"
echo "  > Login with username 'admin' and password '$GRAFANA_PASSWORD'"
