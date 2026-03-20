package shop.manukindapp.com

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import shop.manukindapp.com.ui.composable.approot.AppRoot
import shop.manukindapp.com.ui.theme.ManukindAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ManukindAppTheme {
                AppRoot()
            }
        }
    }
}