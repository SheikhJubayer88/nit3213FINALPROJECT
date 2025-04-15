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


