<h1 align="center">Ai Wallpapers</h1> 

AI Wallpaper is a cross-platform mobile app that delivers intelligently generated wallpapers tailored to your style and mood powered by cutting-edge AI. Whether you're on Android or iOS, enjoy a seamless, consistent experience with stunning visuals and smart customization.

Built using Kotlin Multiplatform, AI Wallpaper offers blazing fast performance and a modern UI across both platforms.


##Demo

<img width="1722" alt="Screenshot 2025-05-31 at 11 06 07 AM" src="https://github.com/user-attachments/assets/12fbf501-a5df-4a12-864e-11ac0283b5a0" />




* `composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
