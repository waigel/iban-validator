import {useTranslate} from '@tolgee/react';
import React, {useEffect, useState} from 'react';
import {ColorRing} from 'react-loader-spinner';
import {useValidationRequest} from '../../hooks/useValidationRequest';
import {classNames} from '../../utils/classNames';
import {Button} from '../Button/Button';
import {DeveloperDialog} from '../DeveloperDialog/DeveloperDialog';
import {TextInput} from '../Input/TextInput';
import {IBANStatusCard} from './IBANStatusCard';
export interface IBANComponentProps {
  clasName?: string;
}

export const IBANComponent = ({clasName}: IBANComponentProps) => {
  const {execute, erroCode, information, pending, clearHandleValueChange} =
    useValidationRequest();
  const {t} = useTranslate();

  const [visiblePending, setVisiblePending] = useState(false);
  useEffect(() => {
    if (pending) {
      const timeout = setTimeout(() => {
        setVisiblePending(true);
      }, 100);
      return () => clearTimeout(timeout);
    } else {
      setVisiblePending(false);
    }
  }, [pending]);

  const [input, setInput] = useState('');
  const valueChange = (changedValue: string) => {
    setInput(changedValue);
    clearHandleValueChange();
  };

  const submit = () => execute(input);

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
          handleValueChange={valueChange}
          defaultValue={input}
          handleEnterPress={submit}
          className="rounded-none border-none focus:border-transparent uppercase w-full"
        />
        <Button
          className="rounded-xl md:rounded-r-xl px-12 w-full md:w-auto"
          onClick={submit}
        >
          {t('LABEL_VALIDATION_BUTTON')}
        </Button>
      </div>
      {visiblePending && (
        <div className="self-center py-8">
          <ColorRing
            visible={true}
            height="80"
            width="80"
            wrapperStyle={{}}
            wrapperClass="blocks-wrapper"
            colors={['#d93ea5', '#E15089', '#f37653', '#F69A2C', '#fbbc05']}
          />
        </div>
      )}
      {!visiblePending && (
        <IBANStatusCard
          erroCode={erroCode}
          information={information}
          pending={pending}
          clasName={clasName}
        />
      )}
    </>
  );
};
