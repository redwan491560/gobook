package com.example.gobook.viewmodels

import androidx.lifecycle.ViewModel
import com.example.gobook.db.Routes

class MainViewmodel : ViewModel() {


    val meetList = listOf(
        Routes.MeetingsItem(
            hostId = "rtefdt120",
            startTime = "12.0",
            hostName = "Redwan",
            endTime = "12.0"
        ),
        Routes.MeetingsItem(
            hostId = "rtefdt120",
            startTime = "12.0",
            hostName = "Fardin",
            endTime = "12.0"
        ),
        Routes.MeetingsItem(
            hostId = "rtefdt120",
            startTime = "12.0",
            hostName = "Redwan",
            endTime = "12.0"
        ),
        Routes.MeetingsItem(
            hostId = "rtefdt120",
            startTime = "12.0",
            hostName = "Redwan",
            endTime = "12.0"
        ),

        )

    val slotsList = listOf(
        Routes.BookMeetItem(
            hostId = "rtefdt120",
            startTime = "12.0",
            hostName = "Redwan",
            endTime = "12.0",
            date = ""
        ),
        Routes.BookMeetItem(
            hostId = "rtefdt120",
            startTime = "12.0",
            hostName = "Fardin",
            endTime = "12.0",
            date = ""
        ),
        Routes.BookMeetItem(
            hostId = "rtefdt120",
            startTime = "12.0",
            hostName = "Redwan",
            endTime = "12.0",
            date = ""
        ),
        Routes.BookMeetItem(
            hostId = "rtefdt120",
            startTime = "12.0",
            hostName = "Redwan",
            endTime = "12.0",
            date = ""
        ),

        )


}