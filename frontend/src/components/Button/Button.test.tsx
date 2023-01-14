import React from 'react';
import TestRenderer from 'react-test-renderer';
import {Button} from './Button';

it('should test the Button component is rendered correctly', () => {
  const tree = TestRenderer.create(<Button />).toJSON();
  expect(tree).toMatchSnapshot();
});
