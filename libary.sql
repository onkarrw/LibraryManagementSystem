create database CentralLibrary;
use CentralLibrary;


	CREATE TABLE student (
	  student_id INT PRIMARY KEY,
	  student_name VARCHAR(255),
	  student_email VARCHAR(255)
	);

	CREATE TABLE book (
	  book_id INT PRIMARY KEY auto_increment,
	  book_title VARCHAR(255),
	  book_publisher VARCHAR(255),
	  total_books INT,
	  available_books INT
	);

CREATE TABLE library (
  student_id INT,
  book_id INT,
  date_taken DATE,
  FOREIGN KEY (student_id) REFERENCES student(student_id),
  FOREIGN KEY (book_id) REFERENCES book(book_id)
);

CREATE TABLE passwords (
  id INT PRIMARY KEY,
  username VARCHAR(50),
  password VARCHAR(50),
  user_type ENUM('student', 'admin')
);


CREATE TABLE student_passwords (
  username VARCHAR(50) PRIMARY KEY,
  password VARCHAR(50) NOT NULL
);

-- Creating table for admin passwords
CREATE TABLE admin_passwords (
  username VARCHAR(50) PRIMARY KEY,
  password VARCHAR(50) NOT NULL
);

INSERT INTO admin_passwords (username, password)
VALUES ('Onkar2654', 'Onkar@21');
