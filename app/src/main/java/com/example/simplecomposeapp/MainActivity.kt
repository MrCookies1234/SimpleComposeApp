package com.example.simplecomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simplecomposeapp.ui.theme.SimpleComposeAppTheme
import com.example.simplecomposeapp.viewmodel.UserViewModel
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
       ){}
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleComposeAppTheme {
        MainScreen()
    }
}