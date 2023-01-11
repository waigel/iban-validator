# Validation if IBAN

## Structure

- 2 chars with country code (e.g. DE) - ISO 3166-1
- 2 chars with check digits - ISO 7064
- 30 chars with bank account number - ISO 13616

### Check digits
 
Calculation based on modulo 97 of the IBAN as a number. 
The remainder must be 1.

1. IBAN  DE68 2105 0170 0012 3456 78
2. Conversion: 2105 0170 0012 3456 78DE 68
3. Modulo:  210501700012345678131468 mod 97 = 1
