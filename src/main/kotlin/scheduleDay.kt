import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import androidx.compose.runtime.mutableStateOf
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


@Composable
fun DayScaffold() {
	// Crear un estado de desplazamiento
	val scrollState = rememberScrollState()

	// Box para mantener tanto el contenido desplazable como la barra de desplazamiento
	Box(modifier = Modifier.fillMaxSize()) {
		// Columna desplazable
		Column(
			modifier = Modifier
				.padding(16.dp)
				.verticalScroll(scrollState)
		) {
			(8..14).forEach { hour ->
				HourRow(hourText = "$hour:00", isHour = true)
				if (hour < 14) {
					HourRow(hourText = "$hour:30", isHour = false)
				}
			}
		}

		// Barra de desplazamiento vertical
		VerticalScrollbar(
			modifier = Modifier.align(Alignment.CenterEnd)
				.fillMaxHeight()
				.padding(6.dp),
			adapter = rememberScrollbarAdapter(scrollState)
		)
	}
}

@Composable
fun HourRow(hourText: String, isHour: Boolean) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentSize(align = Alignment.Center)
			.padding(vertical = if (isHour) 10.dp else 50.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Text(text = hourText, fontSize = 20.sp)
		Spacer(modifier = Modifier.width(16.dp))
		Box(
			modifier = Modifier
				.height(if (isHour) 2.dp else 1.dp)
				.padding(start = if (isHour) 0.dp else 20.dp)
				.weight(1f)
				.align(Alignment.CenterVertically)
				.background(Grey)
		)
	}
}