import React from 'react';
import {Footer} from '../Footer/Footer';

export interface LayoutProps {
  children: React.ReactElement;
}

export const Layout = ({children}: LayoutProps) => {
  return (
    <div className="w-screen h-screen bg-black text-white">
      <main>{children}</main>
      <Footer />
    </div>
  );
};
