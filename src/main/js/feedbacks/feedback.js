import React from 'react';
import ReactDOM from 'react-dom';
import ReactBootstrap from 'react-bootstrap/dist/react-bootstrap.min.js';
import $ from 'jquery';
import {UpdateFeedbackModal} from './updateFeedback.js';
import  {Button as RbsButton} from 'react-bootstrap';
import {Table as BsTable} from 'react-bootstrap';

const AdminFeedback = React.createClass({
    getInitialState : function() {
        return {
            feedbacks :[],
            open:false
        }
    },


    componentDidMount : function() {
        $.get('/feedback/view/'+this.props.params.reviewId,function(data){
            this.setState({
                feedbacks : data
            });
        }.bind(this));

    },
    openAssignPanel : function() {
        if(!this.state.open) {
            $.get('/employee/list', function (data) {
                this.setState({
                    employees: data,
                    open: !this.state.open
                });
            }.bind(this));
        }
        else {
            this.setState({open:!this.state.open})
        }
    },
    render: function() {
        const assignEmp = {}
        return (
            <div><h2>List of Feedbacks</h2>

                <div>
                    <BsTable striped bordered condensed hover>
                        <thead>
                        <tr>
                            <th>Reviewee</th>
                            <th>Reviewee ID</th>
                            <th>Reviewer ID</th>
                            <th>Content</th>
                            <th>State</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.feedbacks.map((fdb)=>{
                            return <tr>
                                <td>{fdb.revieweeName}</td>
                                <td>{fdb.revieweeId}</td>
                                <td>{fdb.reviewerId}</td>
                                <td>{fdb.content}</td>
                                <td>{fdb.state}</td>
                            </tr>;
                        })}
                        </tbody>
                    </BsTable>
                </div>
                <div>
                    <RbsButton bsStyle="info" onClick={this.openAssignPanel}>Assign Reviewers</RbsButton>
                    <Panel collapsible expanded={this.state.open}>
                    </Panel>
                </div>
            </div>
        );
    }
});



const Feedbacks = React.createClass({

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
        this.state.feedbacks.forEach(item=> {
            if (item.feedbackId === JSON.parse(fbId)) {
                alert('fbId=' + fbId + ' item.feedbackId=' + item.feedbackId);
                fbItem = item;
            }
        });
        return fbItem;
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

export {AdminFeedback,Feedbacks};