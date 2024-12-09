package com.example.gobook.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gobook.db.Routes

class ComposableDesign {
    companion object {

        @Composable
        fun IconDesign(img: Int, title: String, state: Boolean = false, onClick: () -> Unit) {


            Column(verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(5.dp)
                    .clickable {
                        onClick()
                    }) {
                Image(
                    painter = painterResource(id = img),
                    contentDescription = null,
                    Modifier.size(35.dp),
                    colorFilter = if (state) ColorFilter.tint(Color.Blue) else ColorFilter.tint(
                        Color.Black
                    )
                )
                Text(text = title, color = if (state) Color.Blue else Color.Black)
            }
        }


        @Composable
        fun NavigationBarIcon(
            title: String,
            image: Painter? = null,
            icon: ImageVector? = null,
            color: Color = Color.White,
            onClick: () -> Unit
        ) {
            Card(shape = RoundedCornerShape(4.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier.clickable {
                    onClick()
                }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    if (icon == null) {
                        Image(
                            painter = image!!,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(10.dp, 10.dp)
                                .size(20.dp)
                        )
                    } else {
                        Image(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(10.dp, 10.dp)
                                .size(20.dp)
                        )
                    }
                    Text(
                        text = title, fontSize = 14.sp, modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }


        @Composable
        fun MeetingCard(item: Routes.MeetingsItem, onClick: () -> Unit) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clickable {
                        onClick()
                    }, shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 15.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(5f)
                    ) {
                        Text(text = "Host name: ${item.hostName}", fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Start: ${item.startTime}", fontSize = 14.sp)
                    }
                    Column(
                        modifier = Modifier.weight(5f)
                    ) {
                        Text(text = "ID: ${item.hostId}", fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "End: ${item.endTime}", fontSize = 14.sp)
                    }
                }
            }

        }


        @Composable
        fun BookMeetingCard(
            item: Routes.MeetingsItem,
            onBookRequest: () -> Unit,
            onClick: () -> Unit
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clickable {
                        onClick()
                    }, shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(5f)
                    ) {
                        Text(text = "Host name: ${item.hostName}", fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Start: ${item.startTime}", fontSize = 14.sp)
                    }
                    Column(
                        modifier = Modifier.weight(4f)
                    ) {
                        Text(text = "ID: ${item.hostId}", fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "End: ${item.endTime}", fontSize = 14.sp)
                    }
                    OutlinedButton(modifier = Modifier.height(30.dp),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(0.dp),
                        onClick = {
                            onBookRequest()
                        }) {
                        Text(text = "Book")
                    }
                }
            }

        }


        @Composable
        fun ManageMeetingCard(item: Routes.MeetingsItem, onClick: () -> Unit) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clickable {
                        onClick()
                    }, shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(5f)
                    ) {
                        Text(text = "Host name: ${item.hostName}", fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Start: ${item.startTime}", fontSize = 14.sp)
                    }
                    Column(
                        modifier = Modifier.weight(4f)
                    ) {
                        Text(text = "ID: ${item.hostId}", fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "End: ${item.endTime}", fontSize = 14.sp)
                    }
                    Column(
                        modifier = Modifier.weight(2f), verticalArrangement = Arrangement.Center
                    ) {
                        Card(
                            border = BorderStroke(1.dp, Color(0xFF3E5251)),
                            modifier = Modifier.height(25.dp),
                            shape = RoundedCornerShape(6.dp),
                        ) {
                            Text(
                                text = "Upcoming",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(3.dp, 5.dp),
                                fontSize = 13.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(3.dp))

                        OutlinedButton(modifier = Modifier
                            .fillMaxWidth()
                            .height(25.dp),
                            shape = RoundedCornerShape(6.dp),

                            contentPadding = PaddingValues(0.dp),
                            onClick = {

                            }) {
                            Text(text = "Cancel")
                        }

                    }

                }
            }

        }


        @Composable
        fun NotificationCard(item: Routes.MeetingsItem, message: String, onCancel: () -> Unit) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp), shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "${item.hostName} $message", fontSize = 16.sp)

                }
            }

        }


        @Composable
        fun HostMeetingCard(
            item: Routes.MeetingsItem,
            onBookRequest: () -> Unit,
            onClick: () -> Unit
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clickable {
                        onClick()
                    }, shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 15.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(5f)
                    ) {
                        Text(text = "Host ID: ${item.hostId}", fontSize = 16.sp)
                    }
                    Column(
                        modifier = Modifier.weight(4f)
                    ) {
                        Text(text = "End: ${item.endTime}", fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Start: ${item.startTime}", fontSize = 14.sp)
                    }
                }
            }

        }


        @Composable
        fun GiveSlots(
            item: Routes.MeetingsItem,
            onCreate: () -> Unit,
            onClick: () -> Unit
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clickable {
                        onClick()
                    }, shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(5f)
                    ) {
                        Text(text = "Available", fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "From:", fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "To:", fontSize = 14.sp)
                    }
                    Column(
                        modifier = Modifier
                            .weight(4f)
                            .align(Alignment.Top)
                    ) {
                        Text(text = "Date:", fontSize = 16.sp)
                    }

                    OutlinedButton(modifier = Modifier.height(30.dp),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(4.dp),
                        onClick = {
                            onCreate()
                        }) {
                        Text(text = "Create Meet")
                    }
                }
            }

        }

        @Composable
        fun MySlotsItemCard(item: Routes.BookMeetItem, onClick: () -> Unit) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clickable {
                        onClick()
                    }, shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(5f)
                    ) {
                        Text(text = "ID: ${item.hostName}", fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Date: ${item.startTime}", fontSize = 14.sp)
                    }
                    Column(
                        modifier = Modifier.weight(4f)
                    ) {
                        Text(text = "Start: ${item.hostId}", fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "End: ${item.endTime}", fontSize = 14.sp)
                    }
                    Column(
                        modifier = Modifier.weight(2f), verticalArrangement = Arrangement.Center
                    ) {
                        Card(
                            border = BorderStroke(1.dp, Color(0xFF3E5251)),
                            modifier = Modifier.height(25.dp),
                            shape = RoundedCornerShape(6.dp),
                        ) {
                            Text(
                                text = "Upcoming",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(3.dp, 5.dp),
                                fontSize = 13.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(3.dp))

                        OutlinedButton(modifier = Modifier
                            .fillMaxWidth()
                            .height(25.dp),
                            shape = RoundedCornerShape(6.dp),

                            contentPadding = PaddingValues(0.dp),
                            onClick = {

                            }) {
                            Text(text = "Cancel")
                        }

                    }

                }
            }

        }


    }
}

@Preview(showSystemUi = true)
@Composable
fun ID(modifier: Modifier = Modifier) {
    ComposableDesign.MySlotsItemCard(
        item = Routes.BookMeetItem(
            startTime = "12 Am",
            hostId = "12500ff",
            hostName = "Not goven",
            endTime = "12 Am",
            date = "12.23"
        )
    ) {

    }
}