# Protection (htpasswd) for Prometheus

To ensure, that no one can access the Prometheus UI / API a basic auth protection is enabled. The credentials are stored in a secret and mounted into the NGINX INGRESS controller. The NGINX INGRESS controller is configured to use the secret for basic auth protection.

## Create secret

```
$ htpasswd -c auth remote-access
```

Then the password for the user `remote-access` is asked. The password is stored in the file `auth`.

# Current state

The password is `2mzXx!0cexo^A*TKO2BBm%InER12daZ09%q4x3Xy` and the user is `remote-access`.
