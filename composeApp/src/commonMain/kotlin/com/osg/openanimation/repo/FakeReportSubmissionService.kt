package com.osg.openanimation.repo

import com.osg.openanimation.core.data.report.ReportSubmission
import com.osg.openanimation.core.ui.di.ReportSubmissionService
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class FakeReportSubmissionService: ReportSubmissionService {
    override suspend fun submit(reportSubmission: ReportSubmission) {
        delay(200.milliseconds)
    }
}