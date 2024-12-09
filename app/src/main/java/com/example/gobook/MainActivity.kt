package com.example.gobook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gobook.db.Routes
import com.example.gobook.screens.LogIn
import com.example.gobook.screens.SignUp
import com.example.gobook.screens.guest.BookingScreen
import com.example.gobook.screens.guest.GuestScreen
import com.example.gobook.screens.guest.ManageScreen
import com.example.gobook.screens.guest.NotificationScreen
import com.example.gobook.screens.host.GiveSlotScreen
import com.example.gobook.screens.host.HostScreen
import com.example.gobook.screens.host.MySlots
import com.example.gobook.screens.host.NotificationScreenHost
import com.example.gobook.ui.theme.GoBookTheme
import com.example.gobook.viewmodels.AuthViewModel

import com.example.gobook.viewmodels.MainViewmodel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoBookTheme(
                darkTheme = false, dynamicColor = false
            ) {
                val authViewmodel = viewModel<AuthViewModel>()
                val mainViewmodel = viewModel<MainViewmodel>()
                App(mainViewmodel = mainViewmodel, authViewmodel)


            }
        }
    }
}


@Composable
fun App(mainViewmodel: MainViewmodel, authViewmodel: AuthViewModel) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.LogIn) {

        composable<Routes.LogIn> {
            LogIn(navController, authViewmodel)
        }
        composable<Routes.SignUp> {
            SignUp(navController, authViewmodel)
        }
        composable<Routes.GuestScreen> {
            GuestScreen(navController, mainViewmodel, authViewmodel)
        }
        composable<Routes.HostScreen> {
            HostScreen(navController, mainViewmodel, authViewmodel)
        }
        composable<Routes.BookingScreen> {
            BookingScreen(navController, mainViewmodel, authViewmodel)
        }
        composable<Routes.ManageScreen> {
            ManageScreen(navController, mainViewmodel, authViewmodel)
        }
        composable<Routes.Notification> {
            NotificationScreen(navController, mainViewmodel, authViewmodel)
        }
        composable<Routes.MySlots> {
            MySlots(navController, mainViewmodel, authViewmodel)
        }
        composable<Routes.GiveSlots> {
            GiveSlotScreen(navController, mainViewmodel, authViewmodel)
        }
        composable<Routes.NotificationHost> {
            NotificationScreenHost(navController, authViewmodel)
        }


    }
}