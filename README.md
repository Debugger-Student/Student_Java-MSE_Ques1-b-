# Student Profile and Skill Management - Q2

## Requirements
- Java 17+, MySQL Server, Maven
- Update `src/main/resources/application.properties` and replace `YOUR_MYSQL_PASSWORD`.
- Run `database/student_q2_schema.sql` in MySQL Workbench to create the database/tables and optional sample data.
- If you execute the sample INSERTs, do not POST the same IDs/emails again.

## Run
From the folder containing `pom.xml`:
```bash
mvn spring-boot:run
```
Base URL: `http://localhost:8080/api/students`

## API endpoints
- `POST /api/students` add student
- `GET /api/students` all students
- `GET /api/students/id/{id}` search by ID
- `GET /api/students/email?email=aman@example.com` search by email
- `GET /api/students/course?course=B.Tech` search by course
- `GET /api/students/merit-list` descending merit list
- `GET /api/students/shared-skills` students grouped by skills shared by 2+ students
- `GET /api/students/programming-needs-improvement` programming < 50 and maths >= 75
- `PUT /api/students/{id}` update
- `DELETE /api/students/{id}` delete

## POST example JSON
```json
{
  "studentId": "S104",
  "studentName": "Neha Sharma",
  "email": "neha@example.com",
  "courseName": "B.Tech CSIT",
  "programmingScore": 82,
  "mathematicsScore": 90,
  "technicalSkills": ["Java", "SQL", "Python"]
}
```

## Business rules
Average = (programmingScore + mathematicsScore) / 2.0.
Category: >=85 Excellent; >=70 Good; >=50 Satisfactory; otherwise Needs Improvement.
Stronger subject compares the two marks; equal marks return "Both are equal".
Average, category and stronger subject are returned as computed JSON properties.
Student ID is the primary key; email is unique; scores are validated 0..100.
