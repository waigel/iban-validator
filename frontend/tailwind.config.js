/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./src/**/*.{js,jsx,ts,tsx}'],
  theme: {
    extend: {
      backgroundImage: {
        hero: "url('../public/images/bg.png')",
      },
      colors: {
        card: '#212121',
        card2: '#2a2a2aCC',
        success: '#79903A',
      },
      fontFamily: {
        omesbold: ['OmnesLatinBold'],
        omessemibold: ['OmnesLatinSemiBold'],
      },
    },
  },
  plugins: [],
};
