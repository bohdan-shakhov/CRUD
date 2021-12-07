package com.acolyte.crudexample.dao;

import com.acolyte.crudexample.model.Employee;
import com.acolyte.crudexample.sql.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoEmployee implements EmployeeDao {
    private DaoEmployee(){}

    private static class SingletonHelper {
        private static final DaoEmployee INSTANCE = new DaoEmployee();
    }

    public static DaoEmployee getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<Employee> find(String id) throws SQLException {
        int empId = 0;
        String empName = null;
        double empSalary = 0.0;
        String empDept = null;

        Connection connection = DataSourceFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(Queries.GET_BY_ID);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            empId = resultSet.getInt("emp_id");
            empName = resultSet.getString("emp_name");
            empSalary = resultSet.getDouble("salary");
            empDept = resultSet.getString("dept_name");
        }
        return Optional.of(new Employee(empId, empName, empSalary, empDept));
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employeeList = new ArrayList<>();
        Connection connection = DataSourceFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(Queries.GET_ALL);

        while (resultSet.next()) {
            int id = resultSet.getInt("emp_id");
            String name = resultSet.getString("emp_name");
            double salary = resultSet.getDouble("salary");
            String department = resultSet.getString("dept_name");
            Employee employee = new Employee(id, name, salary, department);
            employeeList.add(employee);
        }
        return employeeList;
    }

    @Override
    public boolean save(Employee employee) throws SQLException {
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
}
