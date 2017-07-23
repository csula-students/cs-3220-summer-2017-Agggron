-- Create the tables

CREATE TABLE employees (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    supervisor_id INTEGER REFERENCES employees(id)
);

CREATE TABLE projects (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    leader_id INTEGER REFERENCES employees(id)
);

CREATE TABLE project_members (
    project_id INTEGER REFERENCES projects(id),
    member_id INTEGER REFERENCES employees(id)
);

-- Insert dummy data

INSERT INTO employees VALUES
(1, 'John', 'Doe', 'Street #215', null),
(2, 'Jane', 'Doe', 'Street #217', 1);

INSERT INTO projects VALUES
(1, 'Firestone', 1),
(2, 'Blue', 2);

INSERT INTO project_members VALUES
(1, 1),
(2, 1), 
(2, 2);

-- List the last names of the employees whose id are less than 10

SELECT DISTINCT last_name FROM employees WHERE id < 10 
ORDER BY last_name;

-- Find the id of Jane Doe

SELECT id FROM employees WHERE first_name = 'Jane' AND last_name = 'Doe';

-- Find the name of employees who do not have supervisor (concatenate name)

SELECT CONCAT(first_name, ' ' , last_name) FROM employees WHERE supervisor_id IS NULL;