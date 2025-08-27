package com.osg.openanimation

import com.osg.openanimation.core.ui.BaseApp
import org.koin.core.module.Module
import org.koin.ksp.generated.defaultModule


fun getBaseApp(
    platformModules : List<Module> = emptyList()
): BaseApp{
    return BaseApp(
        baseUrl = "http://localhost:8080",
        platformModules = platformModules + defaultModule
    )
}