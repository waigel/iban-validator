import React from 'react';
import TestRenderer from 'react-test-renderer';
import {AnimationStatus} from './AnimationStatus';
import 'jest-canvas-mock';

it('should test the AnimationStatus component rendering', () => {
  let tree = TestRenderer.create(<AnimationStatus status="failed" />);
  expect(tree.toJSON()).toMatchSnapshot();
  tree = TestRenderer.create(<AnimationStatus status="success" />);
  expect(tree.toJSON()).toMatchSnapshot();
});
