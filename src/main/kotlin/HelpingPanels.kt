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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarOverlay(
	selectedDateState: DateState,
	onOverlayDismiss: () -> Unit,
	onOverlayAction: () -> Unit,
){
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(color = TransparentBlack)
			.onClick {
				onOverlayAction.invoke()
					 },
		contentAlignment = Alignment.Center
	) {
		Column {
			ButtonClear(
				onClick = {
					onOverlayDismiss.invoke()
				}
			)
			Box(modifier = Modifier.shadow(elevation = 30.dp,spotColor = Black).size(650.dp,550.dp).clip(shape = RoundedCornerShape(15.dp))){
				Schedule2(
					initialDay = selectedDateState.day,
					initialMonth = selectedDateState.month.toInt(),
					initialYear = selectedDateState.year
				)
			}
		}
	}
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginOverlay(
	onOverlayDismiss: () -> Unit,
	onOverlayAction: () -> Unit
) {
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
				onOverlayAction.invoke()
			}
		)
	}
}

@Composable
fun ButtonClear(onClick: () -> Unit){
	Button(
		onClick = onClick,
		modifier = Modifier
			.padding(0.dp,0.dp,0.dp,20.dp)
			.width(650.dp)
			.wrapContentSize(align = Alignment.TopEnd)
			.shadow(elevation = 60.dp,spotColor = Black)
			.clip(shape = CircleShape),
		colors = ButtonDefaults.buttonColors(backgroundColor = RedDestructor)
	) {
		Icon(
			Icons.Filled.Clear,
			tint = White,
			contentDescription = "Delete",
			modifier = Modifier.padding(0.dp,11.dp,0.dp,11.dp)
		)
	}
}
