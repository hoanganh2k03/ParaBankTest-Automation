# 🏦 Parabank Automation Testing

This project automates test cases for the **Parabank demo banking site** using **Java + Selenium WebDriver + TestNG + Maven**.

---

## 📌 Features Tested

- 🔑 **Login / Logout**
- 💰 **Open New Account** (Checking / Savings)
- 📊 **Account Overview**
- 📄 **Account Details**
- 🔄 **Transfer Funds**
- 🧾 **Pay Bills**
- 🔎 **Find Transactions**
  - By Transaction ID
  - By Date
  - By Date Range
  - By Amount
- 📝 **Update Contact Info**
- 🏦 **Request Loan**

---

## 🏗️ Tech Stack

- **Java 17**
- **Selenium WebDriver**
- **TestNG**
- **Maven**
- **ChromeDriver**
- (Optional) **Allure / Extent Reports**

---

## ▶️ How to Run

### Run all tests
```bash
mvn clean test
```

### Run specific suite
- Right click on `testng.xml` → **Run as TestNG Suite**

### View Test Report
- After execution: `test-output/index.html`

---

## ✅ Sample Test Flow

1. **Login** with valid credentials  
2. Navigate to **Open New Account**  
3. Select **Checking / Savings** and confirm account created  
4. Go to **Transfer Funds**, move money between accounts  
5. Verify transaction in **Find Transactions**  
6. Submit **Pay Bill**  
7. Update profile in **Contact Info**  
8. Apply for a **Loan**

---

## 🌟 Highlights

- End-to-end coverage of Parabank’s core features  
- TestNG annotations for better organization  
- Reusable locators & assertions  
- Ready for CI/CD integration  

---

## 📧 Author
- **VŨ QUỐC HOÀNG ANH**
- GitHub:https://github.com/hoanganh2k03
- Email: vuquochoanganh2k3@gmail.com
