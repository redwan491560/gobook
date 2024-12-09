package com.example.gobook.screens.host

import android.annotation.SuppressLint
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
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gobook.R
import com.example.gobook.db.Routes
import com.example.gobook.ui.theme.ComposableDesign
import com.example.gobook.ui.theme.ComposableDesign.Companion.IconDesign
import com.example.gobook.ui.theme.ComposableDesign.Companion.NavigationBarIcon
import com.example.gobook.viewmodels.AuthViewModel
import com.example.gobook.viewmodels.MainViewmodel
import kotlinx.coroutines.launch
import java.lang.AutoCloseable
import java.util.TimeZone

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MySlots(navController: NavController, mainViewmodel: MainViewmodel,authViewmodel: AuthViewModel) {

    var expanded by remember {
        mutableStateOf(false)

    }

    var searchMeet by remember {
        mutableStateOf("")
    }

    val filter = listOf("Guest", "Host")

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
                        title = "Security",
                        image = painterResource(id = R.drawable.security_24px)
                    ) {

                    }
                    NavigationBarIcon(
                        title = "Settings",
                        image = painterResource(id = R.drawable.settings_24px)
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
                    img = R.drawable.event_booked,
                    title = "My Slots",
                    state = selectedIndex == 0
                ) {
                    selectedIndex = 0
                    navController.navigate(Routes.MySlots)
                }

                IconDesign(
                    img = R.drawable.notifications_24px,
                    title = "Notify", state = selectedIndex == 1
                ) {
                    selectedIndex = 1
                    navController.navigate(Routes.NotificationHost)
                }
                IconDesign(
                    img = R.drawable.event_manage,
                    title = "Give Slots",
                    state = selectedIndex == 2
                ) {
                    selectedIndex = 2
                    navController.navigate(Routes.GiveSlots)
                }
                IconDesign(
                    img = R.drawable.home_24px,
                    title = "Home",
                    state = selectedIndex == 3
                ) {
                    selectedIndex = 3
                }
            }

        }) {
            Box(modifier = Modifier.fillMaxSize()) {

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
                        OutlinedTextField(modifier = Modifier.height(50.dp), placeholder = {
                            Text(text = "Search Meet", fontSize = 14.sp)
                        }, value = searchMeet, onValueChange = {
                            searchMeet = it
                        })
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Manage Meeting", fontSize = 20.sp)
                        Column {
                            Text(text = "Filter", fontSize = 16.sp, modifier = Modifier.clickable {
                                expanded = !expanded
                            })
                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))


                    Column {
                        mainViewmodel.slotsList.forEach {
                            ComposableDesign.MySlotsItemCard(item = it) {

                            }
                        }
                    }
                }


            }


        }


    }

}

