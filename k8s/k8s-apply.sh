#!/bin/sh

set -e

export APP_NAME="iban-validator"
export NAMESPACE="lexoffice"

export IMAGE="waigel.azurecr.io/lexoffice/iban-validator"
export DOMAIN="iban-validator.waigel.com"
export VERSION=$(git rev-parse --short HEAD)

kubectl kustomize k8s/template | envsubst > k8s/prod.yml

# apply only if not on github actions runner
if [ ! -n "$GITHUB_ACTIONS" ]; then
    kubectl apply -f k8s/prod.yml
fi