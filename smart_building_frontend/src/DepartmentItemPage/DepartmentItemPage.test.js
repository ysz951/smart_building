import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import DepartmentItemPage from './DepartmentItemPage';
it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(
    <BrowserRouter>
        <DepartmentItemPage />
    </BrowserRouter>, div);
  ReactDOM.unmountComponentAtNode(div)
})