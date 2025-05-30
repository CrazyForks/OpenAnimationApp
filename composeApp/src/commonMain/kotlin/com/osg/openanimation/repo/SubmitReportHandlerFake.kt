package com.osg.openanimation.repo

import com.osg.core.ui.di.SubmitReportHandler
import com.osg.openanimation.core.data.report.ReportSubmission
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class SubmitReportHandlerFake: SubmitReportHandler {
    override suspend fun submit(reportSubmission: ReportSubmission) {
        delay(200.milliseconds)
    }
}