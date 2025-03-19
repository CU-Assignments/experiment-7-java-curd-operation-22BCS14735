CREATE DATABASE Company;
USE Company;

CREATE TABLE Employee (
    EmpID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50),
    Salary DOUBLE
);

INSERT INTO Employee (Name, Salary) VALUES ('Alice', 50000), ('Bob', 60000);
