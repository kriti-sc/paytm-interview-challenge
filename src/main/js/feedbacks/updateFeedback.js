import React from 'react';
import ReactDOM from 'react-dom';
import {Modal as RbsModal} from 'react-bootstrap';
import {FormGroup} from 'react-bootstrap';
import {FormControl} from 'react-bootstrap';
import {ControlLabel} from 'react-bootstrap';
import $ from 'jquery';

const UpdateFeedbackModal = React.createClass({

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
        e.preventDefault();
        //$.post('/feedback/update',{content: this.state.content,feedbackId: this.state.feedbackId, completed:true})
        $.ajax({
            type:'POST',
            url:'/feedback/update',
            data: JSON.stringify({content: this.state.content,feedbackId: this.state.feedbackId, completed:true}),
            contentType:"application/json; charset=utf-8",
            success: function(res) {
                if(res.status=='OK') {
                    alert('Feedback updated successfully');
                }else {
                    alert('Update failed'+res.message);
                }
            }
        });
        this.closeModal();
    },

    saveAsDraft : function(e) {
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

export {UpdateFeedbackModal};