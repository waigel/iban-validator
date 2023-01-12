import {useTolgee, useTranslate} from '@tolgee/react';
import React from 'react';

export const LocalizationSelector = () => {
  const {t} = useTranslate();
  const tolgee = useTolgee(['language']);

  const localizations = [
    {label: t('de-DE'), value: 'de-DE'},
    {label: t('en-US'), value: 'en-US'},
  ];

  return (
    <>
      <select
        onChange={e => tolgee.changeLanguage(e.target.value)}
        value={tolgee.getLanguage()}
        className="bg-transparent ring-0"
      >
        {localizations.map(localization => (
          <option
            className="bg-black ring-0"
            key={localization.value}
            value={localization.value}
          >
            {localization.label}
          </option>
        ))}
      </select>
    </>
  );
};
