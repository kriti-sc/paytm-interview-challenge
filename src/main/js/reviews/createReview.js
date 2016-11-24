import React from 'react';
import ReactDOM from 'react-dom';
import $ from 'jquery';
import {Modal as RbsModal} from 'react-bootstrap';
import {FormGroup} from 'react-bootstrap';
import {FormControl} from 'react-bootstrap';
import {ControlLabel} from 'react-bootstrap';
import {Button as RbsButton} from 'react-bootstrap';

const CreateReviewModal = React.createClass({

    getInitialState : function() {
        return {
            employees :[],
            selectedEmpId:-1
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
        $.ajax({
            type:'POST',
            url:'/reviews/create/'+this.state.selectedEmpId,
            data: JSON.stringify({content: this.state.content,feedbackId: this.state.feedbackId, completed:true}),
            contentType:"application/json; charset=utf-8",
            success: function(res) {
                if(res.status=='OK') {
                    alert('Review created successfully');
                }else {
                    alert('Update failed'+res.message);
                }
            }
        });
        this.closeModal();
    },
    handleChange : function(e) {
        this.setState({selectedEmpId:event.target.value})
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
                        <FormGroup>
                            <ControlLabel>Choose Employee</ControlLabel>
                            <FormControl componentClass="select" placeholder="select" value={this.state.selectedEmpId} onChange={this.handleChange}>
                                <option value="select">select</option>
                                <option value="other">...</option>
                                {this.state.employees.map(function(emp){
                                    return (
                                        <option value={emp.empId}>{emp.lastName},{emp.firstName}</option>
                                    );
                                })}
                            </FormControl>
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

export {CreateReviewModal};