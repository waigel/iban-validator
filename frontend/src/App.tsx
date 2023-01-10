import React from 'react';
import {IBANComponent} from './components/IBANComponent/IBANComponent';
import {Layout} from './components/Layout/Layout';
import {Title} from './components/Title/Title';

function App() {
  return (
    <Layout>
      <section
        className="flex flex-col py-8 md:py-64 bg-hero bg-cover bg-bottom bg-no-repeat bg-tr px-8 md:px-0"
        id="iban"
      >
        <Title className="self-center">IBAN Validierung</Title>
        <p className="text-xl text-center self-center md:w-4/6">
          Geben eine IBAN ein, um zu überprüfen ob diese gültig ist und um zu
          erfahren, bei welcher Bank das dazugehörige Konto liegt.
        </p>
        <IBANComponent clasName="self-center mt-12" />
      </section>
    </Layout>
  );
}

export default App;
