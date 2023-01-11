import React, {useEffect, useState} from 'react';
import {classNames} from '../../utils/classNames';

export interface TextInputProps
  extends React.HtmlHTMLAttributes<HTMLInputElement> {
  onValueChange?: (value: string) => void;
  onEnterPress?: () => void;
  defaultValue?: string;
}

export const TextInput = (props: TextInputProps) => {
  const {onValueChange, defaultValue, onEnterPress} = props;
  const [value, setValue] = useState(defaultValue);

  useEffect(() => {
    setValue(defaultValue);
  }, [defaultValue]);

  return (
    <input
      {...props}
      className={classNames(
        'bg-transparent border rounded-xl px-4 py-2 text-center tracking-widest',
        props.className
      )}
      onKeyDown={event => {
        if (event.key === 'Enter') {
          onEnterPress && onEnterPress();
        }
      }}
      onChange={event => {
        const changedValue = event.target.value;
        setValue(changedValue);
        onValueChange && onValueChange(changedValue);
      }}
      value={value}
    />
  );
};
