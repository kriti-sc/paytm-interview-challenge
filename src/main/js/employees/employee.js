


import React from 'react';
import ReactDOM from 'react-dom';
import {Table as BsTable} from 'react-bootstrap';
import $ from 'jquery';

const Employees =  React.createClass({

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

export {Employees};

