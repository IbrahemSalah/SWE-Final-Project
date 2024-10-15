package com.example.SWEFinalProject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.SWEFinalProject.ui.theme.SWEFinalProjectTheme
import com.example.SWEFinalProject.util.createNotificationChannel
import com.example.SWEFinalProject.util.sendNotification
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotificationChannel(this)
        sendNotification(this)

        enableEdgeToEdge()

        setContent {
            setContent {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}