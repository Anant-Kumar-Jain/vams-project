# 📋 Weekly Project Progress Report — Week 02
### 🛡️ QR-Based Entry System (VAMS)
**Department of Artificial Intelligence & Data Science**  
**Arya College of Engineering & Information Technology, Jaipur** *(RTU Kota)*  

---

## 📌 Milestone Overview
* **Week Number:** `Week 02`
* **Timeline Duration:** `13 July 2026 – 18 July 2026`
* **Sprint Theme:** **3NF Relational Schema Design, JPA Entity Modeling & Literature Survey**
* **Lead Contributor(s):** **Anant Kumar Jain & Ashish Kumar Swami**
* **Project Guide & Mentor:** **Er. Ram Babu Buri** (*Assistant Professor, Dept. of AI & DS*)
* **Team Details:** **Prime Vectors** (Team ID: `PBL2627-AI&DS-A-011`)


---

## 👥 Member Contribution Breakdown

| Team Member | Roll No. | Email ID | Specific Work & Engineering Modules Delivered |
| :--- | :---: | :---: | :--- |
| **Anant Kumar Jain** | `24EARAD011` | [`anantkumarjain2006@gmail.com`](mailto:anantkumarjain2006@gmail.com) | Designed 3NF relational schema; created User, Role, AccessRequest, and ScanLog Jakarta persistence entity classes with composite indexing. |
| **Ashish Kumar Swami** | `24EARAD024` | [`ashishswami227@gmail.com`](mailto:ashishswami227@gmail.com) | Modeled Gate and GuardStationRequest entities; mapped foreign key constraints and gate operational permissions (ENTRY_ONLY, EXIT_ONLY, etc.). |
| **Ansh Soni (Leader)** | `24EARAD016` | [`ansh10soni@gmail.com`](mailto:ansh10soni@gmail.com) | Configured Spring Data JPA, Hibernate DDL auto-validation, and application-dev/prod profile switching. |
| **Bhavesh Sharma** | `24EARAD030` | [`bhavesh9705@gmail.com`](mailto:bhavesh9705@gmail.com) | Designed responsive layout templates with Tailwind CSS; mapped UI elements to database entity fields. |
| **Deepanshu** | `24EARAD036` | [`deepanshu0973@gmail.com`](mailto:deepanshu0973@gmail.com) | Documented schema data dictionary, nullability constraints, cascade rules, and field character limits. |

---

## 📝 University Portal Submission Summary

### 1. Work Completed This Week:
> Designed 3NF normalized relational database schema with referential integrity constraints across 6 core entities: User, Role, Gate, AccessRequest, ScanLog, and GuardStationRequest. Configured Spring Data JPA entity mappings with Jakarta Persistence annotations. Reviewed literature on digital credential validation and anti-counterfeiting techniques.

### 2. Problems Faced & Engineering Solutions:
> Mapping bidirectional relationships between GuardStationRequest and Gate without circular JSON serialization issues; solved using Jackson @JsonIgnore on parent references.

### 3. Plan for Next Week:
> Draft IEEE 830 Software Requirements Specification (SRS) and construct comprehensive UML structural and behavioral diagrams.

---

## 🔗 Direct GitHub Evidence & Deliverable Links

The following repository artifacts serve as verifiable evidence of the milestone deliverables:

* [JPA Entity Models Directory](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/java/com/vams/model/)
* [User Entity Model](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/java/com/vams/model/User.java)
* [Gate Entity Model](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/java/com/vams/model/Gate.java)
* [AccessRequest Entity Model](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/java/com/vams/model/AccessRequest.java)

---

*Report certified and submitted under the supervision of Project Guide Er. Ram Babu Buri.*  
*© 2026 Team Prime Vectors. All rights reserved.*
