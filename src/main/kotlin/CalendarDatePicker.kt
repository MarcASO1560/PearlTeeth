import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.Month
@Composable
fun Schedule2(
	initialDay: String,
	initialMonth: Int,
	initialYear: String
) {
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
	var selectedDay by remember { mutableStateOf(initialDay) }
	var selectedMonth by remember { mutableStateOf(initialMonth) }
	var selectedYear by remember { mutableStateOf(initialYear) }

	var selectedDateState by remember {
		mutableStateOf(LocalDate.of(initialYear.toInt(), initialMonth, initialDay.toInt()))
	}

	var daysMatrix by remember { mutableStateOf(getDaysMatrix2(selectedYear.toInt(), Month.of(selectedMonth), selectedDay)) }

	fun updateDaysMatrix() {
		daysMatrix = getDaysMatrix2(selectedYear.toInt(), Month.of(selectedMonth), selectedDay)
	}

	Column {
		Column {
			// Añade un TextField para seleccionar el año
			TextField(
				value = selectedYear,
				onValueChange = {
					// Maneja el cambio de año
					it.toIntOrNull()?.let { year ->
						selectedYear = year.toString()
						updateDaysMatrix()
					}
				},
				colors = TextFieldDefaults.textFieldColors(
					backgroundColor = Turquoise,
					focusedIndicatorColor = White,
					cursorColor = TurquoiseLite,
					textColor = White,
					unfocusedIndicatorColor = Turquoise
				),
				keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
				modifier = Modifier
					.background(Turquoise)
					.fillMaxWidth()
					.wrapContentSize(align = Alignment.Center),
				textStyle = TextStyle(
					textAlign = TextAlign.Center,
					fontWeight = FontWeight.Bold,
					fontSize = 20.sp
				)
			)
			Box(modifier = Modifier.fillMaxWidth().height(60.dp).background(Turquoise)) {
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
								if (selectedMonth == 1){
									selectedMonth = selectedMonth + 11
									selectedYear = (selectedYear.toInt() - 1).toString()
								} else {
									selectedMonth = selectedMonth.minus(1)
								}
							updateDaysMatrix()
						},
						modifier = Modifier.size(48.dp)
					) {
						Icon(Icons.Default.ArrowBack, "Mes anterior", tint = White)
					}

					Text(
						text = getSpanishMonthName(Month.of(selectedMonth)),
						style = MaterialTheme.typography.h6,
						fontWeight = FontWeight.Bold,
						color = White
					)

					IconButton(
						onClick = {
							if (selectedMonth == 12){
								selectedMonth = selectedMonth - 11
								selectedYear = (selectedYear.toInt() + 1).toString()
							} else {
								selectedMonth = selectedMonth + 1
							}
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
				Row(modifier = Modifier.fillMaxWidth().weight(0.5f)) {
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
								if (dayInfo.day > 0) White else TurquoiseLite // Color más oscuro para días no válidos
							Card(
								elevation = 1.dp,
								modifier = Modifier
									.padding(5.dp)
									.fillMaxHeight()
									.weight(1f)
									.shadow(20.dp)
									.clip(shape = RoundedCornerShape(10.dp))
									.clickable {
										if (dayInfo.isClickable) {
											val selectedMonth = getSpanishMonthName(selectedDateState.month)
											val currentMonthText = getSpanishMonthName(dayInfo.month)
											if (currentMonthText == selectedMonth) {
												selectedDay = dayInfo.toString()
												updateDaysMatrix()
											}
										}
									},
								backgroundColor = if (dayInfo.day == selectedDay.toInt() && dayInfo.month == selectedDateState.month && dayInfo.year == selectedDateState.year) LightBlue else backgroundColor,
							) {
								Box(
									modifier = Modifier
										.fillMaxSize()
										.padding(2.dp),
									contentAlignment = Alignment.Center
								) {
									Column(
										horizontalAlignment = Alignment.CenterHorizontally,
										verticalArrangement = Arrangement.Center
									) {
										Text(
											text = if (dayInfo.day > 0) dayInfo.day.toString() else "",
											fontWeight = FontWeight.Bold,
											fontSize = 16.sp,
											textAlign = TextAlign.Center,
											color = if (dayInfo.isClickable) {
												if (dayInfo.day == selectedDay.toInt() && dayInfo.month == selectedDateState.month && dayInfo.year == selectedDateState.year) White else Dark1
											} else {
												if (dayInfo.day == selectedDay.toInt() && dayInfo.month == selectedDateState.month && dayInfo.year == selectedDateState.year) White else Bright2
											}
										)
									}
								}
							}
						}
					}
				}
			}
		}
	}
}