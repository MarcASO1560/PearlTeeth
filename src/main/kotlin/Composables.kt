import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import java.time.LocalDate

//Piezas importantes para la página principal.
var accountName = "adminconnombrelargo@pearlteeth.es"
@Composable
fun SideBar(onSectionSelected: (Section) -> Unit) {
    val ButtonColor1 = remember { mutableStateOf(Grey)}
    val ButtonColor2 = remember { mutableStateOf(Grey)}
    val ButtonColor3 = remember { mutableStateOf(Grey)}
    val ButtonColor4 = remember { mutableStateOf(Grey)}
    var currentSection by remember { mutableStateOf(Section.HOME) }
    MaterialTheme {
        Box(
            modifier = Modifier
                .shadow(10.dp)
                .fillMaxHeight()
                .width(120.dp)
                .background(Bright2)
        ){
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp)
            ) {
                Button(
                    onClick = {
                        currentSection = Section.CALENDAR
                        onSectionSelected(currentSection)
                        ButtonColor1.value = Turquoise
                        ButtonColor2.value = Grey
                        ButtonColor3.value = Grey
                        ButtonColor4.value = Grey

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ButtonColor1.value)
                ){
                    Column {
                        val Calendar = painterResource("drawable/baseline_calendar_month_white_48.png")
                        Image(
                            painter = Calendar,
                            contentDescription = "Add",
                            modifier = Modifier
                                .padding(0.dp,0.dp,0.dp,0.dp)
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                                .size(50.dp),
                        )
                    }
                }
                Button(
                    onClick = {
                        currentSection = Section.FILIAR
                        onSectionSelected(currentSection)
                        ButtonColor1.value = Grey
                        ButtonColor2.value = Turquoise
                        ButtonColor3.value = Grey
                        ButtonColor4.value = Grey
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColor2.value)
                ){
                    Column {
                        val addPerson = painterResource("drawable/baseline_person_add_white_48.png")
                        Image(
                            painter = addPerson,
                            contentDescription = "Add",
                            modifier = Modifier
                                .padding(0.dp,0.dp,10.dp,0.dp)
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                                .size(50.dp),
                        )
                    }
                }
                Button(
                    onClick = {
                        currentSection = Section.PATIENTS
                        onSectionSelected(currentSection)
                        ButtonColor1.value = Grey
                        ButtonColor2.value = Grey
                        ButtonColor3.value = Turquoise
                        ButtonColor4.value = Grey
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColor3.value)
                ){
                    Column {
                        val PersonSearch = painterResource("drawable/baseline_person_search_white_48.png")
                        Image(
                            painter = PersonSearch,
                            contentDescription = "Add",
                            modifier = Modifier
                                .padding(10.dp,0.dp,0.dp,0.dp)
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                                .size(50.dp),
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentSize(align = Alignment.BottomCenter)
            ){
                IconButton(
                    onClick = {}
                ){
                    Icon(
                        Icons.Filled.Info,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .wrapContentSize(Alignment.Center)
                            .size(40.dp),
                        tint = Lilac,
                        contentDescription = "info"
                    )
                }
            }
        }
    }
}
@Preview
@Composable
fun Content(currentSection: Section) {
    val image = painterResource("drawable/PearlTeethIcon.png")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),

        ){
        when (currentSection) {
            Section.HOME -> {
                Row(
                    modifier = Modifier
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = image,
                        alpha = 0.75F,
                        contentDescription = "Icon",
                        modifier = Modifier
                            .size(140.dp)
                            .padding(20.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text("PearlTeeth",
                        color = Grey,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .wrapContentSize(align = Alignment.CenterStart)
                    )
                }
            }
            Section.CALENDAR -> {
                Card (elevation = 3.dp, modifier = Modifier
                    .padding(30.dp)
                    .wrapContentSize(Alignment.Center)
                    .fillMaxSize()
                    .shadow(elevation = 30.dp,spotColor = Grey)
                    .clip(shape = RoundedCornerShape(15.dp)),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        var currentMonth by remember { mutableStateOf(LocalDate.now().month) }
                        var currentYear by remember { mutableStateOf(LocalDate.now().year) }
                        Box(modifier = Modifier.fillMaxSize().weight(1.75f).background(White)){
                            Card (elevation = 1.dp, modifier = Modifier
                                .padding(30.dp)
                                .wrapContentSize(Alignment.Center)
                                .fillMaxSize()
                                .clip(shape = RoundedCornerShape(10.dp)),
                            ){
                                Schedule(currentMonth, currentYear)
                            }
                        }
                        Box(modifier = Modifier.fillMaxSize().weight(1.25f).background(Bright1)){
                            Column(modifier = Modifier.fillMaxSize().align(Alignment.TopCenter)) {
                                Box(modifier = Modifier.shadow(5.dp).fillMaxWidth().height(75.dp).background(LightBlue))
                            }
                            dateAdd { TODO() }
                        }
                    }
                }
            }
            Section.FILIAR -> {
                Column {
                    Card (elevation = 1.dp, modifier = Modifier
                        .padding(30.dp,30.dp,30.dp,15.dp)
                        .wrapContentSize(Alignment.TopCenter)
                        .weight(6f)
                        .shadow(elevation = 30.dp,spotColor = Grey)
                        .clip(shape = RoundedCornerShape(15.dp)),
                    ){
                        Filiar()
                    }
                    Card (elevation = 3.dp, modifier = Modifier
                        .padding(30.dp,0.dp,30.dp,30.dp)
                        .wrapContentSize(Alignment.TopCenter)
                        .fillMaxWidth()
                        .height(80.dp)
                        .shadow(elevation = 30.dp,spotColor = Grey)
                        .clip(shape = RoundedCornerShape(15.dp)),
                    ){
                        addingUserButtons()
                    }
                }
            }
            Section.PATIENTS -> {
                Column{
                    var Buscador = remember { mutableStateOf("") }
                    Box(
                        modifier = Modifier
                            .padding(30.dp,30.dp,30.dp,15.dp)
                            .fillMaxWidth()
                            .shadow(elevation = 20.dp, spotColor = Turquoise)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(White)
                            .height(50.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        ) {
                            IconButton(onClick = {}
                            ) {
                                androidx.compose.material.Icon(
                                    Icons.Filled.Search,
                                    tint = Turquoise,
                                    modifier = Modifier.background(White),
                                    contentDescription = "CalendarDatePicker"
                                )
                            }
                            TextField(
                                value = Buscador.value,
                                onValueChange = { Buscador.value = it },
                                modifier = Modifier.fillMaxWidth(),
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = White,
                                    focusedIndicatorColor = Turquoise,
                                    cursorColor = Grey,
                                    textColor = Dark2,
                                    unfocusedIndicatorColor = White
                                ),
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                ),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        // Puedes manejar la acción "Done" si es necesario
                                    }
                                )
                            )
                        }
                    }
                    Card (elevation = 3.dp, modifier = Modifier
                        .padding(30.dp,0.dp,30.dp,30.dp)
                        .wrapContentSize(Alignment.Center)
                        .fillMaxSize()
                        .shadow(elevation = 30.dp,spotColor = Grey)
                        .clip(shape = RoundedCornerShape(15.dp)),
                    ) {
                        Text("ESTA PARTE SERÁ GRACIOSA DE IMPLEMENTAR", modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
                    }
                }
            }
        }
    }
}
@Composable
fun TopBar(onMenuClick: () -> Unit) {
    MaterialTheme {
        Box(modifier = Modifier
            .shadow(10.dp)
            .fillMaxWidth()
            .height(60.dp)
            .background(Turquoise)
        ){
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.CenterStart)
            ){
                IconButton(
                    onClick = {}
                ){
                    Icon(
                        Icons.Filled.ArrowBack,
                        tint = White,
                        contentDescription = "arrow1"
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.ArrowForward,
                        tint = White,
                        contentDescription = "arrow2"
                    )
                }
                Text(text = textoTituloPanel.value, color = White, fontSize = 16.sp, fontWeight = FontWeight.Bold,modifier = Modifier.padding(25.dp,0.dp,0.dp,0.dp).fillMaxHeight().wrapContentSize(align = Alignment.Center))
            }
            Row(
                modifier = Modifier
                    .padding(10.dp,10.dp,10.dp,10.dp)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.CenterEnd)
            ){
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = TurquoiseLite),
                    modifier = Modifier.width(400.dp).wrapContentSize(align = Alignment.CenterEnd)
                ){
                    Row {
                        Icon(
                            Icons.Filled.AccountBox,
                            tint = White,
                            contentDescription = "person",
                            modifier = Modifier
                                .size(40.dp)
                                .fillMaxSize()
                                .wrapContentSize(align = Alignment.CenterStart)
                        )
                        Text(
                            text = accountName,
                            fontWeight = FontWeight.Bold,
                            color = White,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(0.dp,0.dp,10.dp,0.dp)
                                .fillMaxHeight()
                                .wrapContentSize(align = Alignment.Center)
                        )
                    }
                }
                IconButton(
                    onClick = onMenuClick,
                    modifier = Modifier
                        .padding(10.dp,0.dp,0.dp,0.dp)
                ){
                    Icon(
                        Icons.Filled.Menu,
                        tint = White,
                        contentDescription = "menu",
                    )
                }
            }
        }
    }
}
//Piezas para el login.
@Composable
fun LoginPage(onClose: () -> Unit) {
    val icon = painterResource("drawable/PearlTeethIcon.png")
    val state = WindowState(width = 500.dp, height = 700.dp)
    var visibility by remember { mutableStateOf(true) }
    var isMaxScreen by remember { mutableStateOf(false) }
    Window(onCloseRequest = { onClose.invoke() }, title = "Login", visible = visibility, state = state, icon = icon, resizable = false) {
        Scaffold(
            topBar =
            {
                Box(
                    modifier = Modifier
                        .shadow(150.dp)
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(shape = RoundedCornerShape(0.dp,0.dp,250.dp,250.dp))
                        .background(TurquoiseLite)
                )
                val image = painterResource("drawable/PearlTeethIcon.png")
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize()
                        .wrapContentSize(Alignment.TopCenter)
                ){
                    Image(
                        painter = image,
                        contentDescription = "Icon",
                        modifier = Modifier
                            .size(140.dp)
                            .padding(20.dp)
                            .align(Alignment.CenterHorizontally)
                            .shadow(40.dp)
                    )
                    Card(elevation = 6.dp, modifier = Modifier
                        .padding(30.dp)
                        .wrapContentSize(Alignment.Center)
                        .fillMaxSize()
                        .shadow(50.dp)
                        .clip(shape = RoundedCornerShape(40.dp)),
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.TopCenter)
                                .padding(20.dp)
                        ){
                            Text(
                                text = "Welcome",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentSize(Alignment.Center))
                            var Mail = remember { mutableStateOf("") }
                            var Password = remember { mutableStateOf("") }
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(Alignment.Center)
                            ){
                                Column {
                                    TextField(
                                        value = Mail.value,
                                        onValueChange = { Mail.value = it },
                                        label = {
                                            Text(
                                                text = "Username",
                                                fontWeight = FontWeight.Bold,
                                                color = LightBlue
                                            ) },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp)
                                            .shadow(30.dp)
                                            .clip(shape = RoundedCornerShape(15.dp))
                                            .height(60.dp),
                                        colors = TextFieldDefaults.textFieldColors(
                                            backgroundColor = White,
                                            focusedIndicatorColor = LightBlue,
                                            cursorColor = Grey,
                                            textColor = Grey,
                                            unfocusedIndicatorColor = LightBlue
                                        )
                                    )
                                    var passwordVisible by rememberSaveable { mutableStateOf(false) }
                                    TextField(
                                        value = Password.value,
                                        onValueChange = { Password.value = it },
                                        label = {
                                            Text(
                                                text = "Password",
                                                fontWeight = FontWeight.Bold,
                                                color = LightBlue
                                            ) },
                                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                        trailingIcon = {
                                            val image = if (passwordVisible)
                                                Icons.Filled.Lock
                                            else Icons.Filled.Close

                                            val description = if (passwordVisible) "Hide password" else "Show password"

                                            IconButton(onClick = {passwordVisible = !passwordVisible}){
                                                Icon(imageVector  = image, description)
                                            }
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp)
                                            .shadow(30.dp)
                                            .clip(shape = RoundedCornerShape(15.dp))
                                            .height(60.dp),
                                        colors = TextFieldDefaults.textFieldColors(
                                            backgroundColor = White,
                                            focusedIndicatorColor = LightBlue,
                                            cursorColor = Grey,
                                            textColor = Grey,
                                            unfocusedIndicatorColor = LightBlue
                                        )
                                    )
                                    var isSecondWindowOpen by remember { mutableStateOf(false) }
                                    Button(
                                        onClick = {
                                            if (validateFields(Mail.value, Password.value)) {
                                                // Aquí puedes realizar la lógica de inicio de sesión
                                                isSecondWindowOpen = true
                                                isMaxScreen = !isMaxScreen
                                            } else {
                                            }
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(80.dp)
                                            .padding(30.dp,15.dp,30.dp,15.dp)
                                            .clip(shape = RoundedCornerShape(20.dp)),
                                        colors = ButtonDefaults.buttonColors(backgroundColor = Turquoise)
                                    ){
                                        Text(
                                            text = "Login",
                                            fontSize = 20.sp,
                                            color = White,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .wrapContentSize(Alignment.Center)
                                        )
                                    }
                                    if (isSecondWindowOpen) {
                                        menu(
                                            onClose = {
                                                isSecondWindowOpen = false
                                                visibility = true
                                            }
                                        )
                                        visibility = false
                                    }
                                }
                            }
                        }
                    }
                }
            },
            content = { padding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    color = White
                ){
                }
            }
        )
    }
}
