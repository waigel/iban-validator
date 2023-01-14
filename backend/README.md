# IBAN Validator Backend (API)

Das Backend wurde mit Spring Boot und Java umgesetzt. <br/>
Die Validierung der IBAN basierend auf der Prüfziffer, Länge und Struktur wurde selbst implementiert.

Die Informationen über die nationale IBAN Länge und die Zugehörigkeit zum SWIFT Abkommen stammen von der Quelle: [swift iban registry](https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&ved=2ahUKEwinqfSN_MH8AhXIV6QEHZu2AXQQFnoECBoQAQ&url=https%3A%2F%2Fwww.swift.com%2Fresource%2Fiban-registry-pdf&usg=AOvVaw0ry5oYpRt6qoPcGs481YGl)
Diese Informationen wurden bereits von [Barend Garvelink](https://github.com/barend/java-iban/blob/main/src/main/resources/nl/garvelink/iban/IBAN.yml) im YML Format aufbereitet. Daher habe ich mir die Mühe gespart und diese auch bei mir eingebunden.

## BLZ lookup

Der BLZ Lookup ist dynamisch und modular aufgebaut, so dass jederzeit eine weitere Datenquelle für ein bestimmtes Land hinzugefügt werden kann. Die Informationen für Deutschland stammen von der [Bundesbank](https://www.bundesbank.de/de/aufgaben/unbarer-zahlungsverkehr/serviceangebot/bankleitzahlen/download-bankleitzahlen-602592)

### Fallback (external API)

Es wurde die externe API [api.ibanapi.com](https://ibanapi.com) als Fallback-Datenquelle angebunden. Wird also eine IBAN zum Validieren eingegeben zu dem keine Datenquelle registriert ist, wird die Anfrage an die externe API weitergeleitet. Da Anfragen an diese API kostenpflichtig sind, werden die Ergebnisse in-memory gecached, um ein erneutes Abrufen zu vermeiden.

Um das Guthaben (Anzahl der noch verfügbaren Anfragen) zu überwachen, gibt es den `MonitorIBANApiCreditBalance.java` Job. Dieser wird jede Minute gescheduled und kontaktiert den `/balance` Endpoint um das noch verfügbare Guthaben abzurufen. Dieser Wert wird anschlißend `prometheus` für das monitoring bereitgestellt.

Dadurch lässt sich in Grafana das Gutachten (mit max 1 Minute delay) überwachen:

<img src="https://user-images.githubusercontent.com/25115243/212501631-92278088-3c5a-4c59-9e90-d51266ba1ce4.png" width="400">

## Datenbank

Um Option1 der Aufgabenstellung zu erfüllen, wird die IBAN (wenn gültig) in eine Datenbank Tabelle `ibanhistory` gespeichert und kann als zusätliche Quelle für das Monitoring oder für sonstige statsitische Zwecke genutzt werden.

## Dokumentation

Es wird automatisch eine OpenAPI Dokumentation generiert, die über Swagger UI bereitgestellt wird. <br/>
Erreichbar unter: 
- Lokal: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- Remote: [iban-validator.waigel.com/api](https://iban-validator.waigel.com/api/swagger-ui/index.html)

## Tests

Es wurden Tests für Controller LookupService und Validierung implementiert.
