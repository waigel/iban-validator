import React from 'react';
import TestRenderer from 'react-test-renderer';
import {Title} from './Title';

it('should test the Title component is rendered correctly', () => {
  const tree = TestRenderer.create(<Title>TEST TITLE</Title>).toJSON();
  expect(tree).toMatchSnapshot();
});

it('should test the Title component is rendered correctly with additional className', () => {
  const tree = TestRenderer.create(
    <Title className={'test2'}>TEST TITLE</Title>
  ).toJSON();
  expect(tree).toMatchSnapshot();
});
