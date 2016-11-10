var destination = document.querySelector("#container");
var { Router,
    Route,
    IndexRoute,
    IndexLink,
    Link } = ReactRouter;

var BsTable = ReactBootstrap.Table;
var RbsButton = ReactBootstrap.Button;
var ButtonToolBar = ReactBootstrap.ButtonToolbar;
var RbsModal = ReactBootstrap.Modal;
var Form = ReactBootstrap.Form;
var FormGroup = ReactBootstrap.FormGroup;
var FormControl = ReactBootstrap.FormControl;
var Col = ReactBootstrap.Col;
var ControlLabel = ReactBootstrap.ControlLabel;

var CreateReviewModal = React.createClass({

    getInitialState : function() {
        return {
            employees :[]
        }
    },


    componentDidMount : function() {
        $.get('/employee/list', function (data) {
            this.setState({
                employees: data
            });
        }.bind(this));
    },

        closeModal : function() {
        if(typeof this.props.onClose === 'function') {
            this.props.onClose();
        }
    },

    createReview: function(e) {
        e.preventDefault();
        this.closeModal();
    },

    render : function() {
        return(
            <RbsModal show={this.props.showCreateModal} onHide={this.closeModal}>
                <RbsModal.Header closeButton>
                    <RbsModal.Title>
                        Create Review
                    </RbsModal.Title>
                </RbsModal.Header>
                <form onsubmit={this.createReview}>
                    <RbsModal.Body>
                        <FormGroup controlId="formEmpSelect">
                            <Col componentClass={ControlLabel} sm={2}>
                                Reviewee
                            </Col>
                            <Col sm={10}>
                                <FormControl componentClass="select" name="selected_id" ref="selected_id">
                                    <option value={-1} >select</option>
                                    {this.state.employees.map(function(emp){
                                        return (
                                            <option value={emp.empId}>{emp.lastName},{emp.firstName}</option>
                                        );
                                    })}
                                </FormControl>
                            </Col>
                        </FormGroup>
                    </RbsModal.Body>
                    <RbsModal.Footer>
                        <RbsButton onClick={this.createReview} bsStyle="primary">Create</RbsButton>
                    </RbsModal.Footer>
                </form>
            </RbsModal>
        );
    }
});
var Employees = React.createClass({

    getInitialState : function() {
        return {
            employees :[]
        }
    },


    componentDidMount : function() {
        alert('mounting component');
        $.get('/employee/list',function(data){
            this.setState({
                employees : data
            });
        }.bind(this));


    },

        render: function() {
        return (
            <div><h2>List of Employees</h2>
                <div className="button-bar">
                    <button className="btn-primary" type="button">Add</button>
                    <button className="btn-primary" type="button">Update</button>
                    <button className="btn-primary" type="button">Delete</button>
                </div>
                <div>
                    <BsTable striped bordered condensed hover>
                        <thead>
                        <tr>
                            <th></th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Emp ID</th>
                            <th>Employee Type</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.employees.map(function(emp){
                            return <tr>
                                <td><input type="radio"/> </td>
                                <td>{emp.firstName}</td>
                                <td>{emp.lastName}</td>
                                <td>{emp.empId}</td>
                                <td>{emp.type}</td>
                            </tr>;
                        })}
                        </tbody>
                    </BsTable>
                </div>
            </div>
        );
    }
});

var Reviews = React.createClass({

    getInitialState : function() {
        return {
            reviews :[],
            showCreateModal: false
        }
    },

    componentDidMount : function() {
        $.get('/reviews',function(data){
            this.setState({
                reviews : data,
                showCreateModal: false
            });
        }.bind(this));


    },


    openModal : function() {
        this.setState({ showCreateModal: true });
    },

    closeModal : function() {
      this.setState({showCreateModal : false});
    },

    createReview : function() {
        alert('create!');
    },

    render: function() {
        return (
            <div>
                <h2>List of Reviews</h2>
                <ButtonToolBar>
                    <RbsButton bsStyle="primary" onClick={this.openModal}>Create</RbsButton>
                </ButtonToolBar>

                <BsTable striped bordered condensed hover>
                    <thead>
                    <tr>
                        <th>Review ID</th>
                        <th>Reviewee Name</th>
                        <th>Reviewee ID</th>
                        <th>State</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.state.reviews.map(function(item){
                        return <tr>
                            <td><Link to="/feedback">{item.reviewId}</Link></td>
                            <td>{item.revieweName}</td>
                            <td>{item.revieweeEmpId}</td>
                            <td>{item.state}</td>
                        </tr>;
                    })}
                    </tbody>
                </BsTable>
                <CreateReviewModal showCreateModal={this.state.showCreateModal} onClose={this.closeModal}/>
               </div>
        );
    }
});

var AdminFeedback = React.createClass({
    render: function() {
        return (<h2>hello feedbacks</h2>);
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
            <Route path="feedback" component={AdminFeedback} />
        </Route>
    </Router>,
    destination
);