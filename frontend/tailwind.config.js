/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./src/**/*.{js,jsx,ts,tsx}'],
  theme: {
    extend: {
      backgroundImage: {
        hero: "url('../public/images/bg.png')",
      },
      fontFamily: {
        omesbold: ['OmnesLatinBold'],
        omessemibold: ['OmnesLatinSemiBold'],
      },
    },
  },
  plugins: [],
};
