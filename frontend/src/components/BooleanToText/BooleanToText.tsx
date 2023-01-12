import {useTranslate} from '@tolgee/react';
import React from 'react';

export interface BooleanToTextProps {
  value: boolean;
}
export const BooleanToText = ({value}: BooleanToTextProps) => {
  const {t} = useTranslate();
  return (
    <span className={value ? 'text-success' : 'text-red-500'}>
      {value ? t('YES') : t('NO')}
    </span>
  );
};
