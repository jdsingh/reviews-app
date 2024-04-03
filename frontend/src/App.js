import './App.css';
import ReviewCard from "./components/ReviewCard/ReviewCard";
import {useEffect, useState} from "react";

function App() {
    // use state to store reviews
    const [reviews, setReviews] = useState([]);

    useEffect(() => {
        fetch('http://0.0.0.0:8080/api/reviews')
            .then(response => response.json())
            .then(data => setReviews(data));
    }, []);

    return (
        <div className="App">
            <h1>Reviews in last 48 hours</h1>
            {reviews.map(review => <ReviewCard key={review.id} review={review}/>)}
            {reviews.length === 0 && <p>No reviews in the last 48 hours</p>}
        </div>
    );
}

export default App;
