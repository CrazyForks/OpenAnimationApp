package com.osg.openanimation

import com.osg.core.ui.BaseApp
import com.osg.openanimation.repo.AnimationDataFetcherFake
import com.osg.openanimation.repo.AnimationMetadataRepositoryFake
import com.osg.openanimation.repo.SignInProviderSim
import com.osg.openanimation.repo.SubmitReportHandlerFake
import com.osg.openanimation.repo.UserRepositoryFake

val basePreviewApp by lazy {
    BaseApp(
        metadataRepository ={
            AnimationMetadataRepositoryFake()
        },
        userRepository = {
            UserRepositoryFake()
        },
        dataFetcher = {
            AnimationDataFetcherFake()
        },
        signInLoader = {
            SignInProviderSim()
        },
        reportHandlerLoader = {
            SubmitReportHandlerFake()
        },
        baseUrl = "http://localhost:8080"
    )
}