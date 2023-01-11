import axios, {AxiosError} from 'axios';
import {useState} from 'react';
import {ErrorResponse} from '../types/ErrorResponse';
import {IBANResponse} from '../types/IBANResponse';

export const useValidationRequest = () => {
  const [pending, setPending] = useState(false);
  const [information, setInformation] = useState<IBANResponse | null>(null);
  const [erroCode, setErroCode] = useState<string | null>(null);

  const execute = async (iban: string) => {
    setPending(true);
    const response = await axios
      .post<ErrorResponse>(
        `${process.env.REACT_APP_API_ENDPOINT}/api/iban/validate`,
        {iban}
      )
      .then(response => {
        if (response.status !== 200) {
          return response.data.code ?? response.status;
        }
        setInformation({bankName: 'test'});
        return null;
      })
      .catch((error: AxiosError) => {
        const errorResponse = error.response?.data as ErrorResponse;
        return errorResponse.code ?? error.code;
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

  return {execute, pending, erroCode, information, clearOnValueChange};
};
