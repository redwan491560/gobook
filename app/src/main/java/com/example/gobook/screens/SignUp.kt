package com.example.gobook.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gobook.R
import com.example.gobook.db.Routes
import com.example.gobook.db.Status
import com.example.gobook.viewmodels.AuthViewModel

@Composable
fun SignUp(navController: NavHostController, authViewmodel: AuthViewModel) {

    val context = LocalContext.current
    val authState = authViewmodel.authState.observeAsState()

    var state by remember {
        mutableStateOf("")
    }

    LaunchedEffect(
        authState.value
    ) {
        when (authState.value) {
            is Status.Authenticated -> if (state == "Guest") {
                navController.navigate(Routes.GuestScreen)
            } else {
                navController.navigate(Routes.HostScreen)
            }

            is Status.Error -> Toast.makeText(
                context, (authState.value as Status.Error).message, Toast.LENGTH_SHORT
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
    var retypePassword by remember {
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
    var visibility by remember {
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
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        colors = ButtonDefaults.buttonColors(
                            if (state == "Guest") Color(0xFF9DE22E) else Color(
                                0xFFDAE7C5
                            )
                        ), onClick = {
                            state = "Guest"
                        }, shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Guest", color = Color.Black, fontSize = 15.sp
                        )
                    }
                    OutlinedButton(
                        onClick = {
                            state = "Host"
                        }, colors = ButtonDefaults.buttonColors(
                            if (state == "Host") Color(0xFF9DE22E) else Color(
                                0xFFDAE7C5
                            )
                        ), shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Host", color = Color.Black, fontSize = 15.sp
                        )
                    }
                }




                TextField(
                    value = userName,
                    onValueChange = {
                        userName = it
                    },
                    textStyle = TextStyle(
                        fontSize = 14.sp
                    ),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Username",
                            fontSize = 14.sp,
                        )
                    },
                    modifier = Modifier
                        .height(60.dp)

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

                TextField(value = password,
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
                    },
                    trailingIcon = {
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
                TextField(value = retypePassword,
                    onValueChange = {
                        retypePassword = it
                    },
                    textStyle = TextStyle(
                        fontSize = 14.sp
                    ),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Confirm Password",
                            fontSize = 14.sp,
                        )
                    },
                    trailingIcon = {
                        if (visibility) Image(painter = painterResource(id = R.drawable.visibility_off_24px),
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    visibility = !visibility
                                })
                        else Image(painter = painterResource(id = R.drawable.visibility_24px),
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    visibility = !visibility
                                })
                    },
                    visualTransformation = if (visibility) VisualTransformation.None
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
                        authViewmodel.register(
                            email = email, password = password, username = userName, role = state
                        )
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFDAE7C5)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(top = 15.dp)
                ) {
                    Text(
                        text = "Sign up", color = Color.Black, fontSize = 15.sp
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
                    text = "Already have an account?", color = Color.Black, fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Log In", color = Color.Blue, fontSize = 24.sp,

                    modifier = Modifier.clickable {
                        navController.navigate(Routes.LogIn)
                    }, textDecoration = TextDecoration.Underline
                )
            }
        }


    }

}


@Preview(showSystemUi = true)
@Composable
private fun DJf() {
    SignUp(rememberNavController(), viewModel())
}