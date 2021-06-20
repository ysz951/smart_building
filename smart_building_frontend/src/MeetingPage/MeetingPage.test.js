import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import MeetingPage from './MeetingPage';
it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(
    <BrowserRouter>
      <MeetingPage />
    </BrowserRouter>, div);
  ReactDOM.unmountComponentAtNode(div)
})