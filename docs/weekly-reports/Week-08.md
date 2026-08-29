# 📋 Weekly Project Progress Report — Week 08
### 🛡️ QR-Based Entry System (VAMS)
**Department of Artificial Intelligence & Data Science**  
**Arya College of Engineering & Information Technology, Jaipur** *(RTU Kota)*  

---

## 📌 Milestone Overview
* **Week Number:** `Week 08`
* **Timeline Duration:** `24 August 2026 – 29 August 2026`
* **Sprint Theme:** **WebRTC HTML5 Camera Scanner & Checkpoint Terminal Interface**
* **Lead Contributor(s):** **Bhavesh Sharma & Ashish Kumar Swami**
* **Project Guide & Mentor:** **Er. Ram Babu Buri** (*Assistant Professor, Dept. of AI & DS*)
* **Team Details:** **Prime Vectors** (Team ID: `PBL2627-AI&DS-A-011`)


---

## 👥 Member Contribution Breakdown

| Team Member | Roll No. | Email ID | Specific Work & Engineering Modules Delivered |
| :--- | :---: | :---: | :--- |
| **Bhavesh Sharma** | `24EARAD030` | [`bhavesh9705@gmail.com`](mailto:bhavesh9705@gmail.com) | Integrated html5-qrcode.js for real-time WebRTC camera video stream processing; built responsive scanner viewfinder with auto-focus and torch toggles. |
| **Ashish Kumar Swami** | `24EARAD024` | [`ashishswami227@gmail.com`](mailto:ashishswami227@gmail.com) | Implemented checkpoint mode selection (ENTRY vs. EXIT) and station locking in ScannerController.java. |
| **Anant Kumar Jain** | `24EARAD011` | [`anantkumarjain2006@gmail.com`](mailto:anantkumarjain2006@gmail.com) | Built sub-50ms REST API /api/scan for asynchronous scan verification and response rendering. |
| **Ansh Soni (Leader)** | `24EARAD016` | [`ansh10soni@gmail.com`](mailto:ansh10soni@gmail.com) | Integrated visual feedback cues (emerald green for granted, crimson red for rejected) and audio beeps. |
| **Deepanshu** | `24EARAD036` | [`deepanshu0973@gmail.com`](mailto:deepanshu0973@gmail.com) | Tested scanner performance across mobile Android Chrome, iOS Safari, and desktop webcams under various lighting conditions. |

---

## 📝 University Portal Submission Summary

### 1. Work Completed This Week:
> Engineered client-side touchless QR scanner using HTML5 WebRTC and html5-qrcode.js. Developed asynchronous scanning endpoint /api/scan returning instant JSON validation telemetry. Integrated real-time audio-visual feedback indicators (pass approved vs pass denied). Optimized camera frame sampling rate to achieve sub-50ms decode latency.

### 2. Problems Faced & Engineering Solutions:
> Cross-browser camera permission policies on mobile browsers requiring secure HTTPS origin; configured self-signed SSL for local mobile testing.

### 3. Plan for Next Week:
> Develop anti-passback state machine algorithm and dynamic facility occupancy counter.

---

## 🔗 Direct GitHub Evidence & Deliverable Links

The following repository artifacts serve as verifiable evidence of the milestone deliverables:

* [Touchless Camera Scanner Interface](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/resources/templates/scanner.html)
* [Checkpoint Scanner Controller](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/java/com/vams/controller/ScannerController.java)

---

*Report certified and submitted under the supervision of Project Guide Er. Ram Babu Buri.*  
*© 2026 Team Prime Vectors. All rights reserved.*
