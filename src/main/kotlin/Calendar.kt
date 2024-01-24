import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import java.awt.Color
import java.time.LocalDate
import java.time.Month

// Función para obtener la matriz de días del mes y año especificados
fun getDaysMatrix(year: Int, month: Month): List<List<DayInfo>> {
	val firstDayOfMonth = LocalDate.of(year, month.ordinal + 1, 1)
	val daysInMonth = firstDayOfMonth.lengthOfMonth()

	// Obtener el último día del mes anterior
	val lastDayOfPreviousMonth = firstDayOfMonth.minusDays(1)
	val daysInLastMonth = lastDayOfPreviousMonth.dayOfMonth

	val daysMatrix = mutableListOf<List<DayInfo>>()
	var currentRow = mutableListOf<DayInfo>()

	// Agregar los días del mes anterior a la primera fila
	for (i in daysInLastMonth - firstDayOfMonth.dayOfWeek.value + 2..daysInLastMonth) {
		currentRow.add(DayInfo(i, false))
	}

	// Agregar los días del mes actual
	for (day in 1..daysInMonth) {
		val currentDate = LocalDate.of(year, month.ordinal + 1, day)
		val dayOfWeek = currentDate.dayOfWeek.value

		if (dayOfWeek == 1 && currentRow.isNotEmpty()) {
			// Cambiar de semana, agregar la fila actual y reiniciar
			daysMatrix.add(currentRow.toList())
			currentRow.clear()
		}

		val isClickable = day <= daysInMonth // Solo permitir hacer clic en los días del mes
		currentRow.add(DayInfo(day, isClickable))
	}

	// Completar la última fila con los primeros días del mes siguiente
	val lastRow = currentRow + (1..7 - currentRow.size).map { DayInfo(it, false) }
	daysMatrix.add(lastRow)

	// Asegurarse de tener 6 filas
	while (daysMatrix.size < 5) {
		daysMatrix.add(List(6) { DayInfo(0, false) })
	}

	return daysMatrix
}

