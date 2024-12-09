package com.example.gobook.db

import kotlinx.serialization.Serializable


sealed class Routes {

    @Serializable
    object HostScreen


    @Serializable
    object GuestScreen

    @Serializable
    object LogIn

    @Serializable
    object SignUp

    @Serializable
    object BookingScreen


    @Serializable
    object ManageScreen

    @Serializable
    object Notification


    @Serializable
    object GiveSlots

    @Serializable
    object MySlots

    @Serializable
    object NotificationHost

    @Serializable
    data class MeetingsItem(
        val hostName: String, val hostId: String, val startTime: String, val endTime: String
    )

    @Serializable
    data class BookMeetItem(
        val hostName: String,
        val hostId: String,
        val startTime: String,
        val endTime: String,
        val date: String
    )


}


sealed class Status {
    data object Authenticated : Status()
    data object NotAuthenticated : Status()
    data object Loading : Status()
    data class Error(val message: String) : Status()
}


