export interface IBANResponse {
  bankName: string;
  countryCode: string;
  countryName: string;
  isInSwiftRegistry: boolean;
  isSepaCountry: boolean;
  bankCode?: string;
  blzRecord: BLZRecord;
}

export interface BLZRecord {
  blz?: string;
  bankName?: string;
  shortBankName?: string;
  zipCode?: string;
  city?: string;
  bic?: string;
}
