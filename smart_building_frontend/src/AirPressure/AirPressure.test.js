import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import AirPressure from './AirPressure';
it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(
    <BrowserRouter>
        <AirPressure />
    </BrowserRouter>, div);
  ReactDOM.unmountComponentAtNode(div)
})