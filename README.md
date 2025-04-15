# 📱 VU I-App — NIT3213 Final Assignment

This project is developed as part of the **NIT3213 Android Application Development** course at Victoria University. It demonstrates modern Android development practices including **API integration**, **Jetpack Compose UI**, **Dependency Injection with Hilt**, **Clean Architecture**, and **Unit Testing**.

---

## 📌 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Screens Overview](#screens-overview)
- [Setup Instructions](#setup-instructions)
- [API Information](#api-information)
- [Project Structure](#project-structure)
- [Testing](#testing)
- [Version Control](#version-control)
- [Author](#author)

---

## ✅ Features

- **User Login**: Authenticates students via a provided API endpoint.
- **Dashboard Screen**: Displays a list of entity items returned from the API.
- **Details Screen**: Shows complete details of a selected entity.
- **Dependency Injection**: Configured using Hilt.
- **Navigation**: Jetpack Compose Navigation is used to navigate between screens.
- **Error Handling**: Graceful UI updates on network errors or API failures.

---

## 🛠 Tech Stack

| Technology        | Purpose                          |
|-------------------|----------------------------------|
| Kotlin            | Main programming language        |
| Jetpack Compose   | UI framework                     |
| Retrofit          | Network communication            |
| Gson Converter    | JSON serialization/deserialization |
| Hilt              | Dependency Injection             |
| StateFlow         | Reactive state management        |
| MVVM              | Architecture Pattern             |
| Parcelize         | For passing complex data types   |
| Navigation Compose| Navigation between composables   |
| Gradle            | Build system                     |

---

## 🧱 Architecture

The app follows the **MVVM (Model-View-ViewModel)** architecture with the following layers:

- **Data Layer**: Retrofit-based API integration (`ApiService.kt`)
- **Domain Layer**: Repository abstraction (`AuthRepository.kt`)
- **Presentation Layer**: Composables + ViewModels managing UI state

---

## 🖼 Screens Overview

### 🔐 Login Screen
- Accepts username and password.
- Sends a `POST` request to the appropriate API endpoint (`/sydney/auth` in this case).
- On success, receives a `keypass` and navigates to the Dashboard.

### 📊 Dashboard Screen
- Uses the `keypass` to fetch entities from `/dashboard/{keypass}`.
- Displays each entity’s basic information in a list (`LazyColumn`).
- On item click, navigates to the Details screen.

### 📄 Details Screen
- Displays full entity details including name, culture, domain, symbol, parentage, Roman equivalent, and description.

---

## 🧪 How to Build & Run

### 📦 Prerequisites:
- Android Studio Giraffe/Arctic Fox or later
- Android SDK 17
- Kotlin 1.9+
- Internet connection (for API requests)

### 🚀 Steps to Run:

#### 1. Clone the Repository
```bash
git clone https://github.com/SheikhJubayer88/nit3213FINALPROJECT.git
cd nit3213FINALPROJECT
```
#### 2. Open the Project in Android Studio
- Launch Android Studio
- Click on File → Open
- Navigate to the cloned folder nit3213FINALPROJECT
- Click OK to open the project

#### 3. Sync Gradle & Resolve Dependencies
- Android Studio should automatically sync the project
- If it doesn't, go to File → Sync Project with Gradle Files
- Wait until the Gradle build completes successfully

#### 4. Run the App
- Click on the green Run ▶️ button at the top
- Choose a connected physical device or launch an emulator (e.g., Pixel API 33)
- Wait for the app to install and launch



