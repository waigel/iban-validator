import React from 'react';
import TestRenderer from 'react-test-renderer';
import {Layout} from './Layout';

jest.mock('../Footer/Footer', () => ({
  Footer: () => <div>Footer</div>,
}));

it('should test the Layout component is rendered correctly', () => {
  const tree = TestRenderer.create(
    <Layout>
      <p>TEST CHILD</p>
    </Layout>
  ).toJSON();
  expect(tree).toMatchSnapshot();
});
