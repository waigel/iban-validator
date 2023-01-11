import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import './index.css';
import reportWebVitals from './reportWebVitals';
import {Tolgee, DevTools, TolgeeProvider, FormatSimple} from '@tolgee/react';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

const tolgee = Tolgee().use(DevTools()).use(FormatSimple()).init({
  language: 'de-DE',
  apiUrl: process.env.REACT_APP_TOLGEE_API_URL,
  apiKey: process.env.REACT_APP_TOLGEE_API_KEY,
});

root.render(
  <React.StrictMode>
    <TolgeeProvider
      tolgee={tolgee}
      fallback="Loading..." // loading fallback
    >
      <App />
    </TolgeeProvider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
