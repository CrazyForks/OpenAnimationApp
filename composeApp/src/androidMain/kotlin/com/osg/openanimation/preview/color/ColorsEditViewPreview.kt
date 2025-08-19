package com.osg.openanimation.preview.color

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.osg.openanimation.core.ui.color.ColorsEditView
import com.osg.openanimation.repo.AnimationDataCollection
import com.osg.openanimation.repo.fromLocaleStorage
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ColorsEditViewPreview() {

    var lottieRaw by remember {
        mutableStateOf<String?>(null)
    }

    Scaffold(
        modifier = androidx.compose.ui.Modifier
            .background(color = androidx.compose.ui.graphics.Color.White)
            .fillMaxWidth()
            .height(400.dp)
    ){
        Column {
            ColorsEditView(
                modifier = androidx.compose.ui.Modifier.padding(it),
                lottieRaw = lottieRaw?: return@Column,
                hash = AnimationDataCollection.CHECKMARK.metadata.hash
            )
        }

    }


    LaunchedEffect(Unit) {
        lottieRaw = AnimationDataCollection.CHECKMARK.fromLocaleStorage()
    }

}