var AdminComponent = React.createClass({

    getInitialState : function() {
        return {
            employees :[],
            reviews :[]
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
        return (<div className="container"><h1> Hello Admin</h1>
                <div className="button-bar">
                    <button className="btn-primary" type="button">Add Employee</button>
                </div>
                <table className="table table-bordered">
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Emp ID</th>
                        <th>Employee Type</th>
                        <th>Actions</th>
                    </tr>
                    {this.state.employees.map(function(emp){
                        return <tr>
                            <td>{emp.firstName}</td>
                            <td>{emp.lastName}</td>
                            <td>{emp.empId}</td>
                            <td>{emp.type}</td>
                            <td><div className="btn-group-sm">
                                <button type="button" className="btn-sm">Update</button>
                                <button type="button" className="btn-sm">Delete</button>
                            </div> </td>
                         </tr>;
                    })}
                </table>

            </div>);
    }
});

ReactDOM.render(<AdminComponent/>,document.getElementById('react'));