package org.example.project.compose.multi.platform

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIColor
import platform.UIKit.UIRectEdgeAll


fun MainViewController() = ComposeUIViewController {
    val controller = ComposeUIViewController { App() }

    // Tell iOS to extend content behind system bars
    controller.edgesForExtendedLayout = UIRectEdgeAll
    controller.extendedLayoutIncludesOpaqueBars = true
    // Optional: make background transparent so Compose controls the whole background
    controller.view.backgroundColor = UIColor.clearColor

    controller
}
