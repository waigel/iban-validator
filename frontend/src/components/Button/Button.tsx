import React from 'react';
import {classNames} from '../../utils/classNames';
export type ButtonProps = React.HtmlHTMLAttributes<HTMLButtonElement>;

export const Button = (props: ButtonProps) => {
  return (
    <button
      {...props}
      className={classNames(
        props.className,
        'bg-white px-4 py-2 rounded-3xl border text-black hover:scale-110 font-omessemibold'
      )}
      type="submit"
    />
  );
};
