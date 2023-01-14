import React from 'react';
import TestRenderer from 'react-test-renderer';
import {Footer} from './Footer';

jest.mock('@tolgee/react', () => ({
  useTranslate: () => ({t: jest.fn(key => key), isLoading: false}),
}));

jest.mock('../LocalizationSelector/LocalizationSelector', () => ({
  LocalizationSelector: () => <div>LocalizationSelector</div>,
}));

it('should test the Footer component is rendered correctly', () => {
  const tree = TestRenderer.create(<Footer />);
  expect(tree.toJSON()).toMatchSnapshot();
});
