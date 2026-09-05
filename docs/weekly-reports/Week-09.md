# 📋 Weekly Project Progress Report — Week 09
### 🛡️ QR-Based Entry System (VAMS)
**Department of Artificial Intelligence & Data Science**  
**Arya College of Engineering & Information Technology, Jaipur** *(RTU Kota)*  

---

## 📌 Milestone Overview
* **Week Number:** `Week 09`
* **Timeline Duration:** `31 August 2026 – 05 September 2026`
* **Sprint Theme:** **Anti-Passback Core Algorithm, Facility Headcount Tracking & Dual Audit Logs**
* **Lead Contributor(s):** **Anant Kumar Jain & Ashish Kumar Swami**
* **Project Guide & Mentor:** **Er. Ram Babu Buri** (*Assistant Professor, Dept. of AI & DS*)
* **Team Details:** **Prime Vectors** (Team ID: `PBL2627-AI&DS-A-011`)


---

## 👥 Member Contribution Breakdown

| Team Member | Roll No. | Email ID | Specific Work & Engineering Modules Delivered |
| :--- | :---: | :---: | :--- |
| **Anant Kumar Jain** | `24EARAD011` | [`anantkumarjain2006@gmail.com`](mailto:anantkumarjain2006@gmail.com) | Developed ScannerService.java anti-passback state machine; enforced strict physical perimeter logic (ENTRY requires previous EXIT; EXIT requires previous ENTRY; double ENTRY blocked). |
| **Ashish Kumar Swami** | `24EARAD024` | [`ashishswami227@gmail.com`](mailto:ashishswami227@gmail.com) | Implemented mathematical live facility occupancy engine (Sum Entries - Sum Exits) and gate traversal permissions. |
| **Deepanshu** | `24EARAD036` | [`deepanshu0973@gmail.com`](mailto:deepanshu0973@gmail.com) | Implemented dual-stream immutable logging: ScanLog (gate traversals) and SystemLog (administrative changes). |
| **Bhavesh Sharma** | `24EARAD030` | [`bhavesh9705@gmail.com`](mailto:bhavesh9705@gmail.com) | Built real-time animated occupancy badge and scan activity feed on dashboard. |
| **Ansh Soni (Leader)** | `24EARAD016` | [`ansh10soni@gmail.com`](mailto:ansh10soni@gmail.com) | Verified synchronized execution locks preventing concurrent multi-gate race conditions. |

---

## 📝 University Portal Submission Summary

### 1. Work Completed This Week:
> Engineered core Anti-Passback (APB) state machine algorithm within ScannerService to prevent duplicate entry attempts using cloned passes. Implemented real-time facility occupancy headcount tracker based on dynamic net gate movements. Established dual-stream immutable audit logging recording every scan attempt, gate ID, timestamp, and status for forensic verification.

### 2. Problems Faced & Engineering Solutions:
> Edge case where a user leaves the facility through an unmonitored exit causing state desynchronization; added manual supervisor override capability.

### 3. Plan for Next Week:
> Build Owner Super-Admin administration console and dynamic gate policy management.

---

## 🔗 Direct GitHub Evidence & Deliverable Links

The following repository artifacts serve as verifiable evidence of the milestone deliverables:

* [Anti-Passback Core Engine (ScannerService)](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/java/com/vams/service/ScannerService.java)
* [Immutable ScanLog Entity](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/java/com/vams/model/ScanLog.java)
* [Manager Service Occupancy Calculation](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/java/com/vams/service/ManagerService.java)

---

*Report certified and submitted under the supervision of Project Guide Er. Ram Babu Buri.*  
*© 2026 Team Prime Vectors. All rights reserved.*
