package com.osg.openanimation.repo

import com.osg.openanimation.core.data.report.ReportSubmission
import com.osg.openanimation.core.ui.di.domain.ReportSubmissionService
import kotlinx.coroutines.delay
import org.koin.core.annotation.Factory
import kotlin.time.Duration.Companion.milliseconds

@Factory
class FakeReportSubmissionService: ReportSubmissionService {
    override suspend fun submit(reportSubmission: ReportSubmission) {
        delay(200.milliseconds)
    }
}