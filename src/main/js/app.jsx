import React from 'react';
import ReactDOM from 'react-dom';
import {Router,Route,IndexRoute,IndexLink,Link} from 'react-router';
import {browserHistory} from 'react-router'
import {Employees} from './employees/employee.js';
import {Reviews} from './reviews/review.js';
import {Home} from './home';
import {AdminFeedback} from './feedbacks/feedback.js';


var App = React.createClass({
    render: function() {
        return (
            <div className="container-fluid" >
                <h1>Employee Performance Reviews App</h1>
                <nav className="navbar navbar-default">
                    <ul className="nav navbar-nav">
                        <li className="active"><Link to="/">Home</Link></li>

                        <li><Link to="/employees">Employees</Link></li>
                        <li><Link to="/reviews">Reviews</Link></li>
                    </ul>
                </nav>
                <div className="reactContainer">
                    {this.props.children}
                </div>
            </div>
        )
    }
});

ReactDOM.render(
    <Router>
        <Route path="/" component={App}>
            <IndexRoute component={Home}/>
            <Route path="employees" component={Employees}/>
            <Route path="reviews" component={Reviews}/>
        </Route>
    </Router>,
    document.getElementById('container')
);
