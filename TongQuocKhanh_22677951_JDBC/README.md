# Hệ thống Quản lý Nhân viên - JDBC

## Mô tả
Ứng dụng Spring Boot sử dụng JDBC để quản lý nhân viên và phòng ban với giao diện web đơn giản.

## Tính năng

### 1. CRUD Cơ bản
- **Thêm nhân viên mới**: Tạo nhân viên với thông tin tên, tuổi, lương, phòng ban
- **Xem danh sách nhân viên**: Hiển thị tất cả nhân viên trong bảng
- **Sửa thông tin nhân viên**: Cập nhật thông tin nhân viên
- **Xóa nhân viên**: Xóa nhân viên khỏi hệ thống

### 2. Mối quan hệ 1-n (Employee-Department)
- **Xem nhân viên theo phòng ban**: Hiển thị danh sách nhân viên thuộc từng phòng ban
- **Quản lý phòng ban**: Xem danh sách các phòng ban và số nhân viên

### 3. Tìm kiếm nâng cao
- **Tìm kiếm theo tên**: Tìm nhân viên có tên chứa từ khóa
- **Tìm kiếm theo tuổi**: Tìm nhân viên trong khoảng tuổi hoặc lớn/nhỏ hơn tuổi nhất định
- **Tìm kiếm theo lương**: Tìm nhân viên trong khoảng lương hoặc lớn/nhỏ hơn mức lương nhất định
- **Tìm kiếm theo phòng ban**: Tìm nhân viên thuộc phòng ban cụ thể

## Cấu trúc dự án

```
src/main/java/iuh/fit/tongquockhanh_22677951_jdbc/
├── controller/
│   ├── EmployeeController.java    # REST API cho Employee
│   └── WebController.java         # Web Controller cho giao diện
├── dao/
│   ├── EmployeeDAO.java           # Interface DAO cho Employee
│   ├── EmployeeDAOImpl.java       # Implementation DAO cho Employee
│   ├── DepartmentDAO.java         # Interface DAO cho Department
│   └── DepartmentDAOImpl.java     # Implementation DAO cho Department
├── entity/
│   ├── Employee.java              # Entity Employee
│   └── Department.java            # Entity Department
└── service/
    ├── EmployeeService.java       # Service cho Employee
    └── DepartmentService.java     # Service cho Department

src/main/resources/
├── templates/                     # Thymeleaf templates
│   ├── index.html                 # Trang chủ
│   ├── employees.html             # Danh sách nhân viên
│   ├── employee-form.html         # Form thêm/sửa nhân viên
│   ├── search.html                # Trang tìm kiếm
│   ├── search-results.html        # Kết quả tìm kiếm
│   ├── departments.html           # Danh sách phòng ban
│   └── department-employees.html  # Nhân viên theo phòng ban
├── static/css/
│   └── style.css                  # CSS đơn giản
├── schema.sql                     # Script tạo database và dữ liệu mẫu
└── application.properties         # Cấu hình ứng dụng
```

## Cài đặt và chạy

### Yêu cầu
- Java 21
- MariaDB/MySQL
- Gradle

### Cấu hình Database
1. Tạo database `employee_db` trong MariaDB/MySQL
2. Cập nhật thông tin kết nối trong `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mariadb://localhost:3306/employee_db
   spring.datasource.username=root
   spring.datasource.password=root
   ```

### Chạy ứng dụng
```bash
./gradlew bootRun
```

Ứng dụng sẽ chạy tại: http://localhost:8080

## Giao diện Web

### Trang chủ (/)
- Hiển thị danh sách tất cả nhân viên
- Navigation menu
- Nút thêm nhân viên mới

### Quản lý Nhân viên (/employees)
- Danh sách nhân viên với thông tin đầy đủ
- Nút sửa/xóa cho từng nhân viên
- Hiển thị tên phòng ban tương ứng

### Thêm/Sửa Nhân viên (/employees/new, /employees/edit/{id})
- Form nhập thông tin nhân viên
- Dropdown chọn phòng ban
- Validation cơ bản

### Tìm kiếm (/search)
- Tìm kiếm theo tên
- Tìm kiếm theo khoảng tuổi
- Tìm kiếm theo khoảng lương
- Tìm kiếm theo phòng ban

### Quản lý Phòng ban (/departments)
- Danh sách các phòng ban
- Xem nhân viên theo phòng ban

## API Endpoints

### REST API cho Employee
- `GET /api/employees` - Lấy tất cả nhân viên
- `GET /api/employees/{id}` - Lấy nhân viên theo ID
- `POST /api/employees` - Tạo nhân viên mới
- `PUT /api/employees/{id}` - Cập nhật nhân viên
- `DELETE /api/employees/{id}` - Xóa nhân viên
- `GET /api/employees/search/name/{name}` - Tìm kiếm theo tên
- `GET /api/employees/search/department/{departmentId}` - Tìm kiếm theo phòng ban
- `GET /api/employees/search/salary/range?minSalary=X&maxSalary=Y` - Tìm kiếm theo khoảng lương

## Dữ liệu mẫu
Ứng dụng tự động tạo dữ liệu mẫu khi khởi động:
- 6 phòng ban: IT, HR, Finance, Marketing, Sales, Operations
- 15 nhân viên mẫu với thông tin đầy đủ

## CSS
Giao diện sử dụng CSS đơn giản với:
- Layout responsive
- Màu sắc hài hòa
- Typography dễ đọc
- Hover effects
- Form styling
- Table styling
