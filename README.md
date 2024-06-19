# Kotlin Multiplatform Notes App

[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This is a basic Kotlin Multiplatform notes app for Android and iOS. 
It includes shared business logic and data handling, and a shared UI implementation using Compose Multiplatform.

### Technologies

The app uses the following multiplatform dependencies in its implementation:

- [Compose Multiplatform](https://jb.gg/compose) for shared UI between platforms
- [Koin](https://github.com/InsertKoinIO/koin) for dependency injection
- [Voyager](https://github.com/adrielcafe/voyager) for navigation and screen models
- [Room](https://developer.android.com/jetpack/androidx/releases/room) for persistence data
