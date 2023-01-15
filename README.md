<p align="center">
<img src="https://user-images.githubusercontent.com/25115243/212448089-afb26657-7713-4def-9a27-137becaf7185.png" />
<h1 align="center">IBAN Validierung für Lexoffice</h1>
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

**Struktur**: 
Das [Frontend](/frontend#readme), [Backend](/backend#readme), [Deployment für Kubernetes](/k8s#readme) und [GitHub Actions (CI)](/.github/workflows/ci.yml) befinden sich in diesem Monorepo.

Zusätlich gibt es im [scripts](/scripts#readme) Ordner noch Scripte zum lokalen aufsetzen von `Prometheus` und `Grafana`.






