package com.example.gobook.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gobook.db.Status
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel : ViewModel() {

    private val _role = MutableLiveData<UserType>()
    val role: LiveData<UserType> = _role

    private val _authState = MutableLiveData<Status>()
    val authState: LiveData<Status> = _authState

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val databaseRef = FirebaseDatabase.getInstance().reference

    private val user = auth.currentUser

    private val authStateListener = FirebaseAuth.AuthStateListener { checkAuthenticationStatus() }

    init {
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener)
        checkAuthenticationStatus()
    }

    override fun onCleared() {
        super.onCleared()
        FirebaseAuth.getInstance().removeAuthStateListener(authStateListener)
    }

    private fun checkAuthenticationStatus() {
        _authState.value = if (auth.currentUser == null) {
            Status.NotAuthenticated
        } else {
            Status.Authenticated
        }
    }

    fun logIn(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _authState.value = Status.Error("Email and password cannot be empty")
            return
        }
        _authState.value = Status.Loading
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                user?.let {
                    db.collection("users").document(it.uid).get()
                        .addOnSuccessListener { document ->
                            if (document.exists()) {
                                val userData = document.data
                                val storedEmail = userData?.get("email") as? String
                                val storedRole = userData?.get("role") as? String ?: "None"

                                if (storedEmail == email) {
                                    _authState.value = Status.Authenticated
                                    _role.value = if (storedRole == "Guest") UserType.Guest else UserType.Host
                                } else {
                                    _authState.value = Status.Error("User data does not match")
                                }
                            } else {
                                _authState.value = Status.Error("User document does not exist")
                            }
                        }
                        .addOnFailureListener { exception ->
                            _authState.value = Status.Error("Failed to retrieve user data: ${exception.message}")
                        }
                }
            } else {
                _authState.value = Status.Error(task.exception?.message ?: "Login failed")
            }
        }
    }

    fun register(email: String, password: String, username: String, role: String) {
        if (email.isBlank() || password.isBlank() || username.isBlank()) {
            _authState.value = Status.Error("Email, password, and username cannot be empty")
            return
        }
        _authState.value = Status.Loading
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                user?.let {
                    val userData = mapOf(
                        "email" to email,
                        "username" to username,
                        "role" to role
                    )
                    db.collection("users").document(it.uid).set(userData)
                        .addOnSuccessListener {
                            _authState.value = Status.Authenticated
                        }
                        .addOnFailureListener { exception ->
                            _authState.value = Status.Error("Failed to store user data: ${exception.message}")
                        }
                }
            } else {
                _authState.value = Status.Error(task.exception?.message ?: "Registration failed")
            }
        }
    }

    fun signOut() {
        auth.signOut()
        _authState.value = Status.NotAuthenticated
    }


    fun onClick(date: String, timeSlots:List<String>) {
        val user = auth.currentUser
        user?.let { currentUser ->
            db.collection("users").document(currentUser.uid).get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val name = document.getString("username") ?: "Unknown"
                        val email = document.getString("email") ?: "Unknown"

                        val userMap = mapOf(
                            "name" to name,
                            "email" to email
                        )

                        databaseRef.child(currentUser.uid).updateChildren(userMap)
                            .addOnSuccessListener {
                                println("User details added under UID: ${currentUser.uid}")

                                val timeSlotMap = timeSlots.withIndex().associate { (index, slot) ->
                                    "TimeSlot${index + 1}" to slot
                                }

                                databaseRef.child(currentUser.uid).child(date).setValue(timeSlotMap)
                                    .addOnSuccessListener {
                                        println("Time slots successfully added for date: $date")
                                    }
                                    .addOnFailureListener { exception ->
                                        println("Failed to add time slots: ${exception.message}")
                                    }
                            }
                            .addOnFailureListener { exception ->
                                println("Failed to add user details: ${exception.message}")
                            }
                    } else {
                        println("User document not found in Firestore.")
                    }
                }
                .addOnFailureListener { exception ->
                    println("Failed to fetch user details: ${exception.message}")
                }
        } ?: println("User is not authenticated.")
    }
}

data class User(
    val username: String = "",
    val email: String = "",
    val role: String = ""
)

enum class UserType {
    Guest, Host
}
