-- Tạo database
CREATE DATABASE IF NOT EXISTS employees_db;
USE employees_db;

-- Tạo bảng departments
CREATE TABLE IF NOT EXISTS departments (
                                           id INT AUTO_INCREMENT PRIMARY KEY,
                                           name VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS employees (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    department_id INT NOT NULL,
    FOREIGN KEY (department_id) REFERENCES departments(id)
    );


INSERT INTO departments (name) VALUES
                                   ('IT'),
                                   ('HR'),
                                   ('Finance'),
                                   ('Marketing'),
                                   ('Sales');

INSERT INTO employees (name, age, salary, department_id) VALUES
                                                             ('Nguyễn Văn An', 28, 15000000, 1),
                                                             ('Trần Thị Bình', 32, 18000000, 1),
                                                             ('Lê Văn Cường', 25, 12000000, 2),
                                                             ('Phạm Thị Dung', 30, 16000000, 2),
                                                             ('Hoàng Văn Em', 35, 20000000, 3),
                                                             ('Vũ Thị Phương', 27, 14000000, 3),
                                                             ('Đặng Văn Giang', 29, 17000000, 4),
                                                             ('Bùi Thị Hoa', 31, 19000000, 4),
                                                             ('Ngô Văn Ích', 26, 13000000, 5),
                                                             ('Đinh Thị Kim', 33, 21000000, 5),
                                                             ('Tôn Văn Nam', 24, 11000000, 1),
                                                             ('Lý Thị Oanh', 29, 16000000, 2),
                                                             ('Đỗ Văn Phúc', 32, 19000000, 3),
                                                             ('Nguyễn Thị Quỳnh', 27, 14500000, 1),
                                                             ('Trần Văn Sơn', 30, 17500000, 2),
                                                             ('Phạm Thị Trang', 26, 13500000, 3),
                                                             ('Lê Văn Hùng', 34, 19500000, 4),
                                                             ('Vũ Thị Lan', 28, 15500000, 5),
                                                             ('Bùi Văn Tài', 31, 18500000, 1),
                                                             ('Nguyễn Thị Xuân', 29, 16500000, 2);
employees_db