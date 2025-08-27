package com.osg.openanimation.preview.profile

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.osg.openanimation.core.ui.profile.state.ProfileScreenState
import com.osg.openanimation.core.ui.profile.state.UserProfileUi
import com.osg.openanimation.core.ui.profile.ui.ProfileEditorScreenWrapper

@PreviewScreenSizes
@Composable
fun ProfileEditorPreview() {
    MaterialTheme {
        ProfileEditorScreenWrapper(
            profileScreenState = ProfileScreenState.Success(
                UserProfileUi(
                    displayName = "John Doe",
                    bio = "Software Developer",
                    linkedinUrl = "https://www.linkedin.com/in/johndoe",
                    githubUrl = "https://github.com/johndoe",
                )
            ),
            saveProfile = { /* Save profile logic */ }
        )
    }
}