# 📋 Weekly Project Progress Report — Week 04
### 🛡️ QR-Based Entry System (VAMS)
**Department of Artificial Intelligence & Data Science**  
**Arya College of Engineering & Information Technology, Jaipur** *(RTU Kota)*  

---

## 📌 Milestone Overview
* **Week Number:** `Week 04`
* **Timeline Duration:** `27 July 2026 – 01 August 2026`
* **Sprint Theme:** **JPA Repositories, TiDB Connection Pool & System Architecture Finalization**
* **Lead Contributor(s):** **Anant Kumar Jain & Ashish Kumar Swami**
* **Project Guide & Mentor:** **Er. Ram Babu Buri** (*Assistant Professor, Dept. of AI & DS*)
* **Team Details:** **Prime Vectors** (Team ID: `PBL2627-AI&DS-A-011`)

> [!NOTE] Official Deliverable Submitted This Week
> **Document:** [System Architecture & Database Design](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/docs/System%20Architecture%20and%20Database%20Design.pdf) (`docs/System Architecture and Database Design.pdf`)


---

## 👥 Member Contribution Breakdown

| Team Member | Roll No. | Email ID | Specific Work & Engineering Modules Delivered |
| :--- | :---: | :---: | :--- |
| **Anant Kumar Jain** | `24EARAD011` | [`anantkumarjain2006@gmail.com`](mailto:anantkumarjain2006@gmail.com) | Published finalized System Architecture and Database Design specification; tuned HikariCP high-throughput connection pool for TiDB Serverless; implemented composite indices. |
| **Ashish Kumar Swami** | `24EARAD024` | [`ashishswami227@gmail.com`](mailto:ashishswami227@gmail.com) | Published finalized VAMS SRS document; built GateRepository and GuardStationRequestRepository with custom JPQL queries. |
| **Ansh Soni (Leader)** | `24EARAD016` | [`ansh10soni@gmail.com`](mailto:ansh10soni@gmail.com) | Created DataInitializer.java to seed default administrative accounts (owner, manager, guard1, guard2, guest) and default physical gates. |
| **Bhavesh Sharma** | `24EARAD030` | [`bhavesh9705@gmail.com`](mailto:bhavesh9705@gmail.com) | Built responsive base Thymeleaf templates with navigation header and alert modals. |
| **Deepanshu** | `24EARAD036` | [`deepanshu0973@gmail.com`](mailto:deepanshu0973@gmail.com) | Tested database connection retry logic during transient network disconnects; signed off Sprint 1 retrospective. |

---

## 📝 University Portal Submission Summary

### 1. Work Completed This Week:
> Implemented Spring Data JPA repositories with custom query methods. Published finalized 'VAMS SRS' and 'System Architecture and Database Design' deliverables. Established cloud database connection to TiDB Serverless via HikariCP connection pool. Built DataInitializer to automatically seed multi-role accounts (Owner, Manager, Guards, Guest) and physical checkpoints on startup.

### 2. Problems Faced & Engineering Solutions:
> TiDB Serverless connection idle timeouts dropping inactive TCP sockets; resolved by configuring HikariCP max-lifetime=1200000 and idle-timeout=300000.

### 3. Plan for Next Week:
> Configure Spring Security 6.x FilterChain, BCrypt password hashing (12 rounds), and Role-Based Access Control (RBAC) route guards.

---

## 🔗 Direct GitHub Evidence & Deliverable Links

The following repository artifacts serve as verifiable evidence of the milestone deliverables:

* [System Architecture and Database Design (PDF)](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/docs/System%20Architecture%20and%20Database%20Design.pdf)
* [VAMS SRS Document (PDF)](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/docs/VAMS%20SRS.pdf)
* [DataInitializer Seed Automation](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/java/com/vams/config/DataInitializer.java)
* [JPA Repository Layer](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/java/com/vams/repository/)

---

*Report certified and submitted under the supervision of Project Guide Er. Ram Babu Buri.*  
*© 2026 Team Prime Vectors. All rights reserved.*
