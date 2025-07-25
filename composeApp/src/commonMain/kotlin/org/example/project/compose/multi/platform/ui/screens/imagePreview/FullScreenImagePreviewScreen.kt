package org.example.project.compose.multi.platform.ui.screens.imagePreview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.backhandler.BackHandler
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.mohamedrejeb.calf.permissions.ExperimentalPermissionsApi
import com.mohamedrejeb.calf.permissions.Permission
import com.mohamedrejeb.calf.permissions.isGranted
import com.mohamedrejeb.calf.permissions.rememberPermissionState
import io.github.vinceglb.filekit.FileKit
import kotlinx.coroutines.launch
import org.example.project.compose.multi.platform.domain.models.Photo
import org.example.project.compose.multi.platform.utils.saveImage

class FullScreenImagePreviewScreen(
    val photo: Photo
) : Screen {

    @OptIn(ExperimentalComposeUiApi::class, ExperimentalPermissionsApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val coroutineScope = rememberCoroutineScope()

        val writePermissionState = rememberPermissionState(
            Permission.WriteStorage
        )

        BackHandler(true) {
            navigator?.pop()
        }

        FullScreenImagePreviewScreenContent(
            photo = photo,
            onButtonClick = {
                if (writePermissionState.status.isGranted) {
                    coroutineScope.launch {
                        FileKit.saveImage(photo.src.original)
                    }
                } else {
                    writePermissionState.launchPermissionRequest()
                }
            }
        )
    }

}