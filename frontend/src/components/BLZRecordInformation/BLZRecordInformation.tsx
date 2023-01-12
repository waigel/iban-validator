import {useTranslate} from '@tolgee/react';
import React from 'react';
import {BLZRecord} from '../../types/IBANResponse';

export interface BLZRecordInformationProps {
  value?: BLZRecord;
}

export const BLZRecordInformation = ({value}: BLZRecordInformationProps) => {
  const {t} = useTranslate();
  if (!value) {
    return null;
  }

  const className = 'text-gray-300 flex gap-2';
  return (
    <div className="flex flex-col md:pt-6">
      {(value.bankName || value.shortBankName) && (
        <p className={className}>
          <b>{t('BLZ_INFORMATION_LABEL_BANK_NAME')}</b>
          {value.shortBankName ?? value.bankName}
        </p>
      )}

      {value.bic && (
        <p className={className}>
          <b>{t('BLZ_INFORMATION_LABEL_BIC')}</b>
          {value.bic}
        </p>
      )}

      {value.zipCode && value.city && (
        <p className={className}>
          <b>{t('BLZ_INFORMATION_LABEL_BANK_ADDRESS')}</b>
          {value.zipCode} {value.city}
        </p>
      )}
    </div>
  );
};
