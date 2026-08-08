# 📋 Weekly Project Progress Report — Week 05
### 🛡️ QR-Based Entry System (VAMS)
**Department of Artificial Intelligence & Data Science**  
**Arya College of Engineering & Information Technology, Jaipur** *(RTU Kota)*  

---

## 📌 Milestone Overview
* **Week Number:** `Week 05`
* **Timeline Duration:** `03 August 2026 – 08 August 2026`
* **Sprint Theme:** **Spring Security 6.x Architecture, Role-Based Access Control (RBAC) & Password Hashing**
* **Lead Contributor(s):** **Anant Kumar Jain & Ansh Soni**
* **Project Guide & Mentor:** **Er. Ram Babu Buri** (*Assistant Professor, Dept. of AI & DS*)
* **Team Details:** **Prime Vectors** (Team ID: `PBL2627-AI&DS-A-011`)


---

## 👥 Member Contribution Breakdown

| Team Member | Roll No. | Email ID | Specific Work & Engineering Modules Delivered |
| :--- | :---: | :---: | :--- |
| **Anant Kumar Jain** | `24EARAD011` | [`anantkumarjain2006@gmail.com`](mailto:anantkumarjain2006@gmail.com) | Configured Spring Security 6.x SecurityFilterChain, custom UserDetailsService, BCryptPasswordEncoder (12 rounds), and session fixation protection. |
| **Ansh Soni (Leader)** | `24EARAD016` | [`ansh10soni@gmail.com`](mailto:ansh10soni@gmail.com) | Implemented CustomAuthenticationSuccessHandler for intelligent role-based redirection (/owner/dashboard, /manager/dashboard, /employee/scanner, /guest/dashboard). |
| **Ashish Kumar Swami** | `24EARAD024` | [`ashishswami227@gmail.com`](mailto:ashishswami227@gmail.com) | Enforced gate access policy constraints and route protection for guard checkpoint stations. |
| **Bhavesh Sharma** | `24EARAD030` | [`bhavesh9705@gmail.com`](mailto:bhavesh9705@gmail.com) | Built modern authentication UI in login.html with 1-click quick demo login cards. |
| **Deepanshu** | `24EARAD036` | [`deepanshu0973@gmail.com`](mailto:deepanshu0973@gmail.com) | Executed negative security test suite verifying unauthorized URL traversal prevention (HTTP 403 Forbidden). |

---

## 📝 University Portal Submission Summary

### 1. Work Completed This Week:
> Architected enterprise security layer using Spring Security 6.x. Implemented BCrypt hashing (12 rounds), session management, and CSRF protection. Developed Role-Based Access Control (RBAC) with URL pattern route guards. Implemented CustomAuthenticationSuccessHandler for seamless role-based landing redirection. Added 1-click quick demo login cards to facilitate evaluator testing.

### 2. Problems Faced & Engineering Solutions:
> Preventing authenticated visitors from accessing employee scanning endpoints; solved via granular .requestMatchers('/employee/**').hasAnyRole('EMPLOYEE', 'MANAGER', 'OWNER') rules.

### 3. Plan for Next Week:
> Implement visitor pass request lifecycle, Manager approval workflow, and dynamic status tracking.

---

## 🔗 Direct GitHub Evidence & Deliverable Links

The following repository artifacts serve as verifiable evidence of the milestone deliverables:

* [Spring Security Configuration](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/java/com/vams/security/SecurityConfig.java)
* [Custom User Details Service](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/java/com/vams/security/CustomUserDetailsService.java)
* [Login Page with 1-Click Demo Cards](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/resources/templates/login.html)

---

*Report certified and submitted under the supervision of Project Guide Er. Ram Babu Buri.*  
*© 2026 Team Prime Vectors. All rights reserved.*
