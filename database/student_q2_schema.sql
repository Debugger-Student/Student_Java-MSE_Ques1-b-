CREATE DATABASE IF NOT EXISTS student_q2_db;
USE student_q2_db;

-- The application creates/updates these tables through JPA.
-- Equivalent logical schema:
CREATE TABLE IF NOT EXISTS students (
    student_id VARCHAR(50) PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    course_name VARCHAR(100) NOT NULL,
    programming_score INT NOT NULL CHECK (programming_score BETWEEN 0 AND 100),
    mathematics_score INT NOT NULL CHECK (mathematics_score BETWEEN 0 AND 100)
);

CREATE TABLE IF NOT EXISTS student_technical_skills (
    student_id VARCHAR(50) NOT NULL,
    skill_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE
);

-- Sample data (optional)
INSERT INTO students VALUES
('S101', 'Aman Verma', 'aman@example.com', 'B.Tech CSIT', 90, 80),
('S102', 'Riya Singh', 'riya@example.com', 'B.Tech CSIT', 45, 88),
('S103', 'Kabir Khan', 'kabir@example.com', 'BCA', 70, 75);

INSERT INTO student_technical_skills VALUES
('S101', 'Java'), ('S101', 'SQL'),
('S102', 'Java'), ('S102', 'Python'),
('S103', 'Python'), ('S103', 'C++');
