import React from 'react';
import {classNames} from '../../utils/classNames';
import styles from './Title.module.scss';

export const Title = (props: React.HtmlHTMLAttributes<HTMLHeadingElement>) => {
  return (
    <h1
      {...props}
      className={classNames(
        props.className,
        'font-omesbold tracking-normal',
        styles.colorfull,
        styles.size
      )}
    />
  );
};
