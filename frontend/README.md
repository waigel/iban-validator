
## IBAN Validator Frontend

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


### Tests

Um das Frontend leichter zu testen, kann mit <kbd>STRG</kbd> + <kbd>i</kbd> eine Liste mit Test IBANs geöffnet werden. Diese lassen sich mit einem Klick auf die IBAN direkt in die Eingabe einfügen und direkt validieren. 

Zusätlich gibt es für alle Komponenten snapshot Tests. Diese können mit 
```
$ npm run test
```
ausgeführt werden. 
