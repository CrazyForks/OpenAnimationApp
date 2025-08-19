package com.osg.openanimation.preview.color

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osg.openanimation.core.ui.color.model.ColorsEditHandler

class ColorsEditViewModel(
    json: String,
    hash: String,
): ViewModel(){
    val handler = ColorsEditHandler(
        animationContentLoader = {
            json
        },
        scope = viewModelScope,
        path = hash,
    )
    val uiState = handler.uiState

    fun onSelectColorTransformOption(index: Int) = handler.onSelectColorTransformOption(index)
}