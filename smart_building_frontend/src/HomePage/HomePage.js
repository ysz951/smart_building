import { Link } from 'react-router-dom';

function HomePage() {
    return (
        <div className="home-div mt-4">
            <h1>Welcome</h1>
            <Link to="/login">Find more</Link>
        </div>
    )

}

export default HomePage;