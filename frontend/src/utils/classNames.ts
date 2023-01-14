type classNamesInputType = string | undefined | number;

export const classNames = (...array: classNamesInputType[]) => {
  return array
    .filter(name => name)
    .map(name => name)
    .join(' ');
};
