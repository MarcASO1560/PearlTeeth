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
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

interface DateSelectionListener {
	fun onDateSelected(selectedDate: LocalDate)
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarOverlay(
	initialSelectedDate: LocalDate,
	onOverlayDismiss: () -> Unit,
	dateSelectionListener: DateSelectionListener
) {
	var selectedDate by remember { mutableStateOf(initialSelectedDate) }
	var datePickerText by remember { mutableStateOf("${selectedDate.dayOfMonth}/${selectedDate.monthValue}/${selectedDate.year}") }

	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(color = TransparentBlack)
			.onClick {

			},
		contentAlignment = Alignment.Center
	) {
		Column(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center)) {
			ButtonClear(
				onClick = {
					onOverlayDismiss.invoke()
				}
			)
			Box(modifier = Modifier.shadow(elevation = 30.dp).size(650.dp,550.dp).clip(shape = RoundedCornerShape(15.dp))){
				Schedule2(
					initialDay = selectedDate.dayOfMonth.toString(),
					initialMonth = selectedDate.monthValue,
					initialYear = selectedDate.year.toString(),
					onDaySelected = { newDate ->
						// Actualiza la fecha localmente dentro del overlay, pero no globalmente.
						selectedDate = newDate
					}
				)
			}
			Box(
				modifier = Modifier.width(650.dp)
			){
				Button(
					onClick = {
						dateSelectionListener.onDateSelected(selectedDate)
						onOverlayDismiss.invoke()
					},
					modifier = Modifier
						.fillMaxWidth()
						.wrapContentSize(align = Alignment.Center)
						.padding(15.dp)
						.width(150.dp)
						.shadow(elevation = 30.dp, spotColor = LightBlue)
						.clip(shape = RoundedCornerShape(15.dp)),
					colors = ButtonDefaults.buttonColors(
						backgroundColor = LightBlue)
				) {
					Text(
						text = "Aplicar",
						fontSize = 16.sp,
						color = White,
						fontWeight = FontWeight.Bold,
						modifier = Modifier.padding(10.dp).fillMaxWidth()
							.wrapContentSize(Alignment.Center)
					)
				}
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
	) {
		Column {
			ButtonClear(
				onClick = {
					onOverlayDismiss.invoke()
					onOverlayAction.invoke()
				}
			)
			Card(
				elevation = 3.dp,
				modifier = Modifier
					.padding(30.dp,10.dp,30.dp,30.dp)
					.wrapContentSize(Alignment.Center)
					.fillMaxSize()
					.shadow(elevation = 10.dp, spotColor = Grey)
					.clip(shape = RoundedCornerShape(30.dp)),
			){}
		}
	}
}

@Composable
fun ButtonClear(onClick: () -> Unit){
	Button(
		onClick = onClick,
		modifier = Modifier
			.padding(20.dp)
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
