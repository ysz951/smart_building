import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import CreateDepartment from './CreateDepartment';
it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(
    <BrowserRouter>
        <CreateDepartment />
    </BrowserRouter>, div);
  ReactDOM.unmountComponentAtNode(div)
})