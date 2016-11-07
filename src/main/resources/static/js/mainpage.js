var destination = document.querySelector("#container");
var { Router,
    Route,
    IndexRoute,
    IndexLink,
    Link } = ReactRouter;

var Employees = React.createClass({

    getInitialState : function() {
        return {
            employees :[]
        }
    },


    componentDidMount : function() {
        $.get('/employee/list',function(data){
            this.setState({
                employees : data
            });
        }.bind(this));


    },

    render: function() {
        return (
            <div><h2>List of Employees</h2>
                <ButtonGroup>
                    <Button bsStyle="primary">Add</Button>
                    <Button bsStyle="primary">Update</Button>
                    <Button bsStyle="primary">Delete</Button>
                </ButtonGroup>
                <div className="clearfix">
                    <table className="table table-bordered">
                        <tr>
                            <th></th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Emp ID</th>
                            <th>Employee Type</th>
                        </tr>
                        {this.state.employees.map(function(emp){
                            return <tr>
                                <td><input type="radio"/> </td>
                                <td>{emp.firstName}</td>
                                <td>{emp.lastName}</td>
                                <td>{emp.empId}</td>
                                <td>{emp.type}</td>
                            </tr>;
                        })}
                    </table>
                </div>
            </div>
        );
    }
});

var Reviews = React.createClass({
    render: function() {
        return (
            <div>
                <h2>List of Reviews</h2>
                <table className="table table-bordered">
                    <tr className="table-row-cell">
                        <th>Review ID</th>
                        <th>Reviewer ID</th>
                        <th>Reviewee ID</th>
                        <th>Content</th>
                        <th>State</th>
                    </tr>
                    <tr className="table-row-cell">
                        <td>123</td>
                        <td>123</td>
                        <td>123</td>
                        <td>123</td>
                        <td>123</td>
                    </tr>
                    <tr className="table-row-cell">
                        <td>123</td>
                        <td>123</td>
                        <td>123</td>
                        <td>123</td>
                        <td>123</td>
                    </tr>
                </table>
               </div>
        );
    }
});



var Home = React.createClass({
    render: function() {
        return (
            <div>
                <h2>Hello Admin!</h2>
            </div>
        );
    }
});

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
                <div className="container">
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
    destination
);