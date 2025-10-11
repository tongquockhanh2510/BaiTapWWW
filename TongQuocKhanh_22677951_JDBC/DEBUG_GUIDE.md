# Hướng dẫn Debug - Thêm và Sửa Nhân viên

## Các bước kiểm tra

### 1. Kiểm tra Database
- Đảm bảo MariaDB đang chạy
- Kiểm tra database `employee_db` đã được tạo
- Kiểm tra bảng `departments` và `employees` đã có dữ liệu

### 2. Kiểm tra Logs
Khi chạy ứng dụng, xem console logs:
```bash
./gradlew bootRun
```

### 3. Test thêm nhân viên
1. Truy cập: http://localhost:8080/employees/new
2. Điền form:
   - Tên: "Test Employee"
   - Tuổi: 25
   - Lương: 1000000
   - Phòng ban: Chọn một phòng ban
3. Submit form
4. Xem logs trong console

### 4. Test sửa nhân viên
1. Truy cập: http://localhost:8080/employees
2. Click "Sửa" trên một nhân viên
3. Thay đổi thông tin
4. Submit form
5. Xem logs trong console

### 5. Test API trực tiếp
Truy cập: http://localhost:8080/test
- Nếu thành công: "Test successful! Employee saved."
- Nếu lỗi: Sẽ hiển thị thông báo lỗi

## Các lỗi thường gặp

### Lỗi Database Connection
```
Error: Could not create connection to database
```
**Giải pháp**: Kiểm tra MariaDB đang chạy và thông tin kết nối trong `application.properties`

### Lỗi Foreign Key
```
Error: Cannot add or update a child row: a foreign key constraint fails
```
**Giải pháp**: Đảm bảo `department_id` tồn tại trong bảng `departments`

### Lỗi Form Binding
```
Error: Required request parameter 'name' is not present
```
**Giải pháp**: Kiểm tra form có đúng field names không

## Debug Steps

1. **Kiểm tra Console Logs**: Xem có lỗi gì không
2. **Kiểm tra Database**: Truy cập MariaDB và xem dữ liệu
3. **Test API**: Sử dụng endpoint `/test` để kiểm tra
4. **Kiểm tra Form**: Đảm bảo form submit đúng dữ liệu
