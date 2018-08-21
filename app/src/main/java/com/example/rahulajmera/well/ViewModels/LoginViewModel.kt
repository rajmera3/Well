package com.example.rahulajmera.well.ViewModels

import android.content.Intent
import android.util.Log
import com.example.rahulajmera.well.MainActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.subjects.PublishSubject

class LoginViewModel {

    private lateinit var auth: FirebaseAuth
    public lateinit var firebaseLoginObservable: PublishSubject<Boolean>
    private var loggedIn: Boolean = false

    init {
        auth = FirebaseAuth.getInstance()
        firebaseLoginObservable = PublishSubject.create()

    }

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            e: Task<AuthResult> ->

            if (e.isSuccessful) {
                Log.d("SUCCESS", email)
                loggedIn = true
                firebaseLoginObservable.onNext(loggedIn)
            } else {
                Log.d("LOGIN", "FAILEEEEED")
            }
        }

//        auth.createUserWithEmailAndPassword("hitest@test.com", "hihihi").addOnCompleteListener (this, {
//            e: Task<AuthResult> ->
//            if (e.isSuccessful) {
//                var currentUser: FirebaseUser = auth.currentUser!!
//                database.child("users").child(currentUser.uid).setValue(currentUser)
////                    var intent: Intent = Intent(this, PlaidLink::class.java)
////                    startActivity(intent)
//            }
//        })
    }
}