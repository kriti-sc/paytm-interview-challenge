var destination = document.querySelector("#container");
var { Router,
    Route,
    IndexRoute,
    IndexLink,
    Link } = ReactRouter;

var createEmpModal = React.createClass({

    getInitialState : function() {
        this.handleSubmit = this.handleSubmit.bind(this);
        return   {
            firstName : '',
            lastName : '',
            empId :'',
            empType:''
        }
    },


    handleSubmit : function() {
        alert(this.state.firstName);
    },

    render(){
        return (
            <div className="modal fade">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 className="modal-title">Modal title</h4>
                        </div>
                        <div className="modal-body">
                            <input type="text" label="First Name" value={this.state.firstName}></input>
                            <input type="text" label="Last Name" value={this.state.lastName}></input>
                            <input type="text" label="Emp ID" value={this.state.empId}></input>
                            <input type="text" label="Empolyee Type" value={this.state.empType}></input>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" className="btn btn-primary" onclick={this.handleSubmit}>Create</button>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
});

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

    showCreateModal : function() {
        $(this.refs.modal.findDOMNode()).modal();
    },

    render: function() {
        return (
            <div><h2>List of Employees</h2>
                <div className="button-bar">
                    <button className="btn-primary" type="button" onClick={this.showCreateModal}>Add</button>
                    <button className="btn-primary" type="button">Update</button>
                    <button className="btn-primary" type="button">Delete</button>
                    <createEmpModal/>
                </div>
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