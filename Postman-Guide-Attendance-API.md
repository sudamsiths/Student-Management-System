# Postman Guide: Student Attendance Management REST API

## Base URL: `http://localhost:8081`

## Step-by-Step Process

### 1. Create Admin First (One Time Setup)
**Method:** POST  
**URL:** `http://localhost:8081/Admin/create`  
**Headers:**
```
Content-Type: application/json
```
**Body (JSON):**
```json
{
    "name": "John Admin",
    "email": "admin@example.com",
    "password": "admin123"
}
```

### 2. Admin Login
**Method:** POST  
**URL:** `http://localhost:8081/Admin/login`  
**Headers:**
```
Content-Type: application/json
```
**Body (JSON):**
```json
{
    "email": "admin@example.com",
    "password": "admin123"
}
```
**Response Example:**
```json
{
    "id": 1,
    "name": "John Admin",
    "email": "admin@example.com"
}
```

### 3. Create Students (One Time Setup)
**Method:** POST  
**URL:** `http://localhost:8081/student/create`  
**Headers:**
```
Content-Type: application/json
```
**Body (JSON):**
```json
{
    "name": "Alice Student",
    "email": "alice@example.com",
    "password": "student123"
}
```

## ATTENDANCE MANAGEMENT

### 4. Mark Single Student Attendance
**Method:** POST  
**URL:** `http://localhost:8081/admin/attendance/mark`  
**Headers:**
```
Content-Type: application/json
```
**Body (JSON):**
```json
{
    "date": "2025-01-15",
    "status": "PRESENT",
    "studentId": 1,
    "adminId": 1
}
```

**Available Status Options:**
- `"PRESENT"`
- `"ABSENT"`
- `"LATE"`
- `"EXCUSED"`

### 5. Mark Bulk Student Attendance (Multiple Students at Once)
**Method:** POST  
**URL:** `http://localhost:8081/admin/attendance/mark-bulk`  
**Headers:**
```
Content-Type: application/json
```
**Body (JSON):**
```json
[
    {
        "date": "2025-01-15",
        "status": "PRESENT",
        "studentId": 1,
        "adminId": 1
    },
    {
        "date": "2025-01-15",
        "status": "ABSENT",
        "studentId": 2,
        "adminId": 1
    },
    {
        "date": "2025-01-15",
        "status": "LATE",
        "studentId": 3,
        "adminId": 1
    },
    {
        "date": "2025-01-15",
        "status": "EXCUSED",
        "studentId": 4,
        "adminId": 1
    }
]
```

### 6. Update Existing Attendance
**Method:** PUT  
**URL:** `http://localhost:8081/admin/attendance/update/{id}`  
*Replace {id} with actual attendance record ID*  
**Example:** `http://localhost:8081/admin/attendance/update/1`  
**Headers:**
```
Content-Type: application/json
```
**Body (JSON):**
```json
{
    "date": "2025-01-15",
    "status": "PRESENT"
}
```

## ATTENDANCE REPORTS

### 7. Get All Attendance Records
**Method:** GET  
**URL:** `http://localhost:8081/admin/attendance/all`

### 8. Get Attendance for Specific Student
**Method:** GET  
**URL:** `http://localhost:8081/admin/attendance/student/{studentId}`  
**Example:** `http://localhost:8081/admin/attendance/student/1`

### 9. Get Attendance by Date
**Method:** GET  
**URL:** `http://localhost:8081/admin/attendance/date/{date}`  
**Example:** `http://localhost:8081/admin/attendance/date/2025-01-15`

### 10. Get Students by Attendance Status
**Method:** GET  
**URL:** `http://localhost:8081/admin/attendance/students-by-status?status={STATUS}&date={DATE}`  
**Example:** `http://localhost:8081/admin/attendance/students-by-status?status=PRESENT&date=2025-01-15`

### 11. Get Attendance Statistics for Student
**Method:** GET  
**URL:** `http://localhost:8081/admin/attendance/statistics/{studentId}`  
**Example:** `http://localhost:8081/admin/attendance/statistics/1`

### 12. Delete Attendance Record
**Method:** DELETE  
**URL:** `http://localhost:8081/admin/attendance/{id}`  
**Example:** `http://localhost:8081/admin/attendance/1`

## Sample Success Responses

### Mark Attendance Response:
```json
{
    "id": 1,
    "date": "2025-01-15",
    "status": "PRESENT",
    "studentId": 1,
    "adminId": 1,
    "studentName": "Alice Student",
    "adminName": "John Admin"
}
```

### Attendance Statistics Response:
```json
{
    "PRESENT": 15,
    "ABSENT": 3,
    "LATE": 2,
    "EXCUSED": 1
}
```

## Quick Testing Steps in Postman:

1. **Create Admin** → **Login Admin** → Note the admin ID
2. **Create Student** → Note the student ID
3. **Mark Attendance** using the IDs from steps 1 & 2
4. **View Results** using the report endpoints

## Important Notes:
- Make sure your MySQL database is running on localhost:3306
- Database `LMSSystem` will be created automatically
- All date formats should be: `"YYYY-MM-DD"`
- Student and Admin IDs are auto-generated when you create them
