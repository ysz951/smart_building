import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import DepartmentRoom from './DepartmentRoom';
it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(
    <BrowserRouter>
        <DepartmentRoom />
    </BrowserRouter>, div);
  ReactDOM.unmountComponentAtNode(div)
})