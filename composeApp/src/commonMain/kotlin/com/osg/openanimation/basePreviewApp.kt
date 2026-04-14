package com.osg.openanimation

import com.osg.openanimation.core.ui.di.domain.AppLinkProvider
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.KoinApplication
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single


@Module
@ComponentScan
@Configuration
object PreviewModule

@Single
fun appLinkProvider(): AppLinkProvider = AppLinkProvider("http://localhost:8080")


@KoinApplication
object PreviewApplication