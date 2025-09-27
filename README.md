# 🎬 Moviq - Movie Discovery App

<div align="center">
<img width="192" height="192" alt="moviq_round" src="https://github.com/user-attachments/assets/08fa4203-a612-4403-b43b-03bb182c2a56" />
  
  **A modern Android movie discovery application built with Jetpack Compose**
  
  [![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/)
  [![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
  [![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpack-compose&logoColor=white)](https://developer.android.com/jetpack/compose)
</div>

---

## 📱 About Moviq

Moviq is a comprehensive movie discovery application that helps users explore, search, and discover their next favorite movies. Built with modern Android development practices, the app features a beautiful UI with smooth animations and an intuitive user experience.

### ✨ Key Features

- 🎯 **Smart Search**: Advanced movie search with real-time suggestions
- 🏠 **Personalized Home**: Curated movie recommendations based on your preferences
- 📋 **Detailed Information**: Comprehensive movie details, cast, and reviews
- 🎨 **Beautiful UI**: Modern design with smooth animations and transitions
- 🔍 **AI-Powered**: Intelligent movie recommendations and search
- 📱 **Responsive Design**: Optimized for all screen sizes

---

## 🏗️ Multi-Module Architecture

This project follows a clean, modular architecture pattern to ensure maintainability, scalability, and separation of concerns.

### 📦 Module Structure

```
MovieApp/
├── app/                          # Main application module
├── core/                         # Core modules
│   ├── androidtest/             # Android testing utilities
│   ├── common/                  # Shared common utilities
│   ├── data/                    # Data layer (repositories, data sources)
│   ├── designsystem/            # UI design system components
│   ├── domain/                  # Domain layer (use cases, entities)
│   ├── model/                   # Data models and entities
│   ├── network/                 # Network layer (API clients, interceptors)
│   ├── testing/                 # Testing utilities and mocks
│   └── ui/                      # Shared UI components
└── feature/                     # Feature modules
    ├── ai/                      # AI-powered features
    ├── detail/                  # Movie detail feature
    ├── home/                    # Home screen feature
    └── search/                  # Search functionality
```

### 🎯 Architecture Benefits

- **Modularity**: Each feature is self-contained and can be developed independently
- **Testability**: Clear separation makes unit testing easier
- **Scalability**: Easy to add new features without affecting existing code
- **Team Collaboration**: Different teams can work on different modules
- **Code Reusability**: Shared components across features

---

## 🎨 Animations & UI Showcase

### 🎬 Featured Animations

<div align="center">
  
**Movie List Animations**

![Home Screen Animation](https://github.com/user-attachments/assets/368820a0-e23e-4f4d-9148-6c39bbacc15c)

**Search List Animation**
<!-- Add GIF: search-animation.gif -->
![Search Animation](https://github.com/user-attachments/assets/5a89f6c2-fe2f-474a-86da-cdc63703cb24)


</div>

### 📱 Screenshots

<div align="center">

<table>
  <tr>
    <td align="center"><b>Home Screen</b></td>
    <td align="center"><b>Search Screen</b></td>
    <td align="center"><b>Movie Detail</b></td>
  </tr>
  <tr>
    <td>
      <img width="270" height="600" alt="home-screen" src="https://github.com/user-attachments/assets/8dbfcbc7-cb76-4927-98a3-6864e944275e" />
    </td>
    <td>
      <img width="270" height="600" alt="search-screen" src="https://github.com/user-attachments/assets/e4d26f30-40fd-4023-b1c4-1bb8dc11664f" />
    </td>
    <td>
      <img width="270" height="600" alt="movie-detail" src="https://github.com/user-attachments/assets/5fdf45d6-e543-419c-a30d-176f16b82808" />
    </td>
  </tr>
</table>

</div>

---

## 🛠️ Technology Stack

### Core Technologies
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM + Clean Architecture
- **Dependency Injection**: Hilt
- **Navigation**: Navigation Compose
- **State Management**: ViewModel + StateFlow

### Libraries & Tools
- **Networking**: Retrofit + OkHttp
- **Image Loading**: Coil
- **Database**: Room (if applicable)
- **Testing**: JUnit, Mockito, Espresso
- **Build System**: Gradle with Kotlin DSL

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- JDK 11 or later
- Android SDK API 21+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/MovieApp.git
   cd MovieApp
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned directory

3. **Sync the project**
   - Android Studio will automatically sync Gradle files
   - Wait for the sync to complete

4. **Run the app**
   - Connect an Android device or start an emulator
   - Click the "Run" button or use `Ctrl+R`

---

## 📈 Performance

- **Fast Startup**: Optimized app launch time
- **Smooth Scrolling**: Efficient list rendering
- **Memory Efficient**: Proper lifecycle management
- **Network Optimized**: Smart caching and request batching

---

### Development Setup
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

---

## 📞 Contact

- **Email**: muhammetemir.erkut@gmail.com
- **LinkedIn**: https://www.linkedin.com/in/muhammetemir-erkut/


