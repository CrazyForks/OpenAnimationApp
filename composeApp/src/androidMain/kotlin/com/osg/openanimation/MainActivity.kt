package com.osg.openanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.osg.core.ui.BaseApp
import com.osg.openanimation.repo.AnimationDataFetcherFake
import com.osg.openanimation.repo.AnimationMetadataRepositoryFake
import com.osg.openanimation.repo.SignInProviderSim
import com.osg.openanimation.repo.UserRepositoryFake

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            basePreviewApp.AppEntry()
        }
    }
}