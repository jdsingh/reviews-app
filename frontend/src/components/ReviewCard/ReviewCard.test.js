import {render, screen} from '@testing-library/react';
import ReviewCard from "./ReviewCard";

test('renders review card', () => {
    render(<ReviewCard review={
        {
            id: "1",
            title: 'Great place to stay',
            content: 'The hotel was very clean and the staff were very helpful.',
            author: 'John Doe',
            rating: 5,
            datetime: '2021-03-15T12:00:00Z'
        }
    }/>);

    const titleElement = screen.getByText("Great place to stay");
    expect(titleElement).toBeInTheDocument();

    const contentElement = screen.getByText("The hotel was very clean and the staff were very helpful.");
    expect(contentElement).toBeInTheDocument();

    const authorElement = screen.getByText("John Doe");
    expect(authorElement).toBeInTheDocument();

    const dateElement = screen.getByText("March 15, 2021 at 8:00:00 AM");
    expect(dateElement).toBeInTheDocument();

    const ratingElement = screen.getByText("Rating: 5 stars");
    expect(ratingElement).toBeInTheDocument();
});
