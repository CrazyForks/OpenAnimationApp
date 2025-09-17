package com.osg.openanimation.repo

import com.osg.openanimation.core.data.animation.AnimationMetadata
import openanimationapp.composeapp.generated.resources.Res


enum class AnimationDataCollection(
    val metadata: AnimationMetadata
) {
    CHECKMARK(
        AnimationMetadata(
            name = "Checkmark",
            hash = "125804972",
            localFileName = "os_99592_checkmark_p0.json",
            tags = setOf(
                "checkmark",
                "confirmation",
                "success",
                "tick"
            )
        )
    ),
    AIRPLANE(
        AnimationMetadata(
            name = "Airplane Travel",
            hash = "220165352",
            localFileName = "os_airplane_p0.json",
            tags = setOf(
                "airplane",
                "flight",
                "globe",
                "travel",
                "loop",
                "minimal"
            )
        )
    ),
    SIMPLE_JUMP(
        AnimationMetadata(
            name = "Simple Jump",
            hash = "234928409",
            localFileName = "os_bouncing_p0.json",
            tags = setOf(
                "jump",
                "bounce",
                "character",
                "minimal",
                "motion"
            )
        )
    ),
    DAY_NIGHT_CYCLE(
        AnimationMetadata(
            name = "Day Night Cycle",
            hash = "741231865",
            localFileName = "os_darkmode_p0.json",
            tags = setOf(
                "day",
                "night",
                "sun",
                "moon",
                "clouds",
                "stars",
                "cycle"
            )
        )
    ),
    SURPRISED_FACE(
        AnimationMetadata(
            name = "Surprised Face",
            hash = "-1003861391",
            localFileName = "os_dead_p0.json",
            tags = setOf(
                "face",
                "surprised",
                "emotion",
                "expression"
            )
        )
    ),
    HAPPY_DOG(
        AnimationMetadata(
            name = "Happy Dog",
            hash = "1388726296",
            localFileName = "os_dog_p0.json",
            tags = setOf(
                "dog",
                "happy",
                "cute",
                "pet",
                "white",
                "tail"
            )
        )
    ),
    ORBITING_DOTS(
        AnimationMetadata(
            name = "Orbiting Dots",
            hash = "319645589",
            localFileName = "os_loop_p0_p0.json",
            tags = setOf(
                "loading",
                "orbit",
                "dots",
                "circles",
                "rotation",
                "spinner"
            )
        )
    ),
    WALKING_DOG(
        AnimationMetadata(
            name = "Walking Dog",
            hash = "-963052743",
            localFileName = "os_lottie_example_p0_p0.json",
            tags = setOf(
                "dog",
                "walking",
                "pet",
                "movement",
            )
        )
    ),
    PARTLY_CLOUDY_SUN(
        AnimationMetadata(
            name = "Partly Cloudy Sun",
            hash = "960577",
            localFileName = "os_my_anim_p0.json",
            tags = setOf(
                "sun",
                "cloud",
                "partly cloudy",
                "weather",
                "daytime"
            )
        )
    ),
    LOADING_EMOJI(
        AnimationMetadata(
            name = "Loading Emoji",
            hash = "-20657691543",
            localFileName = "loading_emo.json",
            tags = setOf(
                "loading",
                "emoji",
                "face",
                "expression",
                "waiting"
            )
        )
    ),
    SYNC_DOTS(
        AnimationMetadata(
            name = "Sync Dots",
            hash = "1140977817",
            localFileName = "os_pageLoad_p0.json",
            tags = setOf(
                "dots",
                "circle",
                "movement",
                "sync",
                "loading",
                "progress"
            )
        )
    );

    companion object {
        fun findByHash(hash: String): AnimationDataCollection {
            return entries.first { it.metadata.hash == hash }
        }

        val metadataList: List<AnimationMetadata>
            get() = entries.map { it.metadata }
    }
}


suspend fun AnimationDataCollection.fromLocaleStorage(): String {
    return Res.readBytes("files/${metadata.localFileName}").decodeToString()
}
