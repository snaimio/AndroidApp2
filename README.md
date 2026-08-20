# Team Coffee Run (Android)

A native Android application built with Kotlin and Jetpack Compose for coordinating and tracking group coffee orders.

---

## Overview

Team Coffee Run simplifies group beverage ordering for teams and study groups. The app allows users to customize individual drink orders across multiple team members (Alex, Jordan, Taylor, and Casey), preview dynamic pricing based on drink size, simulate order preparation with an animated countdown, rate beverages, and view organized daily order history.

---

## Features

- **Multi-Member Pager Navigation**: Full-screen horizontal paging allowing seamless swiping across the Welcome screen and individual member ordering screens.
- **Direct Tab Navigation**: Interactive header badges allowing instant navigation to any specific team member.
- **Beverage Customization & Pricing Engine**:
  - 6 drink options with base pricing (Coffee, Hot Chocolate, Tea, Latte, French Vanilla, Cappuccino).
  - 4 cup sizes with automatic price scaling factors (S: 0.8x, M: 1.0x, L: 1.3x, XL: 1.6x).
  - Granular adjustments for sugar cubes (0–4) and milk shots (0–4).
- **Order Preparation & Feedback Flow**:
  - 3-second animated preparation countdown timer.
  - Interactive 5-point coffee cup rating system.
  - Final order celebration screen upon completing all team orders.
- **Chronological Order History**:
  - Modal bottom sheet displaying past orders organized by date.
  - Detailed breakdown of team member names, selected drinks, customizations, and ratings.

---

## Tech Stack & Architecture

- **Language**: Kotlin
- **UI Toolkit**: Jetpack Compose with Material 3
- **Architecture**: MVVM (Model-View-ViewModel) with State Hoisting
- **State Management**: Kotlin Coroutines & `StateFlow`
- **Minimum SDK**: API 26 (Android 8.0)
- **Target SDK**: API 34 (Android 14)
- **Build Tool**: Gradle Version Catalog (`libs.versions.toml`)

---

## Project Structure

```text
com.sheikhnaim.androidapp2/
├── data/
│   ├── CoffeeOrderItem.kt    # Order data model
│   ├── OrderDay.kt           # Date grouping model for history
│   └── OrderViewModel.kt     # Shared state & business logic
├── ui/
│   ├── theme/                # Material3 color, typography & theme definitions
│   ├── components/           # Reusable Header, Rating, and Timer composables
│   └── screens/              # Welcome, Order, History, and Success screens
└── MainActivity.kt           # App entry point with edge-to-edge Compose configuration
```

---

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/snaimio/AndroidApp2.git
   ```
2. Open the project in **Android Studio** (Ladybug or newer recommended).
3. Ensure your Gradle JDK is configured to **JDK 17** or **JDK 21**.
4. Sync Gradle and run on an Android emulator or physical device running API 26+.

