import React, {useState} from 'react';
import {classNames} from '../../utils/classNames';
import {Button} from '../Button/Button';
import {TextInput} from '../Input/TextInput';

export interface IBANComponentProps {
  clasName?: string;
}

export const IBANComponent = ({clasName}: IBANComponentProps) => {
  const [input, setInput] = useState('');
  const valueChange = (changedValue: string) => {
    setInput(changedValue);
  };

  const submit = () => {
    alert(input);
  };

  return (
    <div
      className={classNames(
        'flex items-center rounded-xl border flex-wrap md:flex-nowrap',
        clasName
      )}
    >
      <TextInput
        placeholder={'IBAN'}
        onValueChange={valueChange}
        onEnterPress={submit}
        className="rounded-none border-none focus:border-transparent uppercase w-full"
      />
      <Button
        className="rounded-xl md:rounded-r-xl px-12 w-full md:w-auto"
        onClick={submit}
      >
        Validieren
      </Button>
    </div>
  );
};
