# 📋 Weekly Project Progress Report — Week 06
### 🛡️ QR-Based Entry System (VAMS)
**Department of Artificial Intelligence & Data Science**  
**Arya College of Engineering & Information Technology, Jaipur** *(RTU Kota)*  

---

## 📌 Milestone Overview
* **Week Number:** `Week 06`
* **Timeline Duration:** `10 August 2026 – 15 August 2026`
* **Sprint Theme:** **Visitor Access Request Lifecycle, Manager Approval Workflow & Guard Station Requests**
* **Lead Contributor(s):** **Ansh Soni & Ashish Kumar Swami**
* **Project Guide & Mentor:** **Er. Ram Babu Buri** (*Assistant Professor, Dept. of AI & DS*)
* **Team Details:** **Prime Vectors** (Team ID: `PBL2627-AI&DS-A-011`)


---

## 👥 Member Contribution Breakdown

| Team Member | Roll No. | Email ID | Specific Work & Engineering Modules Delivered |
| :--- | :---: | :---: | :--- |
| **Ansh Soni (Leader)** | `24EARAD016` | [`ansh10soni@gmail.com`](mailto:ansh10soni@gmail.com) | Built GuestController and ManagerController handling pass requests, time validity bounds, and manager approval/rejection endpoints. |
| **Ashish Kumar Swami** | `24EARAD024` | [`ashishswami227@gmail.com`](mailto:ashishswami227@gmail.com) | Developed guard station request protocol allowing security guards to request check-in at physical gates, requiring manager confirmation. |
| **Anant Kumar Jain** | `24EARAD011` | [`anantkumarjain2006@gmail.com`](mailto:anantkumarjain2006@gmail.com) | Added transactional locking to prevent race conditions during concurrent approval updates. |
| **Bhavesh Sharma** | `24EARAD030` | [`bhavesh9705@gmail.com`](mailto:bhavesh9705@gmail.com) | Designed manager review tables, filter badges (PENDING, APPROVED, REJECTED), and action buttons. |
| **Deepanshu** | `24EARAD036` | [`deepanshu0973@gmail.com`](mailto:deepanshu0973@gmail.com) | Tested access pass state transitions and edge cases (expired requests, past-dated visit requests). |

---

## 📝 University Portal Submission Summary

### 1. Work Completed This Week:
> Implemented full lifecycle of visitor access requests: submission, validation, manager review, and instant status updates. Built Manager Approval Dashboard with approval/rejection modal actions. Developed Guard Station Request workflow enabling physical gate assignment with manager locking. Added real-time notification alerts.

### 2. Problems Faced & Engineering Solutions:
> Handling simultaneous manager decisions on the same pass request; resolved using Spring @Transactional with pessimistic database locking.

### 3. Plan for Next Week:
> Integrate ZXing cryptographic QR engine with HMAC-SHA256 signature verification and in-memory Base64 streaming.

---

## 🔗 Direct GitHub Evidence & Deliverable Links

The following repository artifacts serve as verifiable evidence of the milestone deliverables:

* [Manager Controller](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/java/com/vams/controller/ManagerController.java)
* [Guest Controller](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/java/com/vams/controller/GuestController.java)
* [Manager Dashboard Template](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/resources/templates/manager-dashboard.html)

---

*Report certified and submitted under the supervision of Project Guide Er. Ram Babu Buri.*  
*© 2026 Team Prime Vectors. All rights reserved.*
