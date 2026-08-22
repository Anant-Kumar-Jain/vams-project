# 📋 Weekly Project Progress Report — Week 07
### 🛡️ QR-Based Entry System (VAMS)
**Department of Artificial Intelligence & Data Science**  
**Arya College of Engineering & Information Technology, Jaipur** *(RTU Kota)*  

---

## 📌 Milestone Overview
* **Week Number:** `Week 07`
* **Timeline Duration:** `17 August 2026 – 22 August 2026`
* **Sprint Theme:** **ZXing Cryptographic QR Code Engine & In-Memory Base64 Streaming**
* **Lead Contributor(s):** **Anant Kumar Jain & Bhavesh Sharma**
* **Project Guide & Mentor:** **Er. Ram Babu Buri** (*Assistant Professor, Dept. of AI & DS*)
* **Team Details:** **Prime Vectors** (Team ID: `PBL2627-AI&DS-A-011`)


---

## 👥 Member Contribution Breakdown

| Team Member | Roll No. | Email ID | Specific Work & Engineering Modules Delivered |
| :--- | :---: | :---: | :--- |
| **Anant Kumar Jain** | `24EARAD011` | [`anantkumarjain2006@gmail.com`](mailto:anantkumarjain2006@gmail.com) | Developed QrCodeService.java utilizing Google ZXing; built encrypted payload format with pass ID, user ID, cryptographic nonce, expiration timestamp, and HMAC-SHA256 digital signature. |
| **Bhavesh Sharma** | `24EARAD030` | [`bhavesh9705@gmail.com`](mailto:bhavesh9705@gmail.com) | Implemented in-memory streaming of PNG byte arrays directly into Base64 data:image/png;base64,... data URIs in Thymeleaf, eliminating sensitive image files on disk. |
| **Ansh Soni (Leader)** | `24EARAD016` | [`ansh10soni@gmail.com`](mailto:ansh10soni@gmail.com) | Added dynamic QR passport pass badge in guest-dashboard.html with countdown timer. |
| **Ashish Kumar Swami** | `24EARAD024` | [`ashishswami227@gmail.com`](mailto:ashishswami227@gmail.com) | Formulated pass expiration rules and active time-window validation. |
| **Deepanshu** | `24EARAD036` | [`deepanshu0973@gmail.com`](mailto:deepanshu0973@gmail.com) | Tested cryptographic payload tampering resistance (modifying payload bytes causes instant validation rejection). |

---

## 📝 University Portal Submission Summary

### 1. Work Completed This Week:
> Integrated Google ZXing 3.5.3 library for cryptographic QR code generation. Engineered tamper-proof payload structure combining pass ID, user ID, epoch expiration timestamp, nonce, and HMAC-SHA256 signature. Implemented zero-disk-leak in-memory Base64 PNG rendering directly within Thymeleaf templates. Designed responsive visitor digital pass badge.

### 2. Problems Faced & Engineering Solutions:
> Preventing QR code screenshot re-use; mitigated by embedding dynamic expiration countdowns and cryptographic nonce validation.

### 3. Plan for Next Week:
> Build front-facing WebRTC HTML5 camera barcode scanner interface for checkpoint terminals.

---

## 🔗 Direct GitHub Evidence & Deliverable Links

The following repository artifacts serve as verifiable evidence of the milestone deliverables:

* [Cryptographic QR Code Service](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/java/com/vams/service/QrCodeService.java)
* [Guest Dashboard Pass Badge](https://github.com/Anant-Kumar-Jain/vams-project/blob/main/src/main/resources/templates/guest-dashboard.html)

---

*Report certified and submitted under the supervision of Project Guide Er. Ram Babu Buri.*  
*© 2026 Team Prime Vectors. All rights reserved.*
