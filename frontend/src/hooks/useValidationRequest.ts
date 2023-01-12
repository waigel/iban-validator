import {useTolgee} from '@tolgee/react';
import axios, {AxiosError} from 'axios';
import {useState} from 'react';
import {ErrorResponse} from '../types/ErrorResponse';
import {IBANResponse} from '../types/IBANResponse';

export const useValidationRequest = () => {
  const [pending, setPending] = useState(false);
  const [information, setInformation] = useState<IBANResponse | null>(null);
  const [erroCode, setErroCode] = useState<ErrorResponse | null>(null);

  const tolgee = useTolgee(['language']);

  const execute = async (iban: string) => {
    setPending(true);

    const response = await axios
      .post<IBANResponse | ErrorResponse>(
        `${process.env.REACT_APP_API_ENDPOINT}/iban/validate`,
        {iban, locale: tolgee.getLanguage()}
      )
      .then(response => {
        if ('code' in response.data) {
          return {
            code: response.data.code ?? response.status,
            params: response.data.params,
          };
        } else {
          setInformation(response.data);
        }
        return null;
      })
      .catch((error: AxiosError<ErrorResponse>) => {
        return {
          code: error.response?.data.code ?? error.response?.status ?? 500,
          params: error.response?.data.params,
        };
      })
      .finally(() => setPending(false));

    setErroCode(response);
    if (response !== null) {
      setInformation(null);
    }
    return response;
  };

  const clearOnValueChange = () => {
    setErroCode(null);
    setInformation(null);
  };

  return {
    execute,
    pending,
    erroCode,
    information,
    clearOnValueChange,
  };
};
