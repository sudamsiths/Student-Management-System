# Postman Guide: How to Send POST Request to Add Students

## Base URL: `http://localhost:8081`

## Student POST Request Details

### **Method:** POST  
### **URL:** `http://localhost:8081/students/add`

### **Headers:**
```
Content-Type: application/json
```

## Sample POST Requests:

### 1. Add Student WITHOUT Courses
**Body (JSON):**
```json
{
    "name": "Alice Student",
    "email": "alice@example.com",
    "password": "student123"
}
```

**Expected Response:**
```json
{
    "id": 1,
    "name": "Alice Student",
    "email": "alice@example.com",
    "password": "student123",
    "courseIds": null
}
```

### 2. Add Student WITH Courses
**Body (JSON):**
```json
{
    "name": "Bob Student",
    "email": "bob@example.com",
    "password": "student123",
    "courseIds": [1, 2]
}
```

**Expected Response:**
```json
{
    "id": 2,
    "name": "Bob Student",
    "email": "bob@example.com",
    "password": "student123",
    "courseIds": [1, 2]
}
```

### 3. Add Student WITH Single Course
**Body (JSON):**
```json
{
    "name": "Charlie Student",
    "email": "charlie@example.com",
    "password": "student123",
    "courseIds": [1]
}
```

## Prerequisites (Setup Required):

### Step 1: Create Courses First
Before adding students with courses, create some courses:

**POST:** `http://localhost:8081/courses/add/course`
```json
{
    "name": "Java Programming",
    "duration": "3 months",
    "startDate": "2025-01-15"
}
```

**POST:** `http://localhost:8081/courses/add/course`
```json
{
    "name": "Spring Boot",
    "duration": "2 months",
    "startDate": "2025-02-01"
}
```

### Step 2: Get Course IDs
**GET:** `http://localhost:8081/courses/api/getAllCourses`

This will return course IDs that you can use in the `courseIds` array.

## Complete Postman Setup Steps:

1. **Open Postman**
2. **Create New Request**
3. **Set Method to POST**
4. **Enter URL:** `http://localhost:8081/students/add`
5. **Add Header:**
   - Key: `Content-Type`
   - Value: `application/json`
6. **Go to Body Tab**
7. **Select 'raw' option**
8. **Select 'JSON' from dropdown**
9. **Paste your JSON body**
10. **Click Send**

## Other Student Endpoints:

### Get All Students
**Method:** GET  
**URL:** `http://localhost:8081/students/all`

### Update Student
**Method:** PUT  
**URL:** `http://localhost:8081/students/update/{id}`  
**Example:** `http://localhost:8081/students/update/1`
**Body:**
```json
{
    "name": "Updated Name",
    "email": "updated@example.com",
    "password": "newpassword",
    "courseIds": [1, 2, 3]
}
```

### Delete Student
**Method:** DELETE  
**URL:** `http://localhost:8081/students/delete/{id}`  
**Example:** `http://localhost:8081/students/delete/1`

## Troubleshooting:

### If courses are null in response:
- Make sure courses exist in database first
- Use correct course IDs in the `courseIds` array
- Check that course IDs are numbers, not strings

### If getting 500 error:
- Check that your MySQL database is running
- Verify database connection in `application.yml`
- Make sure required fields are not null

### Common Issues:
1. **Wrong URL endpoint** - Use `/students/add` not `/student/create`
2. **Missing Content-Type header** - Always include `application/json`
3. **Invalid JSON format** - Validate your JSON before sending
4. **Course doesn't exist** - Create courses first before assigning to students
