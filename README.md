<p align="center">
<img src="https://user-images.githubusercontent.com/25115243/212448089-afb26657-7713-4def-9a27-137becaf7185.png" />
<h1 align="center">IBAN Validierung - Bewerbung für Lexoffice</h1>
</p>

# Aufgabe

Bitte bereite eine kurze Präsentation vor, die wir als fachliche Diskussionsgrundlage nutzen können. Erstelle und betreibe eine Single-Page-App mit Frontend/Backend, welche die Validierung einer IBAN Eingabe prüft. Diese sollte…
- freie Benutzereingaben erlauben inkl. Trennzeichen zwischen den Ziffern.
- im Backend jedoch keine Trennzeichen enthalten.
- im Backend prüfen ob die IBAN valide ist
- für drei bekannte Banken, nach Eingabe der IBAN, den Banknamen anzeigen.
- *Option 1*: Speicherung der IBAN in einer Datenbank  
- *Option 2*: Finde einen anderen Weg z.B. über die Anbindung einer externen API zur Validierung oder Namensauflösung

**Unser Stack ist**:
- Java-Backend mit Rest-API (Spring Boot basiert)
- SPA-Frontend (React basierte WebApp)
- SPA-Frontend mit REST-Backend-Kommunikation
- Cloud-Betrieb bei beliebigen Anbieter und Infrastruktur

# Umsetzung

## Struktur 
Das [Frontend](/frontend#readme), [Backend](/backend#readme), [Deployment für Kubernetes](/k8s#readme) und [GitHub Actions (CI)](/.github/workflows/ci.yml) befinden sich in diesem Monorepo.

Zusätlich gibt es im [scripts](/scripts#readme) Ordner noch Scripte zum lokalen aufsetzen von `Prometheus` und `Grafana`.

## Deployment

Die API und das Frontend werden in Docker Images gebaut und in einer private docker registry (azurecr.io) gespeichert. Über`Kustomize` werden alle benötigten Konfigurationsdateien gebaut und durch `kubectl` ins Cluster deployed. 

Dabei besteht das Deployment aus folgendn Ressourcen:

1. Deployments 
- iban-validator-grafana
- iban-validator-prometheus
- iban-validator-api
- iban-validator-frontend<br/>
> **Note**<br/>
> Es wird ein production build generiert, dieser wird zusammen mit einem NGINX Webserver im Image gespeichert. 
2. Service
- iban-validator-grafana
- iban-validator-prometheus
- iban-validator-api
- iban-validator-frontend
3. Ingress
- iban-validator
> **Note**<br/>
> `/api` -> iban-validator-api <br/>
> `/grafana` -> iban-validator-grafana <br/>
> `/` -> iban-validator-frontend

## Monitoring
Gerade durch die Anbindung der externe API [api.ibanapi.de](https://api.ibanapi.de) muss unbedingt der Verbrauch und das noch verfügbare Guthaben überwacht werden. Aus diesem Grund wird `prometheus` mit `Grafana` verwendet um den Verbrauch und den Status, in nahezu echtzeit, einzusehen. 

Zusätlich werden die gespeicherten IBANs aus der Datenbank in einer Geomap grafisch dargestellt. 

### CI

GitHub Actions automatisiert das komplette bauen, prüfen, validieren und anschlißende ausrollen im Cluster. 
![image](https://user-images.githubusercontent.com/25115243/212552370-568e60e9-78ba-4977-bde7-d1d394235c06.png)
