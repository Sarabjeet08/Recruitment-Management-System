
# 🧠 Recruitment Management System (RMS)

The **Recruitment Management System** is a full-stack Java Spring Boot application designed to simplify job recruitment processes for administrators, recruiters, and applicants. It supports job posting, user role management, application processing, resume uploads, and much more.

---

## 📂 Project Structure

```
RecruitmentSystem/
├── src/
│   └── main/
│       ├── java/com/rms/
│       │   ├── controller/
│       │   ├── model/
│       │   ├── service/
│       ├── resources/
│           ├── templates/
│           └── static/
├── uploads/resumes/
├── pom.xml
```

---

## 👤 Roles & Functionalities

### 🔑 Admin
- View all users and jobs.
- Manage job deletion requests.
- View applicants who applied to specific jobs.

### 🧑‍💼 Recruiter
- Post jobs.
- View and manage applicants for their jobs.
- Update profile and password.

### 🧑 Applicant
- Register/login and apply to jobs.
- View job application status.
- Upload and update resume (PDF/DOC/DOCX).
- Update profile and password.

---

## 📌 Key Features

- 🔐 Secure login system (session-based)
- 📝 Resume upload (PDF/Word)
- 📥 Recruiter views applicant resumes
- 📊 Admin can manage jobs & users
- ✅ Status change on applications: `Pending`, `Interview`, `Hired`, `Rejected`
- 📋 Profile feature per role

---

## 🧱 Technologies Used

- **Java 17**, **Spring Boot 3**
- **Thymeleaf**, **Bootstrap**
- **Hibernate (JPA)**, **MySQL**
- **Apache Maven**

---

## 📎 Screenshots

_Screenshots included in `/screenshots` or report document._


---

## 🚀 Running the Project

1. Import as Maven project in IntelliJ or Eclipse.
2. Create MySQL database (use provided SQL script if available).
3. Run the Spring Boot application.
4. Open `http://localhost:8080` in your browser.


---

## 📧 Contact

Feel free to reach out for suggestions or improvements.
