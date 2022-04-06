package com.example.simplecomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.simplecomposeapp.model.User
import com.example.simplecomposeapp.ui.theme.SimpleComposeAppTheme
import com.example.simplecomposeapp.viewmodel.UserViewModel
import com.valentinilk.shimmer.shimmer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(userViewModel: UserViewModel = hiltViewModel()) {

    val users by userViewModel.users.observeAsState(arrayListOf())
    val isLoading by userViewModel.isLoading.observeAsState(false)

       Scaffold(
           topBar = {
               TopAppBar(
                   title={Text("Simple Compose App")},
                   actions={
                       IconButton(onClick = {
                           userViewModel.addUser()
                       }) {
                           Icon(Icons.Filled.Add,"Add")
                       }
                   }
               )
           }
       ){
           LazyColumn {
               var itemCount = users.size
               if (isLoading) itemCount++

               items(count = itemCount) { index ->
                   var auxIndex = index;
                   if (isLoading) {
                       if (auxIndex == 0)
                           return@items LoadingCard()
                       auxIndex--
                   }
                   val user = users[auxIndex]
                   Card(
                       shape = RoundedCornerShape(8.dp),
                       elevation = 1.dp,
                       modifier = Modifier
                           .padding(horizontal = 8.dp, vertical = 4.dp)
                           .fillMaxWidth(),
                   ) {
                       Row(modifier = Modifier.padding(8.dp)) {
                           Image(
                               modifier = Modifier.size(50.dp),
                               painter = rememberImagePainter(
                                   data = user.thumbnail,
                                   builder = {
                                       placeholder(R.drawable.placeholder)
                                       error(R.drawable.placeholder)
                                   }
                               ),
                               contentDescription = null,
                               contentScale = ContentScale.FillHeight
                           )
                           Spacer()
                           Column(
                               Modifier.weight(1f),
                           ) {
                               Text("${user.name} ${user.lastName}")
                               Text(user.city)
                           }
                           Spacer()
                           IconButton(onClick = {
                               userViewModel.deleteUser(user)
                           }) {
                               Icon(Icons.Filled.Delete, "Remove")
                           }
                       }
                   }
               }
           }
       }
}

@Composable
fun LoadingCard() {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 1.dp,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .testTag("loadingCard")
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            ImageLoading()
            Spacer()
            Column {
                Spacer()
                Box(modifier = Modifier.shimmer()) {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Gray)
                        )
                        Spacer()
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Gray)
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun ImageLoading() {
    Box(modifier = Modifier.shimmer()) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Gray)
        )
    }
}

@Composable
fun Spacer(size: Int = 8) = Spacer(modifier = Modifier.size(size.dp))

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleComposeAppTheme {
        MainScreen()
    }
}