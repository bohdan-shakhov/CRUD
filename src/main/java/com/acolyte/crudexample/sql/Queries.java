package com.acolyte.crudexample.sql;

public final class Queries {
    private static final String GET_ALL_BY_ID = "SELECT * FROM employee WHERE emp_id = ?";
    private static final String GET_ALL = "SELECT * FROM employee";
    private static final String INSERT = "INSERT INTO employee (emp_name, salary, dept_name) " +
            "VALUES (?, ?, ?)";
    private static final String UPDATE_BY_ID = "UPDATE employee SET emp_name = ?, salary = ?, dept_name = ? " +
            "WHERE emp_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM employee WHERE emp_id = ?";
}
