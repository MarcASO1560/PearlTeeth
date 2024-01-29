import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
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

@Composable
fun Filiar() {
	val scrollState = rememberScrollState()
	Box(
		modifier = Modifier.fillMaxSize(),
	) {
		Column(
			modifier = Modifier
				.verticalScroll(scrollState)
				.fillMaxSize()
		) {
			Column(modifier = Modifier.weight(1f)) {
				Card(modifier = Modifier.fillMaxSize().padding(15.dp).shadow(20.dp).clip(shape = RoundedCornerShape(10.dp))) {
					Column(modifier = Modifier.fillMaxSize()) {
						Row(modifier = Modifier.fillMaxWidth().height(30.dp).shadow(10.dp)){
							Box(modifier = Modifier.fillMaxSize().background(Turquoise)){
								Text("Datos de domicilio",
									color = White,
									fontSize = 18.sp,
									fontWeight = FontWeight.Bold,
									modifier = Modifier
										.fillMaxSize()
										.wrapContentSize(align = Alignment.Center)
								)
							}
						}
						Row(modifier = Modifier.fillMaxSize()){}
					}
				}
			}
			Column(modifier = Modifier.weight(1f)) {
				Card(modifier = Modifier.fillMaxSize().padding(15.dp).shadow(20.dp).clip(shape = RoundedCornerShape(10.dp))) {
					Column(modifier = Modifier.fillMaxSize()) {
						Row(modifier = Modifier.fillMaxWidth().height(30.dp).shadow(10.dp)){
							Box(modifier = Modifier.fillMaxSize().background(Turquoise)){
								Text("Datos personales",
									color = White,
									fontSize = 18.sp,
									fontWeight = FontWeight.Bold,
									modifier = Modifier
										.fillMaxSize()
										.wrapContentSize(align = Alignment.Center)
								)
							}
						}
						Row(modifier = Modifier.fillMaxSize()){}
					}
				}
			}
			Column(modifier = Modifier.weight(1f)) {
				Card(modifier = Modifier.fillMaxSize().padding(15.dp).shadow(20.dp).clip(shape = RoundedCornerShape(10.dp))) {
					Column(modifier = Modifier.fillMaxSize()) {
						Row(modifier = Modifier.fillMaxWidth().height(30.dp).shadow(10.dp)){
							Box(modifier = Modifier.fillMaxSize().background(Turquoise)){
								Text("Otros",
									color = White,
									fontSize = 18.sp,
									fontWeight = FontWeight.Bold,
									modifier = Modifier
										.fillMaxSize()
										.wrapContentSize(align = Alignment.Center)
								)
							}
						}
						Row(modifier = Modifier.fillMaxSize()){}
					}
				}
			}
		}
	}
}