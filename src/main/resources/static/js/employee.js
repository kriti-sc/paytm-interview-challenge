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
var Radio = ReactBootstrap.Radio;

var UpdateFeedbackModal = React.createClass({

    getInitialState : function() {
        return {
            feedbackId:-1,
            content:'',
            fbState:'',
            completed:false
        }
    },

    componentWillReceiveProps : function(props) {
        if(this.props.selectedFeedbackObj!=null) {
            this.setState({
                feedbackId: props.selectedFeedbackObj.feedbackId,
                content: props.selectedFeedbackObj.content,
                fbState: props.selectedFeedbackObj.state,
                name: props.selectedFeedbackObj.revieweeName,
                completed: false
            });
        }
    },

    closeModal : function() {
        if(typeof this.props.onClose === 'function') {
            this.props.onClose();
        }
    },

    UpdateFeedback: function(e) {
        alert('on submit click');
        e.preventDefault();
        // post data
        //$.post('/feedback/update',{content: this.state.content,feedbackId: this.state.feedbackId, completed:true})
        //$.ajax({
        //    url:'/feedback/update',
        //    data: {content: this.state.content,feedbackId: this.state.feedbackId, completed:true},
        //    contentType:"application/json; charset=utf-8"
        //});
        this.closeModal();
    },

    saveAsDraft : function(e) {
        alert('on save as draft click');
        e.preventDefault();
        // post state data to server
        this.closeModal();
    },

    handleChange : function(event) {
        this.setState({content: event.target.value});
    },

    render : function() {
        return(
            <RbsModal show={this.props.showUpdateModal} onHide={this.closeModal}>
                <RbsModal.Header closeButton>
                    <RbsModal.Title>
                        Update Feedback for {this.state.name}
                    </RbsModal.Title>
                </RbsModal.Header>
                <form onSubmit={this.UpdateFeedback}>
                    <RbsModal.Body>
                        <FormGroup>
                            <ControlLabel>Feedback Content</ControlLabel>
                            <FormControl componentClass="textarea" placeholder="textarea" value={this.state.content} onChange={this.handleChange} />
                        </FormGroup>
                        </RbsModal.Body>
                    <RbsModal.Footer>
                        <RbsButton type="submit" bsStyle="primary">Submit</RbsButton>
                        <RbsButton onClick={this.saveAsDraft} bsStyle="primary">Save as Draft</RbsButton>
                    </RbsModal.Footer>
                </form>
            </RbsModal>
        );
    }
});

var Feedbacks = React.createClass({

    getInitialState : function() {
        return {
            feedbacks :[],
            selectedFbId: 0,
            showUpdateModal: false
        }
    },


    componentDidMount : function() {
        $.get('/feedback/list/'+empId,function(data){
            this.setState({
                feedbacks : data
            });
        }.bind(this));

    },

    changeFdbIdSelection : function(e) {
        this.setState({selectedFbId: e.currentTarget.value});
    },

    closeModal : function() {
        this.setState({showUpdateModal : false});
    },
    openModal : function() {
        this.setState({ showUpdateModal: true });
    },

    getSelectedFbObj : function(fbId) {
        var fbItem=null;
        //this.state.feedbacks.forEach(item=>{
        //    if(item.feedbackId===fbId) {
        //        alert('fbId='+fbId+' item.feedbackId='+item.feedbackId);
        //        fbItem = item;
        //    }
        return this.state.feedbacks[1];
    },
    render: function() {

        return (
            <div><h2>List of Feedbacks</h2>
                <div className="button-bar">
                    <button className="btn-primary" onClick={this.openModal} type="button">Update</button>
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
                        {this.state.feedbacks.map((fdb)=>{
                            return <tr>
                                <td><input type="radio" name={fdb.feedbackId} value={fdb.feedbackId}
                                        checked={fdb.feedbackId === JSON.parse(this.state.selectedFbId)} onChange={this.changeFdbIdSelection}></input></td>
                                <td>{fdb.revieweeName}</td>
                                <td>{fdb.revieweeId}</td>
                                <td>{fdb.content}</td>
                                <td>{fdb.state}</td>
                            </tr>;
                        })}
                        </tbody>
                    </BsTable>
                    <UpdateFeedbackModal showUpdateModal={this.state.showUpdateModal} selectedFeedbackObj={this.getSelectedFbObj(this.state.selectedFbId)} onClose={this.closeModal}/>
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