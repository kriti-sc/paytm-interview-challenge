var destination = document.querySelector("#container");

var empId = document.getElementById("empId").value;
var empName = document.getElementById("empName").value;


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

var Feedbacks = React.createClass({

    getInitialState : function() {
        return {
            feedbacks :[]
        }
    },


    componentDidMount : function() {
        $.get('/feedback/list/'+empId,function(data){
            this.setState({
                feedbacks : data
            });
        }.bind(this));


    },

    render: function() {
        return (
            <div><h2>List of Feedbacks</h2>
                <div className="button-bar">
                    <button className="btn-primary" type="button">Update</button>
                </div>
                <div>
                    <BsTable striped bordered condensed hover>
                        <thead>
                        <tr>
                            <th></th>
                            <th>Reviewee</th>
                            <th>Reviewee ID</th>
                            <th>Content</th>
                            <th>State</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.feedbacks.map(function(fdb){
                            return <tr>
                                <td><input type="radio" value={fdb.feedbackId}/> </td>
                                <td>{fdb.revieweeName}</td>
                                <td>{fdb.revieweeId}</td>
                                <td>{fdb.content}</td>
                                <td>{fdb.state}</td>
                            </tr>;
                        })}
                        </tbody>
                    </BsTable>
                </div>
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
                <h2>Hello {empName}</h2>
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
                        <li><Link to="/feedbacks">Feedbacks</Link></li>
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
            <Route path="feedbacks" component={Feedbacks} />
        </Route>
    </Router>,
    destination
);