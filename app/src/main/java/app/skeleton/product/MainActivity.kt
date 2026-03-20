package app.skeleton.product

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import app.skeleton.product.ui.composable.approot.AppRoot
import app.skeleton.product.ui.theme.ProductAppSkeletonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductAppSkeletonTheme {
                AppRoot()
            }
        }
    }
}