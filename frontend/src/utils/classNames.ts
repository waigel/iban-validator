type classNamesInputType = string | undefined | number;

export const classNames = (...array: classNamesInputType[]) => {
  return array.map(name => name).join(' ');
};
