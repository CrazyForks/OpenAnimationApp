package com.osg.openanimation.repo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.osg.openanimation.core.ui.components.signin.SignInIdentifier
import com.osg.openanimation.core.ui.components.signin.SignInProvider
import com.osg.openanimation.core.ui.components.signin.SignInResult
import com.osg.openanimation.core.ui.di.domain.SignInProviderFactory
import org.koin.core.annotation.Factory

class GoogleSignInSim(
    private val result: Result<SignInResult>
) : SignInProvider {
    override val identifier: SignInIdentifier = SignInIdentifier.Google

    @Composable
    override fun SignInDialog(onComplete: (Result<SignInResult>) -> Unit) {
        LaunchedEffect(Unit) {
            FakeRepositoryState.uidState.value = result.getOrThrow().uid
            onComplete(
                result
            )
        }
    }
}

@Factory
class SignInProviderSim() : SignInProviderFactory {
    override fun buildSignInProviders(): List<SignInProvider> {
        val result: Result<SignInResult> = Result.success(SignInResult(uid = "testUid"))
        return listOf(
            GoogleSignInSim(result)
        )
    }
}