USE employeedb;

DROP TABLE IF EXISTS employeedb.employee;

CREATE TABLE IF NOT EXISTS employeedb.employee (
    emp_id INT(11) NOT NULL AUTO_INCREMENT,
    emp_name VARCHAR(255) NOT NULL,
    salary DOUBLE NOT NULL,
    dept_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (emp_id)
    );

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('Bohdan', 1000.0, 'Java');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('John', 1200.0, 'Python');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('Andrew', 900.0, 'Python');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('Alex', 700.0, 'C++');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('Tony', 500.0, 'C');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('Micheal', 800.0, 'Java');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('Will', 1000.0, 'Java');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('Dereck', 400.0, 'C');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('Rahim', 700.0, 'C++');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('Bernardo', 800.0, 'Python');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('Scott', 1000.0, 'JavaScript');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('Cristiano', 1500.0, 'JavaScript');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('Tomy', 600.0, 'Java');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('David', 1100.0, 'UX/UI');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('Van', 1200.0, 'UX/UI');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('Tomas', 500.0, 'UX/UI');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('Lionel', 1500.0, 'Java');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('Frankie', 800.0, 'UX/UI');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('Memphis', 1200.0, 'C');

INSERT INTO employee (emp_name, salary, dept_name)
VALUES ('Robert', 1500.0, 'Python');


