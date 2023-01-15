import React, {useEffect, useState} from 'react';
import {classNames} from '../../utils/classNames';

export interface TextInputProps
  extends React.HtmlHTMLAttributes<HTMLInputElement> {
  handleValueChange?: (value: string) => void;
  handleEnterPress?: () => void;
  defaultValue?: string;
}

export const TextInput = (props: TextInputProps) => {
  const {handleValueChange, defaultValue, handleEnterPress} = props;
  const [value, setValue] = useState<string>(defaultValue ?? '');

  useEffect(() => {
    defaultValue && setValue(defaultValue);
  }, [defaultValue]);

  const domProps = {...props};
  delete domProps.defaultValue;
  delete domProps.handleEnterPress;
  delete domProps.handleValueChange;

  return (
    <input
      {...domProps}
      className={classNames(
        'bg-transparent border rounded-xl px-4 py-2 text-center tracking-widest',
        props.className
      )}
      onKeyDown={event => {
        if (event.key === 'Enter') {
          handleEnterPress && handleEnterPress();
        }
      }}
      onChange={event => {
        const changedValue = event.target.value;
        setValue(changedValue);
        handleValueChange && handleValueChange(changedValue);
      }}
      value={value}
    />
  );
};
