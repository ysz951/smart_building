import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import MeetingItemPage from './MeetingItemPage';
it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(
    <BrowserRouter>
      <MeetingItemPage />
    </BrowserRouter>, div);
  ReactDOM.unmountComponentAtNode(div)
})