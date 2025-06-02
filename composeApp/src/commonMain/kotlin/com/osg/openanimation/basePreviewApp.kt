package com.osg.openanimation

import com.osg.openanimation.core.ui.BaseApp
import com.osg.openanimation.repo.FakeAnimationContentLoader
import com.osg.openanimation.repo.AnimationMetadataRepositoryFake
import com.osg.openanimation.repo.SignInProviderSim
import com.osg.openanimation.repo.FakeReportSubmissionService
import com.osg.openanimation.repo.FakeUserRepo
import org.koin.core.module.Module

fun getBaseApp(
    platformModules : List<Module> = emptyList()
): BaseApp{
    return BaseApp(
        metadataRepository = {
            AnimationMetadataRepositoryFake()
        },
        userRepository = {
            FakeUserRepo()
        },
        dataFetcher = {
            FakeAnimationContentLoader()
        },
        signInLoader = {
            SignInProviderSim()
        },
        reportHandlerLoader = {
            FakeReportSubmissionService()
        },
        baseUrl = "http://localhost:8080",
        platformModules = platformModules
    )
}