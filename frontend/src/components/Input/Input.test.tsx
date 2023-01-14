import React from 'react';
import TestRenderer, {act} from 'react-test-renderer';
import {TextInput} from './TextInput';

it('should test the TextInput component is rendered correctly', () => {
  const tree = TestRenderer.create(<TextInput />).toJSON();
  expect(tree).toMatchSnapshot();
});

it('should test the TextInput onChange event is called correctly', () => {
  const onChange = jest.fn();
  const tree = TestRenderer.create(<TextInput onValueChange={onChange} />);
  act(() => {
    const input = tree.root.findByType('input');
    input.props.onChange({target: {value: 'test'}});
  });
  expect(onChange).toBeCalledWith('test');
});

it('should test the TextInput onEnterPress event is called correctly', () => {
  const onEnterPress = jest.fn();
  const tree = TestRenderer.create(<TextInput onEnterPress={onEnterPress} />);
  act(() => {
    const input = tree.root.findByType('input');
    input.props.onKeyDown({key: 'Enter'});
  });
  expect(onEnterPress).toBeCalled();
});

it('should test the TextInput onEnterPress event is not called when key is not Enter', () => {
  const onEnterPress = jest.fn();
  const tree = TestRenderer.create(<TextInput onEnterPress={onEnterPress} />);
  act(() => {
    const input = tree.root.findByType('input');
    input.props.onKeyDown({key: 'ALT'});
  });
  expect(onEnterPress).not.toBeCalled();
});

it('should test the TextInput defaultValue is set correctly', () => {
  const tree = TestRenderer.create(<TextInput defaultValue={'test'} />);
  const input = tree.root.findByType('input');
  expect(input.props.value).toEqual('test');
});
