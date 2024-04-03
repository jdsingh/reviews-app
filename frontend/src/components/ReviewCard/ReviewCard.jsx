import React from 'react';
import './ReviewCard.css';

// ReviewCard component
const ReviewCard = ({review}) => {
    // Destructure review object for easier access
    const {title, content, author, rating, datetime} = review;

    // Convert date to a more human-readable format
    const formattedDate = new Date(datetime).toLocaleTimeString('en-US', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
    });

    return (
        <div className="review-card">
            <h2>{title}</h2>
            <p>By <span className="author-name">{author}</span> on <span className="review-date">{formattedDate}</span>
            </p>
            <p>Rating: {rating} stars</p>
            <p>{content}</p>
        </div>
    );
};

export default ReviewCard;
