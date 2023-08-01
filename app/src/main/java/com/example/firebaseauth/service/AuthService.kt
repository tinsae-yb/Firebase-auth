package com.example.firebaseauth.service

import android.content.Context
import android.content.Intent
import com.example.firebaseauth.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class AuthService {
    private val auth = Firebase.auth

    private fun getCurrentUser(): FirebaseUser? = auth.currentUser
    fun checkIfUserIsLoggedIn(): Boolean {
        return getCurrentUser() != null
    }

    suspend fun signUp(email: String, password: String): AuthResult? {
        return auth.createUserWithEmailAndPassword(email, password).await()
    }


    fun listenToAuthStateChanges(listener: (FirebaseAuth) -> Unit) {
        return auth.addAuthStateListener(listener)
    }

    fun signOut() {
        auth.signOut()
    }

    suspend fun login(email: String, password: String): AuthResult? {
        return auth.signInWithEmailAndPassword(email, password).await()
    }


    suspend fun beginSignInWithGoogle(context: Context): BeginSignInResult? {
        val oneTapClient: SignInClient = Identity.getSignInClient(context)
        val signInRequest = BeginSignInRequest.builder().setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.default_web_client_id))
                .build())

            .build()

        return oneTapClient.beginSignIn(signInRequest).await()
    }

    fun credentialFromIntent(context: Context, data: Intent?): SignInCredential {
        val oneTapClient: SignInClient = Identity.getSignInClient(context)
        return oneTapClient.getSignInCredentialFromIntent(data)
    }

    suspend fun signInWithCredential(credential: SignInCredential): AuthResult? {
        val firebaseCredential = GoogleAuthProvider.getCredential(credential.googleIdToken, null)
        return auth.signInWithCredential(firebaseCredential).await()
    }
}