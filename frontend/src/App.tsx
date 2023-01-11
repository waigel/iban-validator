import {useTranslate} from '@tolgee/react';
import React from 'react';
import {IBANComponent} from './components/IBANComponent/IBANComponent';
import {Layout} from './components/Layout/Layout';
import {Title} from './components/Title/Title';

export const App = () => {
  const {t} = useTranslate();
  return (
    <Layout>
      <section className="flex flex-col py-8 md:py-56 lg:py-60 bg-hero bg-cover bg-bottom bg-no-repeat bg-tr px-8 md:px-0">
        <Title className="self-center">{t('APP_TITLE')}</Title>
        <p className="text-xl text-center self-center md:w-4/6">
          {t('APP_DESCRIPTION')}
        </p>
        <IBANComponent clasName="self-center mt-12" />
      </section>
    </Layout>
  );
};

export default App;
