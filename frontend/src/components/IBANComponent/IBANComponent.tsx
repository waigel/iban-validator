import {useTranslate} from '@tolgee/react';
import React, {useState} from 'react';
import {useValidationRequest} from '../../hooks/useValidationRequest';
import {classNames} from '../../utils/classNames';
import {AnimationStatus} from '../AnimationStatus/AnimationStatus';
import {BooleanToText} from '../BooleanToText/BooleanToText';
import {Button} from '../Button/Button';
import {DeveloperDialog} from '../DeveloperDialog/DeveloperDialog';
import {TextInput} from '../Input/TextInput';
import {TolgeeParams} from '../../types/TolgeeParams';
import {BLZRecordInformation} from '../BLZRecordInformation/BLZRecordInformation';
export interface IBANComponentProps {
  clasName?: string;
}

export const IBANComponent = ({clasName}: IBANComponentProps) => {
  const {execute, pending, erroCode, information, clearOnValueChange} =
    useValidationRequest();
  const {t} = useTranslate();

  const [input, setInput] = useState('');
  const valueChange = (changedValue: string) => {
    setInput(changedValue);
    clearOnValueChange();
  };

  const submit = () => execute(input);

  const isError = !pending && erroCode && !information;
  const isSuccess = !pending && !erroCode && information;
  const params: TolgeeParams = {};
  erroCode?.params?.forEach((val, index) => {
    params[index.toString()] = val;
  });

  return (
    <>
      <DeveloperDialog
        onTestIBANSelected={testIBAN => {
          setInput(testIBAN);
          execute(testIBAN);
        }}
      />
      <div
        className={classNames(
          'flex items-center rounded-xl border flex-wrap md:flex-nowrap',
          clasName
        )}
      >
        <TextInput
          placeholder={t('LABEL_IBAN_PLACEHOLDER')}
          onValueChange={valueChange}
          defaultValue={input}
          onEnterPress={submit}
          className="rounded-none border-none focus:border-transparent uppercase w-full"
        />
        <Button
          className="rounded-xl md:rounded-r-xl px-12 w-full md:w-auto"
          onClick={submit}
        >
          {t('LABEL_VALIDATION_BUTTON')}
        </Button>
      </div>
      <div
        className={classNames(
          'flex gap-4 rounded-xl px-8 py-4 bg-card',
          erroCode || information ? 'visible' : 'invisible',
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
    </>
  );
};
