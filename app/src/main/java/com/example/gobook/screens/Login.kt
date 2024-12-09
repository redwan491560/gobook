package com.example.gobook.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gobook.R
import com.example.gobook.db.Routes
import com.example.gobook.db.Status
import com.example.gobook.viewmodels.AuthViewModel
import com.example.gobook.viewmodels.UserType

@Composable
fun LogIn(navController: NavController, authViewmodel: AuthViewModel) {

    val context = LocalContext.current
    val authState = authViewmodel.authState.observeAsState()

    val roleState = authViewmodel.role.observeAsState()
    LaunchedEffect(
        authState.value
    ) {
        when (authState.value) {
            is Status.Authenticated ->

                when (roleState.value) {
                    UserType.Guest -> navController.navigate(Routes.GuestScreen)
                    UserType.Host -> navController.navigate(Routes.HostScreen)
                    else -> Unit
                }

            is Status.Error -> Toast.makeText(
                context,
                (authState.value as Status.Error).message,
                Toast.LENGTH_SHORT
            ).show()

            else -> Unit
        }
    }


    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var userName by remember {
        mutableStateOf("")
    }
    var uID by remember {
        mutableStateOf("")
    }


    var visibility1 by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFD5D8EE))
            )
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .alpha(0.7f)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))

            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 120.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Go Book", fontSize = 24.sp)
                Text(text = "Meet and appoint easy", fontSize = 16.sp)

                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    textStyle = TextStyle(
                        fontSize = 14.sp
                    ),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Email",
                            fontSize = 14.sp,
                        )
                    },
                    modifier = Modifier
                        .height(60.dp)
                        .padding(0.dp, 5.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    shape = RoundedCornerShape(6.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedPlaceholderColor = Color.Black,
                        unfocusedPlaceholderColor = Color.Black,
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Black
                    )
                )

                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    textStyle = TextStyle(
                        fontSize = 14.sp
                    ),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Password",
                            fontSize = 14.sp,
                        )
                    }, trailingIcon = {
                        if (visibility1) Image(painter = painterResource(id = R.drawable.visibility_off_24px),
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    visibility1 = !visibility1
                                })
                        else Image(painter = painterResource(id = R.drawable.visibility_24px),
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    visibility1 = !visibility1
                                })
                    },
                    visualTransformation = if (visibility1) VisualTransformation.None
                    else PasswordVisualTransformation(),

                    modifier = Modifier
                        .height(60.dp)
                        .padding(0.dp, 5.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    shape = RoundedCornerShape(6.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedPlaceholderColor = Color.Black,
                        unfocusedPlaceholderColor = Color.Black,
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Black
                    )
                )

                OutlinedButton(
                    onClick = {
                        authViewmodel.logIn(email = email, password = password)
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFDAE7C5)),
                    shape = RoundedCornerShape(8.dp), modifier = Modifier.padding(top = 15.dp)
                ) {
                    Text(
                        text = "Log In", color = Color.Black,
                        fontSize = 15.sp
                    )
                }
            }


            Spacer(
                modifier = Modifier
                    .width(600.dp)
                    .height(450.dp)
                    .offset(y = 310.dp)
                    .scale(2f, 1f)
                    .clip(RoundedCornerShape(200.dp))
                    .background(Color(0xFFD3F7D7))
                    .align(Alignment.BottomCenter)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account?", color = Color.Black,
                    fontSize = 16.sp,

                    )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Register", color = Color.Blue,
                    fontSize = 24.sp,

                    modifier = Modifier
                        .clickable {
                            navController.navigate(Routes.SignUp)
                        },
                    textDecoration = TextDecoration.Underline
                )
            }
        }


    }

}


@Preview(showSystemUi = true)
@Composable
private fun DJf() {
    LogIn(rememberNavController(), viewModel())
}