data class DayInfo(val day: Int, val isClickable: Boolean)
@Composable
fun Schedule(currentMonth: Month, currentYear: Int) {
	// Agrega esta función a tu código
	fun getSpanishMonthName(month: Month): String {
		return when (month) {
			Month.JANUARY -> "Enero"
			Month.FEBRUARY -> "Febrero"
			Month.MARCH -> "Marzo"
			Month.APRIL -> "Abril"
			Month.MAY -> "Mayo"
			Month.JUNE -> "Junio"
			Month.JULY -> "Julio"
			Month.AUGUST -> "Agosto"
			Month.SEPTEMBER -> "Septiembre"
			Month.OCTOBER -> "Octubre"
			Month.NOVEMBER -> "Noviembre"
			Month.DECEMBER -> "Diciembre"
		}
	}

	var currentMonth by remember { mutableStateOf(LocalDate.now().month) }
	var currentYear by remember { mutableStateOf(LocalDate.now().year) }
	var daysMatrix by remember { mutableStateOf(getDaysMatrix(currentYear, currentMonth)) }

	fun updateDaysMatrix() {
		daysMatrix = getDaysMatrix(currentYear, currentMonth)
	}

	Column {
		Box(modifier = Modifier.fillMaxWidth().height(60.dp).background(Turquoise)){
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(8.dp),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				IconButton(
					onClick = {
						// Función para retroceder un mes
						currentMonth =
							if (currentMonth == Month.JANUARY) Month.DECEMBER else Month.values()[currentMonth.ordinal - 1]
						updateDaysMatrix()
					},
					modifier = Modifier.size(48.dp)
				) {
					Icon(Icons.Default.ArrowBack, "Mes anterior", tint = White)
				}

				Text(
					text = "${getSpanishMonthName(currentMonth)} $currentYear",
					style = MaterialTheme.typography.h6,
					fontWeight = FontWeight.Bold,
					color = White
				)

				IconButton(
					onClick = {
						// Función para avanzar un mes
						currentMonth =
							if (currentMonth == Month.DECEMBER) Month.JANUARY else Month.values()[currentMonth.ordinal + 1]
						updateDaysMatrix()
					},
					modifier = Modifier.size(48.dp)
				) {
					Icon(Icons.Default.ArrowForward, "Mes siguiente", tint = White)
				}
			}
		}
		Column(
			modifier = Modifier
				.background(Bright1)
				.fillMaxSize()
				.padding(5.dp)
		) {
			Row(modifier = Modifier.fillMaxWidth().weight(0.35f)) {
				Card(elevation = 1.dp, modifier = Modifier
					.padding(5.dp)
					.fillMaxHeight()
					.weight(1f)
					.shadow(20.dp)
					.clip(shape = RoundedCornerShape(10.dp)),
					backgroundColor = TurquoiseLite
				){
					Box(
						modifier = Modifier
							.fillMaxSize()
							.padding(2.dp),
						contentAlignment = Alignment.Center,
					) {Text("Lunes",textAlign = TextAlign.Center,color = White,fontSize = 16.sp,fontWeight = FontWeight.Bold)}
				}
				Card(elevation = 1.dp, modifier = Modifier
					.padding(5.dp)
					.fillMaxHeight()
					.weight(1f)
					.shadow(20.dp)
					.clip(shape = RoundedCornerShape(10.dp)),
					backgroundColor = TurquoiseLite
				){
					Box(
						modifier = Modifier
							.fillMaxSize()
							.padding(2.dp),
						contentAlignment = Alignment.Center,
					) {Text("Martes",color = White,textAlign = TextAlign.Center,fontSize = 16.sp,fontWeight = FontWeight.Bold)}
				}
				Card(elevation = 1.dp, modifier = Modifier
					.padding(5.dp)
					.fillMaxHeight()
					.weight(1f)
					.shadow(20.dp)
					.clip(shape = RoundedCornerShape(10.dp)),
					backgroundColor = TurquoiseLite
				){
					Box(
						modifier = Modifier
							.fillMaxSize()
							.padding(2.dp),
						contentAlignment = Alignment.Center,
					) {Text("Miercoles",color = White,textAlign = TextAlign.Center,fontSize = 16.sp,fontWeight = FontWeight.Bold)}

				}
				Card(elevation = 1.dp, modifier = Modifier
					.padding(5.dp)
					.fillMaxHeight()
					.weight(1f)
					.shadow(20.dp)
					.clip(shape = RoundedCornerShape(10.dp)),
					backgroundColor = TurquoiseLite
				){
					Box(
						modifier = Modifier
							.fillMaxSize()
							.padding(2.dp),
						contentAlignment = Alignment.Center,
					) {Text("Jueves",color = White,textAlign = TextAlign.Center,fontSize = 16.sp,fontWeight = FontWeight.Bold)}
				}
				Card(elevation = 1.dp, modifier = Modifier
					.padding(5.dp)
					.fillMaxHeight()
					.weight(1f)
					.shadow(20.dp)
					.clip(shape = RoundedCornerShape(10.dp)),
					backgroundColor = TurquoiseLite
				){
					Box(
						modifier = Modifier
							.fillMaxSize()
							.padding(2.dp),
						contentAlignment = Alignment.Center,
					) {Text("Viernes",color = White,textAlign = TextAlign.Center,fontSize = 16.sp,fontWeight = FontWeight.Bold)}

				}
				Card(elevation = 1.dp, modifier = Modifier
					.padding(5.dp)
					.fillMaxHeight()
					.weight(1f)
					.shadow(20.dp)
					.clip(shape = RoundedCornerShape(10.dp)),
					backgroundColor = Lilac
				){
					Box(
						modifier = Modifier
							.fillMaxSize()
							.padding(2.dp),
						contentAlignment = Alignment.Center,
					) {Text("Sabado",color = White,textAlign = TextAlign.Center,fontSize = 16.sp,fontWeight = FontWeight.Bold)}

				}
				Card(elevation = 1.dp, modifier = Modifier
					.padding(5.dp)
					.fillMaxHeight()
					.weight(1f)
					.shadow(20.dp)
					.clip(shape = RoundedCornerShape(10.dp)),
					backgroundColor = Lilac
				){
					Box(
						modifier = Modifier
							.fillMaxSize()
							.padding(2.dp),
						contentAlignment = Alignment.Center,
					) {Text("Domingo",color = White,textAlign = TextAlign.Center,fontSize = 16.sp,fontWeight = FontWeight.Bold)}
				}
			}
			for (row in daysMatrix) {
				Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
					for (dayInfo in row) {
						val backgroundColor =
							if (dayInfo.day > 0) White else Bright2 // Color más oscuro para días no válidos
						Card(
							elevation = 1.dp,
							modifier = Modifier
								.padding(5.dp)
								.fillMaxHeight()
								.weight(1f)
								.shadow(20.dp)
								.clip(shape = RoundedCornerShape(10.dp))
								.clickable { if (dayInfo.isClickable){} },
							backgroundColor = backgroundColor
						) {
							Box(
								modifier = Modifier
									.fillMaxSize()
									.padding(2.dp),
								contentAlignment = Alignment.Center,
							) {
								Text(
									text = if (dayInfo.day > 0) dayInfo.day.toString() else "",
									fontWeight = FontWeight.Bold,
									fontSize = 16.sp,
									textAlign = TextAlign.Center,
									color = if (dayInfo.isClickable) Dark1 else Bright2
								)
							}
						}
					}
				}
			}
		}
	}
}
@Composable
fun dateAdd(onClick: () -> Unit) {
	FloatingActionButton(
		onClick = { onClick() },
		backgroundColor = Lilac,
		contentColor = White,
		shape = CircleShape,
		modifier = Modifier
			.fillMaxSize()
			.wrapContentSize(Alignment.BottomEnd)
			.padding(20.dp)
			.size(50.dp)
	) {
		Icon(Icons.Filled.Add, "Floating action button.", modifier = Modifier.size(35.dp), tint = White)
	}
}
enum class Month {
	JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
}