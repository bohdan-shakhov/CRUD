package com.acolyte.crudexample.dao;

import com.acolyte.crudexample.model.Employee;
import com.acolyte.crudexample.sql.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoEmployee implements EmployeeDao {
    private int noOfRecords;
    private DaoEmployee(){}

    private static class SingletonHelper {
        private static final DaoEmployee INSTANCE = new DaoEmployee();
    }

    public static DaoEmployee getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Employee find(String id) throws SQLException {
        int empId = 0;
        String name = null;
        double salary = 0.0;
        String department = null;

        Connection connection = DataSourceFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(Queries.GET_BY_ID);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            empId = resultSet.getInt("emp_id");
            name = resultSet.getString("emp_name");
            salary = resultSet.getDouble("salary");
            department = resultSet.getString("dept_name");
        }
        return new Employee(empId, name, salary, department);
    }

    @Override
    public List<Employee> findAll(int offset, int noOfRecords) throws SQLException {
        List<Employee> employeeList = new ArrayList<>();
        Connection connection = DataSourceFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(Queries.GET_ALL + " LIMIT " + offset + ", " + noOfRecords);

        while (resultSet.next()) {
            int id = resultSet.getInt("emp_id");
            String name = resultSet.getString("emp_name");
            double salary = resultSet.getDouble("salary");
            String department = resultSet.getString("dept_name");
            Employee employee = new Employee(id, name, salary, department);
            employeeList.add(employee);
        }
        resultSet.close();

        resultSet = statement.executeQuery("SELECT COUNT(*) FROM employee");
        if (resultSet.next())
            this.noOfRecords = resultSet.getInt(1);

        return employeeList;
    }

    @Override
    public boolean insert(Employee employee) throws SQLException {
        boolean rowInserted;
        Connection connection = DataSourceFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(Queries.INSERT);
        statement.setString(1, employee.getName());
        statement.setDouble(2, employee.getSalary());
        statement.setString(3, employee.getDepartment());
        rowInserted = statement.executeUpdate() > 0;
        return rowInserted;
    }

    @Override
    public boolean update(Employee employee) throws SQLException {
        boolean rowUpdated;
        Connection connection = DataSourceFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(Queries.UPDATE_BY_ID);
        statement.setString(1, employee.getName());
        statement.setDouble(2, employee.getSalary());
        statement.setString(3, employee.getDepartment());
        statement.setInt(4, employee.getId());
        rowUpdated = statement.executeUpdate() > 0;
        return rowUpdated;
    }

    @Override
    public boolean delete(Employee employee) throws SQLException {
        boolean rowDeleted;
        Connection connection = DataSourceFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(Queries.DELETE_BY_ID);
        statement.setInt(1, employee.getId());
        rowDeleted = statement.executeUpdate() > 0;
        return rowDeleted;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }
}
