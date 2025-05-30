package com.osg.openanimation.repo

import com.osg.openanimation.core.ui.di.ReportSubmissionService
import com.osg.openanimation.core.data.report.ReportSubmission
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class ReportSubmissionServiceFake: ReportSubmissionService {
    override suspend fun submit(reportSubmission: ReportSubmission) {
        delay(200.milliseconds)
    }
}