import React from 'react';
import ReactDOM from 'react-dom';
import $ from 'jquery';
import {CreateReviewModal} from './createReview.js';
import {Table as BsTable,ButtonToolBar,Button as RbsButton} from 'react-bootstrap';
import {Link} from 'react-router';

const Reviews = React.createClass({

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
        //this.setState({showCreateModal : false});
        this.componentDidMount();
    },



    render: function() {
        return (
            <div>
                <h2>List of Reviews</h2>
                <RbsButton bsStyle="primary" onClick={this.openModal}>Create</RbsButton>
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
                            <td><Link to={"/feedback/"+item.reviewId}>{item.reviewId}</Link></td>
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

export {Reviews};