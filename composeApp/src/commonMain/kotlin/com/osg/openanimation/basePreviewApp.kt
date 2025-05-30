package com.osg.openanimation

import com.osg.openanimation.core.ui.BaseApp
import com.osg.openanimation.repo.AnimationContentLoaderFake
import com.osg.openanimation.repo.AnimationMetadataRepositoryFake
import com.osg.openanimation.repo.SignInProviderSim
import com.osg.openanimation.repo.ReportSubmissionServiceFake
import com.osg.openanimation.repo.UserRepositoryFake
import org.koin.core.module.Module

fun getBaseApp(
    platformModules : List<Module> = emptyList()
): BaseApp{
    return BaseApp(
        metadataRepository = {
            AnimationMetadataRepositoryFake()
        },
        userRepository = {
            UserRepositoryFake()
        },
        dataFetcher = {
            AnimationContentLoaderFake()
        },
        signInLoader = {
            SignInProviderSim()
        },
        reportHandlerLoader = {
            ReportSubmissionServiceFake()
        },
        baseUrl = "http://localhost:8080",
        platformModules = platformModules
    )
}