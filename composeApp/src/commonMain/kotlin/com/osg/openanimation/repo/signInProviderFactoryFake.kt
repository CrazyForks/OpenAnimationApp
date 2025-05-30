package com.osg.openanimation.repo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.osg.core.ui.components.signin.SignInIdentifier
import com.osg.core.ui.components.signin.SignInProvider
import com.osg.core.ui.components.signin.SignInResult
import com.osg.core.ui.di.SignInProviderFactory


class GoogleSignInSim(
    private val result: Result<SignInResult> = Result.success(SignInResult(uid = TEST_USER_UID_PREFIX))
) : SignInProvider {
    override val identifier: SignInIdentifier = SignInIdentifier.Google

    @Composable
    override fun SignInDialog(onComplete: (Result<SignInResult>) -> Unit) {
        LaunchedEffect(Unit) {
            RepositoryFakeStateFlow.uidState.value = result.getOrThrow().uid
            onComplete(
                result
            )
        }
    }
}

class SignInProviderSim(
    val result: Result<SignInResult> = Result.success(SignInResult(uid = "testUid")),
) : SignInProviderFactory {
    override fun buildSignInProviders(): List<SignInProvider> {

        return listOf(
            GoogleSignInSim(result)
        )
    }
}