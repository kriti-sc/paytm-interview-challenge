import React from 'react';
import ReactDOM from 'react-dom';
var empId = document.getElementById("empId").value;
var empName = document.getElementById("empName").value;

export const Home = React.createClass({
    render: function() {
        return (
            <div>
                <h2>Hello {empName}</h2>
            </div>
        );
    }
});