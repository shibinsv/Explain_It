package explain.it.im10.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.shibin.spendly.ui.theme.BgDeep
import com.shibin.spendly.ui.theme.Im10Theme
import dagger.hilt.android.AndroidEntryPoint
import explain.it.im10.ui.views.HomeView

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Im10Theme {
                Scaffold(
                    containerColor = BgDeep
                ) { innPadding ->
                    Box(modifier = Modifier.padding(innPadding)) {
                        HomeView()
                    }
                }
            }
        }
    }
}