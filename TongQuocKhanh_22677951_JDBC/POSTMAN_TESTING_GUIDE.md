# Hướng dẫn Test API trên Postman

## Thông tin cơ bản
- **Base URL**: `http://localhost:8080`
- **API Prefix**: `/api/employees`
- **Full URL**: `http://localhost:8080/api/employees`

## Cấu trúc dữ liệu Employee
```json
{
  "id": 1,
  "name": "Tên nhân viên",
  "salary": 5000.0,
  "departmentID": 1
}
```

---

## 1. CRUD Operations

### 1.1 Tạo Employee mới (POST)
**Method**: `POST`  
**URL**: `http://localhost:8080/api/employees`  
**Headers**: 
```
Content-Type: application/json
```
**Body** (raw JSON):
```json
{
  "name": "Nguyễn Văn A",
  "salary": 5000.0,
  "departmentID": 1
}
```
**Response**: 
```
Employee saved successfully
```

### 1.2 Lấy tất cả Employee (GET)
**Method**: `GET`  
**URL**: `http://localhost:8080/api/employees`  
**Response**:
```json
[
  {
    "id": 1,
    "name": "Nguyễn Văn A",
    "salary": 5000.0,
    "departmentID": 1
  },
  {
    "id": 2,
    "name": "Trần Thị B",
    "salary": 6000.0,
    "departmentID": 2
  }
]
```

### 1.3 Lấy Employee theo ID (GET)
**Method**: `GET`  
**URL**: `http://localhost:8080/api/employees/1`  
**Response**:
```json
{
  "id": 1,
  "name": "Nguyễn Văn A",
  "salary": 5000.0,
  "departmentID": 1
}
```

### 1.4 Cập nhật Employee (PUT)
**Method**: `PUT`  
**URL**: `http://localhost:8080/api/employees/1`  
**Headers**: 
```
Content-Type: application/json
```
**Body** (raw JSON):
```json
{
  "name": "Nguyễn Văn A (Updated)",
  "salary": 5500.0,
  "departmentID": 1
}
```
**Response**: 
```
Employee updated successfully
```

### 1.5 Xóa Employee (DELETE)
**Method**: `DELETE`  
**URL**: `http://localhost:8080/api/employees/1`  
**Response**: 
```
Employee deleted successfully
```

---

## 2. Search Operations

### 2.1 Tìm Employee theo ID
**Method**: `GET`  
**URL**: `http://localhost:8080/api/employees/search/id/1`  
**Response**:
```json
{
  "id": 1,
  "name": "Nguyễn Văn A",
  "salary": 5000.0,
  "departmentID": 1
}
```

### 2.2 Tìm Employee theo tên
**Method**: `GET`  
**URL**: `http://localhost:8080/api/employees/search/name/Nguyễn`  
**Response**:
```json
[
  {
    "id": 1,
    "name": "Nguyễn Văn A",
    "salary": 5000.0,
    "departmentID": 1
  }
]
```

### 2.3 Tìm Employee theo Department ID
**Method**: `GET`  
**URL**: `http://localhost:8080/api/employees/search/department/1`  
**Response**:
```json
[
  {
    "id": 1,
    "name": "Nguyễn Văn A",
    "salary": 5000.0,
    "departmentID": 1
  },
  {
    "id": 3,
    "name": "Lê Văn C",
    "salary": 4500.0,
    "departmentID": 1
  }
]
```

### 2.4 Tìm Employee theo khoảng lương
**Method**: `GET`  
**URL**: `http://localhost:8080/api/employees/search/salary/range?minSalary=4000&maxSalary=6000`  
**Response**:
```json
[
  {
    "id": 1,
    "name": "Nguyễn Văn A",
    "salary": 5000.0,
    "departmentID": 1
  },
  {
    "id": 3,
    "name": "Lê Văn C",
    "salary": 4500.0,
    "departmentID": 1
  }
]
```

### 2.5 Tìm Employee có lương lớn hơn
**Method**: `GET`  
**URL**: `http://localhost:8080/api/employees/search/salary/greater-than/5000`  
**Response**:
```json
[
  {
    "id": 2,
    "name": "Trần Thị B",
    "salary": 6000.0,
    "departmentID": 2
  }
]
```

### 2.6 Tìm Employee có lương nhỏ hơn
**Method**: `GET`  
**URL**: `http://localhost:8080/api/employees/search/salary/less-than/5000`  
**Response**:
```json
[
  {
    "id": 3,
    "name": "Lê Văn C",
    "salary": 4500.0,
    "departmentID": 1
  }
]
```

---

## 3. Các bước thực hiện trên Postman

### Bước 1: Khởi động ứng dụng
```bash
./gradlew bootRun
```
hoặc
```bash
./gradlew.bat bootRun
```

### Bước 2: Tạo Collection mới
1. Mở Postman
2. Click "New" → "Collection"
3. Đặt tên: "Employee API Tests"

### Bước 3: Tạo các Request
1. Click "Add Request" trong Collection
2. Đặt tên request (ví dụ: "Create Employee")
3. Chọn method (GET, POST, PUT, DELETE)
4. Nhập URL
5. Thêm Headers nếu cần
6. Thêm Body nếu cần (cho POST/PUT)

### Bước 4: Test từng endpoint
1. Click "Send" để gửi request
2. Kiểm tra Status Code (200, 201, 404, 500...)
3. Kiểm tra Response body
4. Kiểm tra Response time

---

## 4. Status Codes thường gặp

- **200 OK**: Request thành công
- **201 Created**: Tạo mới thành công
- **404 Not Found**: Không tìm thấy resource
- **400 Bad Request**: Request không hợp lệ
- **500 Internal Server Error**: Lỗi server

---

## 5. Ví dụ test sequence

1. **GET** `/api/employees` - Kiểm tra danh sách ban đầu
2. **POST** `/api/employees` - Tạo employee mới
3. **GET** `/api/employees/{id}` - Kiểm tra employee vừa tạo
4. **PUT** `/api/employees/{id}` - Cập nhật employee
5. **GET** `/api/employees/search/name/{name}` - Tìm kiếm theo tên
6. **DELETE** `/api/employees/{id}` - Xóa employee
7. **GET** `/api/employees/{id}` - Kiểm tra đã xóa (404)

---

## 6. Tips và Lưu ý

- Luôn kiểm tra database có chạy không (MariaDB trên port 3306)
- Đảm bảo database `employee_db` đã được tạo
- Các department ID phải tồn tại trong bảng departments
- Salary phải là số thực (double)
- ID sẽ được tự động tăng khi tạo mới

## 7. Environment Variables (tùy chọn)

Trong Postman, bạn có thể tạo Environment với:
- `base_url`: `http://localhost:8080`
- `api_prefix`: `/api/employees`

Sau đó sử dụng: `{{base_url}}{{api_prefix}}` trong URL
