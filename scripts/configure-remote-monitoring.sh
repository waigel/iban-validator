#bin/bash
GRAFANA_PASSWORD="grafanaloginpw"
GRAFANA_REMOTE_HOST="iban-validator.grafana.waigel.com"
PROMETHEUS_CLUSTER_HOST="iban-validator-prometheus.lexoffice.svc.cluster.local"

# 1. Create datasource
dataSourceResponse=$(curl -s -X POST -H "Content-Type: application/json" -d '{"name":"Prometheus","type":"prometheus","url":"http://'$PROMETHEUS_CLUSTER_HOST':9090","access":"proxy","isDefault":true,"overwrite": true}' https://admin:$GRAFANA_PASSWORD@$GRAFANA_REMOTE_HOST/api/datasources)
if echo $dataSourceResponse | grep -q "Datasource added";  
    then echo "    [✔️] Datasource added"; 
else
    echo "    [❌] Datasource could not be added"
    echo $dataSourceResponse
fi


# 2. Import dashboard
GRAFANA_DASHBOARD=$(cat scripts/grafana.json)
if [ -z "$GRAFANA_DASHBOARD" ]; then
    echo "    [❌] Dashboard import is not valid json"
    exit 1
fi

importResponse=$(curl -s -X POST -H 'Content-Type: application/json' -d "{\"dashboard\": $GRAFANA_DASHBOARD,\"overwrite\":true}" https://admin:$GRAFANA_PASSWORD@$GRAFANA_REMOTE_HOST/api/dashboards/import)
imported=$(echo $importResponse | jq '.imported')

if [ "$imported" = "true" ]; then
    echo "    [✔️] Dashboard imported"
else
    echo "    [❌] Dashboard import failed"
    echo $importResponse
    exit 1
fi

importedUrl=$(echo $importResponse | jq '.importedUrl' | sed 's/"//g')
echo "  > Grafana is running on https://$GRAFANA_REMOTE_HOST$importedUrl"
echo "  > Login with username 'admin' and password '$GRAFANA_PASSWORD'"
