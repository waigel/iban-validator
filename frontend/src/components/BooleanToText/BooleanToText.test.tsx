import React from 'react';
import TestRenderer from 'react-test-renderer';
import {BooleanToText} from './BooleanToText';

jest.mock('@tolgee/react', () => ({
  useTranslate: () => ({t: jest.fn(key => key), isLoading: false}),
}));

it('should test the BooleanToText component is rendered correctly for true', () => {
  const tree = TestRenderer.create(<BooleanToText value />);
  expect(tree.toJSON()).toMatchSnapshot();
});

it('should test the BooleanToText component is rendered correctly for false', () => {
  const tree = TestRenderer.create(<BooleanToText value={false} />);
  expect(tree.toJSON()).toMatchSnapshot();
});
