package com.osg.openanimation.preview.upload

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.osg.openanimation.core.data.upload.ModerationStatus
import com.osg.openanimation.core.ui.dashboard.ModerationStatusView

@Preview
@Composable
fun ModerationStatusViewPreview() {
    ModerationStatusView(status = ModerationStatus.APPROVED)
}