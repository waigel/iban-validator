import {useTranslate} from '@tolgee/react';
import React, {useEffect} from 'react';
import useState from 'react-usestateref';
import testData from '../../data/testData.json';
import {classNames} from '../../utils/classNames';

export interface DeveloperDialogProps {
  onTestIBANSelected: (iban: string) => void;
}

export const DeveloperDialog = ({onTestIBANSelected}: DeveloperDialogProps) => {
  const [developerDialog, setDeveloperDialog] = useState(false);
  const {t} = useTranslate();

  const handleKeyPressEvents = (event: KeyboardEvent) => {
    const key = event.key;
    if (key === 'i' && event.ctrlKey === true) {
      setDeveloperDialog(!developerDialog);
    }
  };

  useEffect(() => {
    window.addEventListener('keydown', handleKeyPressEvents, false);
    return () => window.removeEventListener('keydown', handleKeyPressEvents);
  }, [developerDialog]);

  return (
    <>
      {developerDialog && (
        <div className="p-8 w-64  bg-card absolute z-50 bottom-10 right-10 rounded-xl">
          <div className="text-center font-omesbold  flex justify-between">
            <h3>{t('TEST_IBAN_TITLE')}</h3>
            <button onClick={() => setDeveloperDialog(false)}>X</button>
          </div>
          <hr className="my-2" />
          <ul className="text-sm overflow-y-auto">
            {testData.map((testIBAN, index) => {
              return (
                <li
                  key={index}
                  className={classNames(
                    'cursor-pointer py-1 truncate ',
                    testIBAN.isValid ? 'text-green-500' : 'text-red-500'
                  )}
                  onClick={() => onTestIBANSelected(testIBAN.value)}
                >
                  {testIBAN.value}
                </li>
              );
            })}
          </ul>
        </div>
      )}
    </>
  );
};
