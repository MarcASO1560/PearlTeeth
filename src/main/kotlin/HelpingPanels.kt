import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransparentOverlay(onOverlayDismiss: () -> Unit) {
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(color = TransparentBlack)
			.onClick { "jaja" },
		contentAlignment = Alignment.Center
	) {
		ButtonClear(
			onClick = {
				onOverlayDismiss.invoke()
			}
		)
		var currentMonth by remember { mutableStateOf(LocalDate.now().month) }
		var currentYear by remember { mutableStateOf(LocalDate.now().year) }
		Box(modifier = Modifier.shadow(elevation = 30.dp,spotColor = Black).size(650.dp).clip(shape = RoundedCornerShape(15.dp))){
			Schedule2(currentMonth, currentYear, "Aún no tiene idea de lo que le espera.")
		}
	}
}

@Composable
fun ButtonClear(onClick: () -> Unit){
	Button(
		onClick = onClick,
		modifier = Modifier
			.padding(20.dp)
			.fillMaxSize()
			.wrapContentSize(align = Alignment.TopEnd)
			.shadow(elevation = 60.dp,spotColor = Black)
			.size(60.dp)
			.clip(shape = CircleShape),
		colors = ButtonDefaults.buttonColors(backgroundColor = RedDestructor)
	) {
		Icon(
			Icons.Filled.Clear,
			tint = White,
			contentDescription = "Delete"
		)
	}
}
