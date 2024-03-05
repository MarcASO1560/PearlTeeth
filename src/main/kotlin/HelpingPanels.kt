import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.onExternalDrag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.io.File.separator
//import java.lang.module.ModuleFinder
import java.time.LocalDate
val showOverlayCalendar = mutableStateOf(false)
val showOverlayLogin = mutableStateOf(false)
val showOverlayProfile = mutableStateOf(false)
val showOverlayEditProfile = mutableStateOf(false)
val showOverlayLogOut = mutableStateOf(false)
val showOverlayCreateDate = mutableStateOf(false)
val showOverlaySureClean = mutableStateOf(false)
val showErrorOverlay = mutableStateOf(false)
var isSecondWindowOpen = mutableStateOf(false)
var visibility = mutableStateOf(true)
var isSidePanelVisible = mutableStateOf(false)
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
	var visible by remember { mutableStateOf(false) }
	val scope = rememberCoroutineScope()
	LaunchedEffect(key1 = true) {
		visible = true
	}
	val offsetY by animateDpAsState(
		targetValue = if (visible) 0.dp else 1000.dp,
		tween(durationMillis = 300)
	)
	var selectedDate by remember { mutableStateOf(initialSelectedDate) }
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(color = TransparentBlack)
			.onClick {},
		contentAlignment = Alignment.Center
	) {
		Column(modifier = Modifier.fillMaxSize().offset(y = offsetY).wrapContentSize(align = Alignment.Center)) {
			ButtonClear(
				onClick = {
					visible = false

					scope.launch {
						delay(300)
						onOverlayDismiss()
					}
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
						visible = false

						scope.launch {
							dateSelectionListener.onDateSelected(selectedDate)
							delay(300)
							onOverlayDismiss()
						}
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
@Composable
fun LoginOverlay(
	onOverlayDismiss: () -> Unit,
	onOverlayAction: () -> Unit
) {
	var visible by remember { mutableStateOf(false) }
	val scope = rememberCoroutineScope()
	LaunchedEffect(key1 = true) {
		visible = true
	}
	val offsetY by animateDpAsState(
		targetValue = if (visible) 0.dp else 1000.dp,
		tween(durationMillis = 300)
	)

	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(TransparentBlack)
			.clickable(
				onClick = {},
				indication = null,
				interactionSource = remember { MutableInteractionSource() }
			)
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.offset(y = offsetY)
				.padding(20.dp)
				.wrapContentSize(Alignment.Center)
		) {
			ButtonClear(onClick = {
				visible = false

				scope.launch {
					delay(300)
					onOverlayDismiss()
					onOverlayAction()
				}
			})
			Card(
				elevation = 3.dp,
				modifier = Modifier
					.align(Alignment.CenterHorizontally)
					.padding(10.dp)
					.fillMaxSize()
					.clip(RoundedCornerShape(30.dp)),
			) {
				Column {
					Text(
						text = "POR FAVOR, COMPLETE TODOS LOS CAMPOS",
						modifier = Modifier
							.fillMaxSize()
							.wrapContentSize(align = Alignment.Center)
							.padding(40.dp)
					)
				}
			}
		}
	}
}
@Composable
fun ProfileOverlay(
	onOverlayDismiss: () -> Unit,
	onOverlayAction: () -> Unit
) {
	var visible by remember { mutableStateOf(false) }
	val scope = rememberCoroutineScope()
	LaunchedEffect(key1 = true) {
		visible = true
	}
	val offsetY by animateDpAsState(
		targetValue = if (visible) 0.dp else -1000.dp,
		tween(durationMillis = 300)
	)
	Box(
		modifier = Modifier
			.fillMaxSize()
			.clickable(
				onClick = {
					visible = false
					scope.launch {
						delay(300)
						onOverlayDismiss()
						onOverlayAction()
					}
				},
				indication = null,
				interactionSource = remember { MutableInteractionSource() }
			)
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(50.dp)
				.wrapContentSize(align = Alignment.TopCenter)
		) {
			Card(
				elevation = 3.dp,
				modifier = Modifier
					.offset(y = offsetY)
					.padding(25.dp)
					.width(400.dp)
					.height(225.dp)
					.wrapContentSize(align = Alignment.Center)
					.shadow(30.dp)
					.clip(RoundedCornerShape(15.dp)),
				backgroundColor = White
			) {
				Box(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.TopEnd)){
					Box(
						modifier = Modifier
							.padding(10.dp)
							.width(80.dp)
							.height(30.dp)
							.clip(RoundedCornerShape(5.dp))
							.background(Lilac),
					){
						Text(
							text = "Rol",
							modifier = Modifier
								.fillMaxSize()
								.wrapContentSize(align = Alignment.Center),
							color = White,
							fontWeight = FontWeight.Bold
						)
					}
				}
				Column(
					verticalArrangement = Arrangement.Top,
					modifier = Modifier.padding(16.dp)
				) {
					Row{
						Column(modifier = Modifier.padding(10.dp).size(100.dp).wrapContentSize(align = Alignment.Center)){
							Image(
								painter = painterResource("drawable/castorPerfil.jpg"),
								contentDescription = "Profile Picture",
								modifier = Modifier
									.size(100.dp)
									.shadow(elevation = 10.dp, shape = RoundedCornerShape(50.dp),clip = true, spotColor = Black)
									.clip(RoundedCornerShape(50.dp)),
								contentScale = ContentScale.Crop
							)
						}
						Column(modifier = Modifier.padding(30.dp,30.dp,0.dp,0.dp).wrapContentHeight(align = Alignment.CenterVertically)){
							Row {
								Text(
									text = "Nombre del Usuario",
									color = Black,
									modifier = Modifier.wrapContentSize(align = Alignment.CenterStart),
									fontWeight = FontWeight.Bold
								)
							}
							Row {
								Text(
									text = "Apellidos del Usuario",
									color = Black,
									modifier = Modifier.wrapContentSize(align = Alignment.CenterStart),
									fontWeight = FontWeight.Bold
								)
							}
							Row {
								Text(
									text = "Correo del Usuario",
									color = Black,
									modifier = Modifier.wrapContentSize(align = Alignment.CenterStart),
									fontWeight = FontWeight.Bold
								)
							}
						}
					}
					// Nombre debajo de la imagen
					Row(modifier = Modifier.fillMaxSize()) {
						Box(modifier = Modifier.fillMaxSize().weight(1f)){
							Box(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.BottomStart)){
								Button(
									onClick = {
										visible = false
										scope.launch {
											delay(300)
											onOverlayDismiss()
											onOverlayAction()
											showOverlayEditProfile.value = true
										}
									},
									modifier = Modifier
										.padding(10.dp)
										.fillMaxWidth()
										.height(40.dp)
										.clip(RoundedCornerShape(5.dp)),
									colors = ButtonDefaults.buttonColors(backgroundColor = LightBlue)
								) {
									Text(
										text = "Editar perfil",
										modifier = Modifier
											.fillMaxSize()
											.wrapContentSize(align = Alignment.Center),
										color = White,
										fontWeight = FontWeight.Bold
									)
								}
							}
						}
						Box(modifier = Modifier.fillMaxSize().weight(1f)){
							Box(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.BottomStart)){
								Button(
									onClick = {
										visible = false
										scope.launch {
											delay(300)
											onOverlayDismiss()
											onOverlayAction()
											showOverlayLogOut.value = true
										}
									},
									modifier = Modifier
										.padding(10.dp)
										.fillMaxWidth()
										.height(40.dp)
										.clip(RoundedCornerShape(5.dp)),
									colors = ButtonDefaults.buttonColors(backgroundColor = RedDestructor)
								) {
									Text(
										text = "Cerrar sesión",
										modifier = Modifier
											.fillMaxSize()
											.wrapContentSize(align = Alignment.Center),
										color = White,
										fontWeight = FontWeight.Bold
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
@Composable
fun editProfileOverlay(
	onOverlayDismiss: () -> Unit,
	onOverlayAction: () -> Unit
) {
	var visible by remember { mutableStateOf(false) }
	val scope = rememberCoroutineScope()
	LaunchedEffect(key1 = true) {
		visible = true
	}
	val offsetY by animateDpAsState(
		targetValue = if (visible) 0.dp else 1000.dp,
		tween(durationMillis = 300)
	)
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(TransparentBlack)
			.clickable(
				onClick = {},
				indication = null,
				interactionSource = remember { MutableInteractionSource() }
			)
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.wrapContentSize(Alignment.Center)
		) {
			Box(
				modifier = Modifier
					.align(Alignment.CenterHorizontally)
					.offset(y = offsetY)
					.padding(top = 80.dp)
					.fillMaxHeight()
					.width(600.dp)
					.clip(RoundedCornerShape(15.dp,15.dp,0.dp,0.dp))
					.background(White),
			) {
				Column {
					Box(modifier = Modifier.padding(20.dp).fillMaxSize()){
						Column {
							ButtonClear2(onClick = {
								// Inicia la animación de salida
								visible = false
								// Espera a que la animación termine antes de ejecutar acciones de cierre
								scope.launch {
									delay(300) // Espera la duración de la animación
									onOverlayDismiss()
									onOverlayAction()
									showOverlayEditProfile.value = false
								}
							})
							Row {


								var showFilePicker by remember { mutableStateOf(true) }

								val fileType = listOf("jpg", "png")
								FilePicker(show = showFilePicker, fileExtensions = fileType) { platformFile ->
									showFilePicker = false
									// do something with the file
								}
							}
							Row {

							}
							Row {

							}
						}
					}
				}
			}
		}
	}
}
var jajaja = mutableStateOf(false)
@Composable
fun CourtineOverlay(
	onOverlayDismiss: () -> Unit,
	onOverlayAction: () -> Unit
) {
	var visible by remember { mutableStateOf(false) }
	val scope = rememberCoroutineScope()
	if (jajaja.value == true) {
		visible = false
		scope.launch {
			delay(300)
			onOverlayDismiss()
			onOverlayAction()
			isSidePanelVisible.value = false
		}
	}
	LaunchedEffect(key1 = true) {
		visible = true
	}
	val offsetX by animateDpAsState(
		targetValue = if (visible) 0.dp else 1000.dp,
		tween(durationMillis = 300)
	)
	Box(
		modifier = Modifier
			.clickable(
				onClick = {
					visible = false
					scope.launch {
						delay(300)
						onOverlayDismiss()
						onOverlayAction()
						isSidePanelVisible.value = false
					}
				},
				indication = null,
				interactionSource = remember { MutableInteractionSource() }
			)
			.fillMaxSize()
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.wrapContentSize(Alignment.CenterEnd)
		) {
			Box(
				modifier = Modifier
					.offset(x = offsetX)
					.fillMaxHeight()
					.width(400.dp)
					.wrapContentSize(align = Alignment.CenterEnd)
					.background(TurquoiseLite),
			) {
				Column {
					Box(modifier = Modifier.padding(20.dp).fillMaxSize()){
						Column(modifier = Modifier.fillMaxWidth()) {
							Button(
								onClick = {},
								colors = ButtonDefaults.buttonColors(
									backgroundColor = White),
								modifier = Modifier.padding(bottom = 20.dp).fillMaxWidth().height(40.dp)
							){
								Row {
									Text(
										text = "Option 1",
										fontWeight = FontWeight.Bold,
										color = TurquoiseLite,
										fontSize = 14.sp,
										modifier = Modifier
											.fillMaxHeight()
											.wrapContentSize(align = Alignment.Center)
									)
								}
							}
							Button(
								onClick = {},
								colors = ButtonDefaults.buttonColors(
									backgroundColor = White),
								modifier = Modifier.padding(bottom = 20.dp).fillMaxWidth().height(40.dp)
							){
								Row {
									Text(
										text = "Option 2",
										fontWeight = FontWeight.Bold,
										color = TurquoiseLite,
										fontSize = 14.sp,
										modifier = Modifier
											.fillMaxHeight()
											.wrapContentSize(align = Alignment.Center)
									)
								}
							}
							Button(
								onClick = {},
								colors = ButtonDefaults.buttonColors(
									backgroundColor = White),
								modifier = Modifier.padding(bottom = 20.dp).fillMaxWidth().height(40.dp)
							){
								Row {
									Text(
										text = "Option 3",
										fontWeight = FontWeight.Bold,
										color = TurquoiseLite,
										fontSize = 14.sp,
										modifier = Modifier
											.fillMaxHeight()
											.wrapContentSize(align = Alignment.Center)
									)
								}
							}
						}
						Box(modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.BottomCenter)){
							Button(
								onClick = {
									visible = false
									scope.launch {
										delay(300)
										onOverlayDismiss()
										onOverlayAction()
										isSidePanelVisible.value = false
									}
									showOverlayLogOut.value = true
								},
								colors = ButtonDefaults.buttonColors(
									backgroundColor = RedDestructor),
								modifier = Modifier.fillMaxWidth().height(40.dp)
							){
								Row {
									Text(
										text = "Cerrar Sesión",
										fontWeight = FontWeight.Bold,
										color = White,
										fontSize = 14.sp,
										modifier = Modifier
											.fillMaxHeight()
											.wrapContentSize(align = Alignment.Center)
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
@Composable
fun logOutOverlay(
	onOverlayDismiss: () -> Unit,
	onOverlayAction: () -> Unit
) {
	var visible by remember { mutableStateOf(false) }
	val scope = rememberCoroutineScope()
	LaunchedEffect(key1 = true) {
		visible = true
	}
	val offsetY by animateDpAsState(
		targetValue = if (visible) 0.dp else 1000.dp,
		tween(durationMillis = 300)
	)
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(TransparentBlack)
			.clickable(
				onClick = {},
				indication = null,
				interactionSource = remember { MutableInteractionSource() }
			)
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(50.dp)
				.wrapContentSize(Alignment.Center)
		) {
			Card(
				elevation = 3.dp,
				modifier = Modifier
					.align(Alignment.CenterHorizontally)
					.offset(y = offsetY)
					.padding(50.dp)
					.width(400.dp)
					.height(240.dp)
					.clip(RoundedCornerShape(30.dp)),
			) {
				Column {
					ButtonClear2(onClick = {
						// Inicia la animación de salida
						visible = false
						// Espera a que la animación termine antes de ejecutar acciones de cierre
						scope.launch {
							delay(300) // Espera la duración de la animación
							onOverlayDismiss()
							onOverlayAction()
							showOverlayLogOut.value = false
						}
					})
					Row {
						Text(
							text = "¿Seguro que quieres cerrar tu sesión?",
							modifier = Modifier
								.padding(10.dp)
								.fillMaxWidth()
								.height(30.dp)
								.wrapContentSize(align = Alignment.TopCenter),
							color = Black,
							fontSize = 16.sp,
							fontWeight = FontWeight.Bold
						)
					}
					Row(modifier = Modifier.padding(bottom = 20.dp).fillMaxSize().wrapContentSize(align = Alignment.Center)) {
						Button(
							onClick = {
								// Inicia la animación de salida
								visible = false
								// Espera a que la animación termine antes de ejecutar acciones de cierre
								scope.launch {
									delay(300) // Espera la duración de la animación
									onOverlayDismiss()
									onOverlayAction()
									cleanDataPatient()
									isSecondWindowOpen.value = false
									visibility.value = true
									showOverlayLogOut.value = false
									showOverlayCalendar.value = false
									showOverlayLogin.value = false
									showOverlayProfile.value = false
									showOverlayEditProfile.value = false
									showOverlayCreateDate.value = false
									showErrorOverlay.value = false
									showOverlaySureClean.value = false
									isSecondWindowOpen.value = false
									isSidePanelVisible.value = false
								}
							},
							modifier = Modifier
								.padding(10.dp)
								.width(150.dp)
								.height(40.dp)
								.wrapContentSize(align = Alignment.Center)
								.clip(RoundedCornerShape(5.dp)),
							colors = ButtonDefaults.buttonColors(backgroundColor = RedDestructor)
						) {
							Text(
								text = "Cerrar sesión",
								modifier = Modifier
									.fillMaxSize()
									.wrapContentSize(align = Alignment.Center),
								color = White,
								fontWeight = FontWeight.Bold
							)
						}
					}
				}
			}
		}
	}
}
@Composable
fun sureDeleteOverlay(
	onOverlayDismiss: () -> Unit,
	onOverlayAction: () -> Unit
) {
	var visible by remember { mutableStateOf(false) }
	val scope = rememberCoroutineScope()
	LaunchedEffect(key1 = true) {
		visible = true
	}
	val offsetY by animateDpAsState(
		targetValue = if (visible) 0.dp else 1000.dp,
		tween(durationMillis = 300)
	)
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(TransparentBlack)
			.clickable(
				onClick = {},
				indication = null,
				interactionSource = remember { MutableInteractionSource() }
			)
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(50.dp)
				.wrapContentSize(Alignment.Center)
		) {
			Card(
				elevation = 3.dp,
				modifier = Modifier
					.align(Alignment.CenterHorizontally)
					.offset(y = offsetY)
					.padding(50.dp)
					.width(400.dp)
					.height(240.dp)
					.clip(RoundedCornerShape(30.dp)),
			) {
				Column {
					ButtonClear2(onClick = {
						// Inicia la animación de salida
						visible = false
						// Espera a que la animación termine antes de ejecutar acciones de cierre
						scope.launch {
							delay(300) // Espera la duración de la animación
							onOverlayDismiss()
							onOverlayAction()
							showOverlayLogOut.value = false
						}
					})
					Row {
						Text(
							text = "¿Seguro que quieres limpiar los datos\npor defecto?",
							modifier = Modifier
								.padding(10.dp)
								.fillMaxWidth()
								.height(40.dp)
								.wrapContentSize(align = Alignment.TopCenter),
							color = Black,
							fontSize = 16.sp,
							fontWeight = FontWeight.Bold
						)
					}
					Row(modifier = Modifier.padding(bottom = 20.dp).fillMaxSize().wrapContentSize(align = Alignment.Center)) {
						Button(
							onClick = {
								// Inicia la animación de salida
								visible = false
								// Espera a que la animación termine antes de ejecutar acciones de cierre
								scope.launch {
									delay(300) // Espera la duración de la animación
									onOverlayDismiss()
									onOverlayAction()
									cleanDataPatient()
								}
							},
							modifier = Modifier
								.padding(10.dp)
								.width(150.dp)
								.height(40.dp)
								.wrapContentSize(align = Alignment.Center)
								.clip(RoundedCornerShape(5.dp)),
							colors = ButtonDefaults.buttonColors(backgroundColor = Lilac)
						) {
							Text(
								text = "Limpiar datos",
								modifier = Modifier
									.fillMaxSize()
									.wrapContentSize(align = Alignment.Center),
								color = White,
								fontWeight = FontWeight.Bold
							)
						}
					}
				}
			}
		}
	}
}
@Composable
fun errorOverlay(
	onOverlayDismiss: () -> Unit,
	onOverlayAction: () -> Unit,
	showError: String
) {
	var visible by remember { mutableStateOf(false) }
	val scope = rememberCoroutineScope()
	LaunchedEffect(key1 = true) {
		visible = true
	}
	val offsetY by animateDpAsState(
		targetValue = if (visible) 0.dp else 1000.dp,
		tween(durationMillis = 300)
	)
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(TransparentBlack)
			.clickable(
				onClick = {},
				indication = null,
				interactionSource = remember { MutableInteractionSource() }
			)
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(50.dp)
				.wrapContentSize(Alignment.Center)
		) {
			Card(
				elevation = 3.dp,
				modifier = Modifier
					.align(Alignment.CenterHorizontally)
					.offset(y = offsetY)
					.padding(50.dp)
					.width(400.dp)
					.height(240.dp)
					.clip(RoundedCornerShape(30.dp)),
			) {
				Column {
					ButtonClear2(onClick = {
						// Inicia la animación de salida
						visible = false
						// Espera a que la animación termine antes de ejecutar acciones de cierre
						scope.launch {
							delay(300) // Espera la duración de la animación
							onOverlayDismiss()
							onOverlayAction()
							showOverlayLogOut.value = false
						}
					})
					Row {
						Text(
							text = showError,
							modifier = Modifier
								.padding(10.dp)
								.fillMaxWidth()
								.height(40.dp)
								.wrapContentSize(align = Alignment.TopCenter),
							color = Black,
							fontSize = 16.sp,
							fontWeight = FontWeight.Bold
						)
					}
					Row(modifier = Modifier.padding(bottom = 20.dp).fillMaxSize().wrapContentSize(align = Alignment.Center)) {
						Button(
							onClick = {
								// Inicia la animación de salida
								visible = false
								// Espera a que la animación termine antes de ejecutar acciones de cierre
								scope.launch {
									delay(300) // Espera la duración de la animación
									onOverlayDismiss()
									onOverlayAction()
								}
							},
							modifier = Modifier
								.padding(10.dp)
								.width(150.dp)
								.height(40.dp)
								.wrapContentSize(align = Alignment.Center)
								.clip(RoundedCornerShape(5.dp)),
							colors = ButtonDefaults.buttonColors(backgroundColor = LightBlue)
						) {
							Text(
								text = "De acuerdo",
								modifier = Modifier
									.fillMaxSize()
									.wrapContentSize(align = Alignment.Center),
								color = White,
								fontWeight = FontWeight.Bold
							)
						}
					}
				}
			}
		}
	}
}

@Composable
fun createDatesOverlay(
	onOverlayDismiss: () -> Unit,
	onOverlayAction: () -> Unit
) {
	var visible by remember { mutableStateOf(false) }
	val scope = rememberCoroutineScope()
	LaunchedEffect(key1 = true) {
		visible = true
	}
	val offsetY by animateDpAsState(
		targetValue = if (visible) 0.dp else 1000.dp,
		tween(durationMillis = 300)
	)
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(TransparentBlack)
			.clickable(
				onClick = {},
				indication = null,
				interactionSource = remember { MutableInteractionSource() }
			)
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(50.dp)
				.wrapContentSize(Alignment.Center)
		) {
			Card(
				elevation = 3.dp,
				modifier = Modifier
					.align(Alignment.CenterHorizontally)
					.offset(y = offsetY)
					.padding(50.dp)
					.fillMaxSize()
					.clip(RoundedCornerShape(30.dp)),
			) {
				var nombreCita = remember { mutableStateOf("") }
				Column {
					Row(modifier = Modifier.fillMaxWidth().height(90.dp).background(Bright1).wrapContentSize(align = Alignment.Center)) {
						TextField(
							value = nombreCita.value,
							onValueChange = { nombreCita.value = it },
							label = {
								Text(
									text = "Nombre de la Cita",
									modifier = Modifier.padding(5.dp).fillMaxSize().wrapContentSize(align = Alignment.TopCenter),
									fontWeight = FontWeight.Bold,
									color = Grey
								)
							},
							modifier = Modifier
								.padding(10.dp)
								.width(600.dp)
								.fillMaxHeight()
								.shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp), spotColor = Grey)
								.clip(shape = RoundedCornerShape(20.dp)),
							colors = TextFieldDefaults.textFieldColors(
								backgroundColor = White,
								focusedIndicatorColor = White,
								cursorColor = Grey,
								textColor = Dark2,
								unfocusedIndicatorColor = White
							),
							textStyle = LocalTextStyle.current.copy(
								fontWeight = FontWeight.Bold,
								fontSize = 20.sp
							)
						)
					}
					val scrollState = rememberScrollState()
					Row(modifier = Modifier.weight(1f)) {
						Box(modifier = Modifier.fillMaxSize()) {
							Column(
								modifier = Modifier
									.padding(10.dp,0.dp,10.dp,0.dp)
									.verticalScroll(scrollState)
							) {
								Row(modifier = Modifier.fillMaxWidth().height(90.dp)) {
									Card(elevation = 1.dp, modifier = Modifier
										.padding(10.dp)
										.wrapContentSize(Alignment.TopCenter)
										.shadow(elevation = 10.dp,shape = RoundedCornerShape(10.dp),spotColor = Grey)
										.clip(shape = RoundedCornerShape(15.dp)),
									) {
										RadioButtonsColors()
										Text(
											text = "a",
											modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.TopCenter),
											fontWeight = FontWeight.Bold,
											color = Grey
										)
									}
								}
								Row(modifier = Modifier.fillMaxWidth().height(600.dp)) {
									Card(elevation = 1.dp, modifier = Modifier
										.padding(10.dp,0.dp,10.dp,10.dp)
										.wrapContentSize(Alignment.TopCenter)
										.shadow(elevation = 10.dp,shape = RoundedCornerShape(10.dp),spotColor = Grey)
										.clip(shape = RoundedCornerShape(15.dp)),
									) {
										Text(
											text = "a",
											modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.TopCenter),
											fontWeight = FontWeight.Bold,
											color = Grey
										)
									}
								}
							}
							VerticalScrollbar(
								modifier = Modifier.align(Alignment.CenterEnd)
									.fillMaxHeight()
									.padding(6.dp),
								adapter = rememberScrollbarAdapter(scrollState)
							)
						}
					}
					Row(modifier = Modifier.fillMaxWidth().shadow(5.dp).height(90.dp).background(White).wrapContentSize(align = Alignment.CenterEnd)) {
						Button(
							onClick = {},
							modifier = Modifier
								.width(200.dp)
								.fillMaxHeight()
								.padding(15.dp,15.dp,15.dp,15.dp)
								.shadow(elevation = 30.dp,spotColor = LightBlue)
								.clip(shape = RoundedCornerShape(15.dp)),
							colors = ButtonDefaults.buttonColors(
								backgroundColor = LightBlue)
						){
							Text(
								text = "Guardar",
								fontSize = 16.sp,
								color = White,
								fontWeight = FontWeight.Bold,
								modifier = Modifier.fillMaxWidth()
									.wrapContentSize(Alignment.Center)
							)
						}
					}
				}
				ButtonClear2(onClick = {
					// Inicia la animación de salida
					visible = false

					// Espera a que la animación termine antes de ejecutar acciones de cierre
					scope.launch {
						delay(300) // Espera la duración de la animación
						onOverlayDismiss()
						onOverlayAction()
					}
				})
			}
		}
	}
}
//Estos Botones composables sirven para poder cerrar los overlays que lo necesiten.
//El ButtonClear sirve para colocarse en la parte exterior de un panel.
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
//El ButtonClear2 sirve para colocarse en la parte interior de un panel.
@Composable
fun ButtonClear2(onClick: () -> Unit){
	Button(
		onClick = onClick,
		modifier = Modifier
			.padding(20.dp)
			.fillMaxWidth()
			.wrapContentSize(align = Alignment.TopEnd)
			.size(50.dp)
			.clip(shape = CircleShape),
		colors = ButtonDefaults.buttonColors(backgroundColor = RedDestructor)
	) {
		Icon(
			Icons.Filled.Clear,
			tint = White,
			contentDescription = "Delete",
			modifier = Modifier.size(35.dp)
		)
	}
}

@Composable
fun RadioButtonsColors(){
	Box(modifier = Modifier.fillMaxHeight().wrapContentSize(align = Alignment.CenterEnd)){
		val colorOptions = listOf(PastelBlue,PastelGreen,PastelRose,PastelOrange,PastelPurple)
		val (selectedOption,onOptionSelected) = remember {mutableStateOf(colorOptions[0])}
		Box(modifier = Modifier
			.padding(10.dp)
			.fillMaxHeight()
			.width(200.dp)
			.shadow(elevation = 10.dp,shape = RoundedCornerShape(10.dp),spotColor = Grey)
			.clip(shape = RoundedCornerShape(15.dp))
			.background(White)
			.wrapContentSize(align = Alignment.Center)){
			Row {
				colorOptions.forEach { color ->
					Box(modifier = Modifier.padding(5.dp).size(28.dp).shadow(elevation = 5.dp,shape = RoundedCornerShape(15.dp),spotColor = Grey).clip(shape = CircleShape).background(White)){
						RadioButton(
							selected = (color == selectedOption),
							onClick = {onOptionSelected(color)},
							colors = RadioButtonDefaults.colors(
								selectedColor = color,
								unselectedColor = color
							)
						)
					}
				}
			}
		}
	}
}