import {classNames} from './classNames';

describe('classNames', () => {
  it('should return a string', () => {
    expect(classNames('a', 'b', 'c')).toBe('a b c');
  });

  it('should return a string with undefined', () => {
    expect(classNames('a', undefined, 'c')).toBe('a c');
  });
});
