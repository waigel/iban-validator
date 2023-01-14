import React from 'react';
import TestRenderer from 'react-test-renderer';
import {BLZRecord} from '../../types/IBANResponse';
import {BLZRecordInformation} from './BLZRecordInformation';

jest.mock('@tolgee/react', () => ({
  useTranslate: () => ({t: jest.fn(key => key), isLoading: false}),
}));

const blzRecord: BLZRecord = {
  blz: '10000000',
  bic: 'GENODEF1M04',
  bankName: 'Deutsche Bank AG',
  shortBankName: 'Deutsche Bank',
  zipCode: '60311',
  city: 'Frankfurt am Main',
};

it('should test the BLZRecordInformation component is rendered correctly', () => {
  const tree = TestRenderer.create(<BLZRecordInformation value={blzRecord} />);
  expect(tree.toJSON()).toMatchSnapshot();
});

it('should display long bankName if shortBankName is not set', () => {
  const blzRecordCopy: BLZRecord = {...blzRecord, shortBankName: undefined};
  const tree = TestRenderer.create(
    <BLZRecordInformation value={blzRecordCopy} />
  );
  expect(tree.toJSON()).toMatchSnapshot();
});

it('should not display bankName if it is not set', () => {
  const blzRecordCopy: BLZRecord = {
    ...blzRecord,
    bankName: undefined,
    shortBankName: undefined,
  };
  const tree = TestRenderer.create(
    <BLZRecordInformation value={blzRecordCopy} />
  );
  expect(tree.toJSON()).toMatchSnapshot();
});

it('should not display bic if it is not set', () => {
  const blzRecordCopy: BLZRecord = {...blzRecord, bic: undefined};
  const tree = TestRenderer.create(
    <BLZRecordInformation value={blzRecordCopy} />
  );
  expect(tree.toJSON()).toMatchSnapshot();
});

it('should not display address if it is not set', () => {
  const blzRecordCopy: BLZRecord = {
    ...blzRecord,
    zipCode: undefined,
    city: undefined,
  };
  const tree = TestRenderer.create(
    <BLZRecordInformation value={blzRecordCopy} />
  );
  expect(tree.toJSON()).toMatchSnapshot();
});
