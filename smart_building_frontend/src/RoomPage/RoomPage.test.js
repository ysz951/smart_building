import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import RoomPage from './RoomPage';
it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(
    <BrowserRouter>
      <RoomPage />
    </BrowserRouter>, div);
  ReactDOM.unmountComponentAtNode(div)
})