import {useTranslate} from '@tolgee/react';
import React from 'react';
import {ErrorResponse} from '../../types/ErrorResponse';
import {IBANResponse} from '../../types/IBANResponse';
import {TolgeeParams} from '../../types/TolgeeParams';
import {classNames} from '../../utils/classNames';
import {AnimationStatus} from '../AnimationStatus/AnimationStatus';
import {BLZRecordInformation} from '../BLZRecordInformation/BLZRecordInformation';
import {BooleanToText} from '../BooleanToText/BooleanToText';

export interface IBANStatusCardProps {
  pending: boolean;
  erroCode: ErrorResponse | null;
  information: IBANResponse | null;
  clasName?: string;
}

export const IBANStatusCard = ({
  pending,
  erroCode,
  information,
  clasName,
}: IBANStatusCardProps) => {
  const {t} = useTranslate();

  const isError = !pending && erroCode && !information;
  const isSuccess = !pending && !erroCode && information;
  const params: TolgeeParams = {};
  erroCode?.params?.forEach((val, index) => {
    params[index.toString()] = val;
  });

  if (erroCode || information)
    return (
      <div
        className={classNames(
          'flex gap-4 rounded-xl px-8 py-4 bg-card',
          clasName
        )}
      >
        <div style={{width: 45, height: 45}}>
          {isError && <AnimationStatus status="failed" />}
          {isSuccess && <AnimationStatus status="success" />}
        </div>
        <div className="my-auto">
          {erroCode && <div>{t(erroCode.code.toString(), params)}</div>}
          {information && (
            <div className="flex gap-0 md:gap-8 flex-col md:flex-row">
              <div>
                <p className="text-success">{t('MESSAGE_VALID_NOTICE')}</p>
                <p className="text-gray-300 flex gap-2">
                  <b>{t('IBAN_VALID_TITLE_COUNTRY')}</b>
                  {information.countryName} ({information.countryCode})
                </p>
                <p className="text-gray-300 flex gap-2">
                  <b>{t('IBAN_VALID_TITLE_IN_SWIFT_REGISTRY')}</b>
                  <BooleanToText value={information.isInSwiftRegistry} />
                </p>
                <p className="text-gray-300 flex gap-2">
                  <b>{t('IBAN_VALID_TITLE_IS_SEPA_COUNTRY')}</b>
                  <BooleanToText value={information.isSepaCountry} />
                </p>
                <p className="text-gray-300 flex gap-2">
                  <b>{t('IBAN_VALID_TITLE_BANK_CODE')}</b>
                  {information.bankCode ?? t('NOT_AVAILABLE')}
                </p>
              </div>
              <BLZRecordInformation value={information.blzRecord} />
            </div>
          )}
        </div>
      </div>
    );
  return null;
};
