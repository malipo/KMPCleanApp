import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp



@Composable
fun SampleNavigationDrawer() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val items =
        listOf(
            "Home" to Icons.Default.Home,
            "Settings" to Icons.Default.Settings,
            "Favourites" to Icons.Default.Favorite,
        )

    ModalNavigationDrawer(
        drawerContent = {
            Column(
                Modifier.background(Color.Gray).fillMaxHeight().padding(12.dp).selectableGroup(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items.forEachIndexed { index, item ->
                    val (text, icon) = item

                    NavigationDrawerItem(
                        label = { Text(text) },
                        selected = false,
                        onClick = { /* Handle click */ },
                    )
                }
            }
        }
    ) {
        Button(modifier = Modifier.height(100.dp).fillMaxWidth(), onClick = {}) { Text("BUTTON") }
    }
}