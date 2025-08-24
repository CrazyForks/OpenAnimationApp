package com.osg.openanimation.preview.tag

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.osg.openanimation.core.ui.dashboard.TagsEditView

@Preview
@Composable
fun TagsEditViewPreview() {
    val tags = remember { mutableStateOf(listOf("tag1", "tag2", "a long tag")) }
    TagsEditView(tags = tags.value, onTagsChange = { tags.value = it })
}