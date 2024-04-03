import { render, screen } from '@testing-library/react';
import App from './App';

test('renders reviews react app', () => {
  render(<App />);
  const linkElement = screen.getByText(/Reviews in last 48 hours/i);
  expect(linkElement).toBeInTheDocument();
});
