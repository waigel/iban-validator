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

<hr/>

## Frontend

**Tech Stack**:
- ReactJs
- Typescript
- Tailwind (css)
- Tolgee (i18n)
- lottie (animationen)
- dev tools (eslint, prettier, jest, ..)

### Start

Es sollte die Datei `.env.development.local` im Verzeichnis `frontend/` erstellt werden.
```yml
REACT_APP_API_ENDPOINT=http://localhost:8080
REACT_APP_TOLGEE_API_URL=https://app.tolgee.io
REACT_APP_TOLGEE_API_KEY=tgpak_.............
```

Danach kann das Frontend mit folgendem Befehl gestartet werden:

```sh
$ npm run start
```
Erreichbar unter [localhost:3000](https://localhost:3000).


### Test

Um das Frontend leichter zu testen, kann mit <kbd>STRG</kbd> + <kbd>i</kbd> eine Liste mit Test IBANs geöffnet werden. Diese lassen sich mit einem Klick auf die IBAN direkt in die Eingabe einfügen und direkt validieren. 

<hr/>

## Backend

**Tech Stack**:






