import {useTranslate} from '@tolgee/react';
import React from 'react';
import {LocalizationSelector} from '../LocalizationSelector/LocalizationSelector';

export const Footer = () => {
  const {t} = useTranslate();
  return (
    <footer className="fixed bottom-0 w-full text-sm text-gray-500">
      <div className="max-w-screen-xl py-4 mx-auto">
        <div className="flex flex-col md:flex-row justify-center gap-1 md:gap-4 text-center">
          <p>{t('COPYRIGHT_NOTICE')}</p>
          <a
            href={`${
              process.env.REACT_APP_API_ENDPOINT ?? 'http://localhost:8080'
            }`}
            target="_blank"
          >
            {t('FOOTER_LABEL_API_DOCU')}
          </a>
          <LocalizationSelector />
        </div>
      </div>
    </footer>
  );
};
