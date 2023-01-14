import React from 'react';
import TestRenderer, {act} from 'react-test-renderer';
import {LocalizationSelector} from './LocalizationSelector';

jest.mock('@tolgee/react', () => ({
  useTranslate: () => ({t: jest.fn(key => key), isLoading: false}),
  useTolgee: () => ({
    isLoading: false,
    getLanguage: jest.fn(),
    changeLanguage: jest.fn(),
  }),
}));

it('should test the LocalizationSelector component is rendered correctly', () => {
  const tree = TestRenderer.create(<LocalizationSelector />);
  expect(tree.toJSON()).toMatchSnapshot();
});

it('should test the LocalizationSelector can change language', () => {
  const tree = TestRenderer.create(<LocalizationSelector />);
  act(() => {
    tree.root.findByType('select').props.onChange({target: {value: 'test'}});
  });
  expect(tree.toJSON()).toMatchSnapshot();
});
