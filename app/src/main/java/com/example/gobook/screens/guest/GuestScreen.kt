package com.example.gobook.screens.guest

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gobook.R
import com.example.gobook.db.Routes
import com.example.gobook.db.Status
import com.example.gobook.ui.theme.ComposableDesign
import com.example.gobook.ui.theme.ComposableDesign.Companion.IconDesign
import com.example.gobook.ui.theme.ComposableDesign.Companion.NavigationBarIcon
import com.example.gobook.viewmodels.AuthViewModel
import com.example.gobook.viewmodels.MainViewmodel
import kotlinx.coroutines.launch
import java.util.TimeZone


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GuestScreen(
    navController: NavController, mainViewmodel: MainViewmodel, authViewmodel: AuthViewModel
) {

    val context = LocalContext.current
    val authState = authViewmodel.authState.observeAsState()

    LaunchedEffect(
        authState.value
    ) {
        when (authState.value) {
            is Status.NotAuthenticated -> navController.navigate(Routes.LogIn)

            is Status.Error -> Toast.makeText(
                context, (authState.value as Status.Error).message, Toast.LENGTH_SHORT
            ).show()

            else -> Unit
        }
    }

    var selectedIndex by remember {
        mutableIntStateOf(3)
    }
    val scope = rememberCoroutineScope()

    val currentTimeZone = TimeZone.getDefault().id // Get current time zone
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)



    ModalNavigationDrawer(drawerState = drawerState, gesturesEnabled = true, drawerContent = {
        ModalDrawerSheet(
            drawerShape = RoundedCornerShape(6.dp), modifier = Modifier.padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(250.dp)
                    .padding(5.dp, 10.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(10.dp)
                ) {
                    NavigationBarIcon(
                        title = "Account",
                        image = painterResource(id = R.drawable.account_circle_24px)
                    ) {

                    }
                    NavigationBarIcon(
                        title = "Security", image = painterResource(id = R.drawable.security_24px)
                    ) {

                    }
                    NavigationBarIcon(
                        title = "Settings", image = painterResource(id = R.drawable.settings_24px)
                    ) {

                    }
                    NavigationBarIcon(
                        title = "Log Out", image = painterResource(id = R.drawable.logout_24px)
                    ) {
                        authViewmodel.signOut()
                    }

                }
            }
        }
    }) {

        Scaffold(modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(), bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                IconDesign(
                    img = R.drawable.event_booked, title = "Book", state = selectedIndex == 0
                ) {
                    selectedIndex = 0
                    navController.navigate(Routes.BookingScreen)

                }

                IconDesign(
                    img = R.drawable.notifications_24px,
                    title = "Notify",
                    state = selectedIndex == 1
                ) {
                    selectedIndex = 1
                    navController.navigate(Routes.Notification)
                }
                IconDesign(
                    img = R.drawable.event_manage, title = "Manage", state = selectedIndex == 2
                ) {
                    selectedIndex = 2
                    navController.navigate(Routes.ManageScreen)
                }
                IconDesign(
                    img = R.drawable.home_24px, title = "Home", state = selectedIndex == 3
                ) {
                    selectedIndex = 3
                }
            }

        }) {

            Column(
                modifier = Modifier
                    .systemBarsPadding()
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Column(
                            Modifier
                                .padding(10.dp, 5.dp)
                                .width(250.dp),
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(text = "No of meetings booked", fontSize = 16.sp)
                            Text(text = "10", fontSize = 18.sp, color = Color.Red)
                        }

                    }
                    Image(
                        painter = painterResource(id = R.drawable.account_circle_24px),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Image(imageVector = Icons.Outlined.Menu,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                scope.launch {
                                    drawerState.open()
                                }
                            })

                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Today's Meeting", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(10.dp))


                Column {
                    mainViewmodel.meetList.forEach {
                        ComposableDesign.MeetingCard(item = it) {
                            navController.navigate("")
                        }
                    }
                }

            }

        }

    }


}


@Preview(showSystemUi = true)
@Composable
private fun SD() {
    GuestScreen(navController = rememberNavController(), mainViewmodel = viewModel(), viewModel())
}