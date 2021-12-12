package com.acolyte.crudexample.controller;

import com.acolyte.crudexample.dao.DaoEmployee;
import com.acolyte.crudexample.model.Employee;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet("/")
public class EmployeeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DaoEmployee daoEmployee = DaoEmployee.getInstance();
    private static final Logger LOGGER = Logger.getLogger(EmployeeController.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertEmployee(request, response);
                case "/delete":
                    deleteEmployee(request, response);
                case "/edit":
                    showEditForm(request, response);
                case "/update":
                    updateEmployee(request, response);
                default:
                    listEmployee(request, response);
                    break;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "trouble with executing redirect methods", e);
        }
    }

    private void listEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Employee> employeeList = daoEmployee.findAll();
        request.setAttribute("listEmployee", employeeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/employee-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/employee-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        Optional<Employee> existingEmployee = daoEmployee.find(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/employee-form.jsp");
        request.setAttribute("employee", existingEmployee);
        dispatcher.forward(request, response);
    }

    private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String department = request.getParameter("department");
        Employee employee = new Employee(name, salary, department);
        daoEmployee.insert(employee);
        response.sendRedirect("list");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String department = request.getParameter("department");

        Employee employee = new Employee(id, name, salary, department);
        daoEmployee.update(employee);
        response.sendRedirect("list");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = new Employee(id);
        daoEmployee.delete(employee);
        response.sendRedirect("list");
    }



}
