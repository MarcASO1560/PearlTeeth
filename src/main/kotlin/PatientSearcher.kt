import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

var totalNumPatients = mutableIntStateOf(getAllPatientsNumber())

var distantName = (2f)
var distantNIF = (1f)
var distantPhone = (1f)
var distantEmail = (1.5f)
data class Patient(
    val name: String,
    val nif: String,
    val phone: Int,
    val email: String
)
val patients = mutableStateOf(getAllPatients())
@Composable
fun searcherResults(){
    Column(modifier = Modifier.padding(15.dp,15.dp,0.dp,15.dp).fillMaxSize()) {
        Box(modifier = Modifier.padding(bottom = 10.dp, end = 25.dp).fillMaxWidth().height(50.dp)){
            filterButtons()
        }
        Column {
            val scrollState = rememberScrollState()
            Box(modifier = Modifier.fillMaxSize()){
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                ) {
                    for (patient in patients.value) {
                        patientBoxFeet(patient.name, patient.nif, patient.phone, patient.email)
                    }
                }
                VerticalScrollbar(
                    modifier = Modifier.align(Alignment.CenterEnd)
                        .padding(end = 8.5.dp)
                        .fillMaxHeight(),
                    adapter = rememberScrollbarAdapter(scrollState)
                )
            }
        }
    }
}
@Composable
fun patientBoxFeet(
    patientName: String,
    patientNif: String,
    patientPhone: Int,
    patientEmail: String
){
    Box(modifier = Modifier
        .padding(5.dp,5.dp,27.5.dp,5.dp)
        .fillMaxWidth()
        .height(50.dp)
        .clip(shape = RoundedCornerShape(5.dp))
        .background(Bright1)
        .clickable { }
    ){
        Row {
            Text(
                text = patientName,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp).fillMaxSize().weight(distantName).wrapContentSize(align = Alignment.CenterStart)
            )
            Text(
                text = patientNif,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp).fillMaxSize().weight(distantNIF).wrapContentSize(align = Alignment.CenterStart)
            )
            Text(
                text = patientPhone.toString(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp).fillMaxSize().weight(distantPhone).wrapContentSize(align = Alignment.CenterStart)
            )
            Text(
                text = patientEmail,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp).fillMaxSize().weight(distantEmail).wrapContentSize(align = Alignment.CenterStart)
            )
        }
    }
}
val optionsNIF = listOf("DNI", "NIE", "Pasaporte")
val expandedNIF = mutableStateOf(false)
val selectedOptionTextNIF = mutableStateOf("NIF")
@Composable
fun filterButtons() {
    Row(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {  },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Turquoise),
            modifier = Modifier.padding(5.dp,5.dp,0.dp,5.dp).fillMaxWidth().weight(distantName).wrapContentSize(align = Alignment.CenterStart)
        ){
            Text(
                text = "Pacientes",
                fontWeight = FontWeight.Bold,
                color = White,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.CenterStart)
            )
        }
        Box(
            modifier = Modifier.padding(5.dp,5.dp,0.dp,5.dp).fillMaxWidth().weight(distantNIF).wrapContentSize(align = Alignment.CenterStart).clip(shape = RoundedCornerShape(5.dp)).background(Turquoise).clickable { expandedNIF.value = !expandedNIF.value }
        ){
            Text(
                text = selectedOptionTextNIF.value,
                fontWeight = FontWeight.Bold,
                color = White,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.CenterStart)
            )
            DropdownMenu(
                expanded = expandedNIF.value,
                onDismissRequest = { expandedNIF.value = false },
                modifier = Modifier.width(150.dp).wrapContentSize(align = Alignment.Center)
            ) {
                optionsNIF.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionTextNIF.value = selectionOption
                            if (selectedOptionTextNIF.value == "DNI") { patients.value = getPatientsByDNI("DNI") }
                            if (selectedOptionTextNIF.value == "NIE") { patients.value = getPatientsByDNI("NIE")}
                            if (selectedOptionTextNIF.value == "Pasaporte") { patients.value = getPatientsByDNI("PAS") }
                            expandedNIF.value = false
                        },
                    ) {
                        Text(text = selectionOption,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Turquoise)
                    }
                }
            }
        }
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Turquoise),
            modifier = Modifier.padding(5.dp,5.dp,0.dp,5.dp).fillMaxWidth().weight(distantPhone).wrapContentSize(align = Alignment.CenterStart)
        ){
            Text(
                text = "Teléfono",
                fontWeight = FontWeight.Bold,
                color = White,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.CenterStart)
            )
        }
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Turquoise),
            modifier = Modifier.padding(5.dp).fillMaxWidth().weight(distantEmail).wrapContentSize(align = Alignment.CenterStart)
        ){
            Text(
                text = "Correo",
                fontWeight = FontWeight.Bold,
                color = White,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.CenterStart)
            )
        }
    }
}
