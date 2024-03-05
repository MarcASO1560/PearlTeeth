import PearlTeethDB.MainD
import androidx.compose.runtime.mutableStateOf
import java.sql.Date
import java.sql.SQLException
import java.sql.Statement
import java.time.LocalDate
import java.time.Period


var showError = mutableStateOf("")
fun findOutPatientName() {
    // Expresión regular para verificar la presencia de al menos una letra
    val regex = Regex(pattern = ".*[a-zA-Z]+.*")

    if (nombre.value.isEmpty() && apellidos.value.isEmpty()) {
        showError.value = "Los campos de nombre y apellidos están vacíos."
        showErrorOverlay.value = true
    } else if (nombre.value.isEmpty() || !nombre.value.matches(regex)) {
        // Verifica si el nombre está vacío o no contiene letras
        showError.value = "El campo del nombre está vacío o no es válido."
        showErrorOverlay.value = true
    } else if (apellidos.value.isEmpty() || !apellidos.value.matches(regex)) {
        // Verifica si los apellidos están vacíos o no contienen letras
        showError.value = "El campo de los apellidos está vacío o \nno es válido."
        showErrorOverlay.value = true
    } else {
        findOutPatientID() // Procede si ambos campos pasan las validaciones
    }
}
fun findOutPatientID(){
    val identification = NumIdentificacion.value
    val selectedOption = selectedOptionText.value

    when (selectedOption) {
        "DNI" -> isValidDNI(identification)
        "NIE" -> isValidNIE(identification)
        "Pasaporte" -> isValidPassport(identification)
        else -> {
            showError.value = "La opción seleccionada del NIF no es válida."
            showErrorOverlay.value = true
        }
    }
}

fun isValidDNI(dni: String) {
    val dniRegex = Regex("^[0-9]{8}[A-Z]{1}$")
    if (!dni.matches(dniRegex) || dni.last() != "TRWAGMYFPDXBNJZSQVHLCKE"[dni.dropLast(1).toInt() % 23]) {
        showError.value = "El DNI introducido no es valido."
        showErrorOverlay.value = true
    } else {
        // Llamada a la siguiente función si el DNI es válido
        findOutBdate()
    }
}

fun isValidNIE(nie: String) {
    val nieRegex = Regex("^[XYZ][0-9]{7}[A-Z]$")
    if (!nie.matches(nieRegex)) {
        showError.value = "El NIE introducido no es valido."
        showErrorOverlay.value = true
        return
    }

    val letters = "TRWAGMYFPDXBNJZSQVHLCKE"
    val convertedNie = nie.replaceFirst("[XYZ]", when (nie.first()) {
        'X' -> "0"
        'Y' -> "1"
        'Z' -> "2"
        else -> {
            showError.value = "El NIE introducido no es valido."
            showErrorOverlay.value = true
            return
        }
    })

    if (nie.last() != letters[convertedNie.drop(1).toInt() % 23]) {
        showError.value = "El NIE introducido no es valido."
        showErrorOverlay.value = true
    } else {
        // Llamada a la siguiente función si el NIE es válido
        findOutBdate()
    }
}

fun isValidPassport(passport: String) {
    if (passport.length !in 5..9) {
        showError.value = "El pasaporte introducido no es valido."
        showErrorOverlay.value = true
    } else {
        // Llamada a la siguiente función si el pasaporte es válido
        findOutBdate()
    }
}

// Suponiendo que la función findOutBdate() ya está definida
fun findOutBdate() {
    val currentDate = LocalDate.now()

    // Calcula la diferencia de periodos entre la fecha actual y la fecha de nacimiento
    val age = Period.between(selectedDateGlobal.value, currentDate).years

    // Comprueba si el paciente es mayor o menor de edad
    if (age >= 18) {
        findOutGenre()
    } else {
        showError.value = "El paciente no puede ser menor de edad.\nPor ahora..."
        showErrorOverlay.value = true
    }
}

fun findOutGenre(){
    if (selectedOptionTextSex.value != "Sexo") {
        findOutBldGroup()
    } else {
        showError.value = "Género no especificado."
        showErrorOverlay.value = true
    }
}

fun findOutBldGroup(){
    if (selectedOptionTextBlood.value != "Grupo Sanguíneo") {
        findOutAddress()
    } else {
        showError.value = "Grupo sanguíneo no especificado."
        showErrorOverlay.value = true
    }
}
fun findOutAddress(){
    val regex = Regex(pattern = ".*[a-zA-Z]+.*")
    if (Direccion.value.isEmpty() || !Direccion.value.matches(regex)){
        showError.value = "El campo de la dirección está vacío o no es válido."
        showErrorOverlay.value = true
    } else {
        findOutCity()
    }
}
fun findOutCity(){
    val regex = Regex(pattern = ".*[a-zA-Z]+.*")
    if (Poblacion.value.isEmpty() || !Poblacion.value.matches(regex)){
        showError.value = "El campo del pueblo o ciudad está vacío o no es válido."
        showErrorOverlay.value = true
    } else {
        findOutProvince()
    }
}
fun findOutProvince(){
    val regex = Regex(pattern = ".*[a-zA-Z]+.*")
    if (Provincia.value.isEmpty() || !Provincia.value.matches(regex)){
        showError.value = "El campo de la provincia está vacío o no es válido."
        showErrorOverlay.value = true
    } else {
        findOutCountry()
    }
}
fun findOutCountry(){
    val regex = Regex(pattern = ".*[a-zA-Z]+.*")
    if (Pais.value.isEmpty() || !Pais.value.matches(regex)){
        showError.value = "El campo del País está vacío o no es válido."
        showErrorOverlay.value = true
    } else {
        findOutCP()
    }
}
fun findOutCP(){
    val regex = Regex("^[0-9]{5}$")
    if (CP.value.isEmpty() || !CP.value.matches(regex)) {
        showError.value = "El campo del CP (Codigo Postal) está vacío \no no es válido."
        showErrorOverlay.value = true
    } else {
        findOutPhone()
    }
}

fun findOutPhone(){
    val regex = Regex("^[0-9]{9}$")
    if (TelefonoPrincipal.value.isEmpty() || !TelefonoPrincipal.value.matches(regex)) {
        showError.value = "El campo del telefono principal está vacío \no no es válido."
        showErrorOverlay.value = true
    } else {
        findOutPhoneAux()
    }
}

fun findOutPhoneAux(){
    val regex = Regex("^[0-9]{9}$")
    if (TelefonoAuxiliar.value.isEmpty()) {
        findOutEmail()
    } else if (!TelefonoAuxiliar.value.matches(regex)) {
        showError.value = "El campo del telefono Auxiliar no es válido."
        showErrorOverlay.value = true
    } else {
        findOutEmail()
    }
}
fun findOutEmail(){
    val regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
    if (Email.value.isEmpty() || !Email.value.matches(regex)) {
        showError.value = "El campo del email está vacío \no no es válido."
        showErrorOverlay.value = true
    } else {
        createPatient()
    }
}
fun createPatient(){
    if (selectedOptionText.value == "Pasaporte") {
        selectedOptionText.value = "PAS"
    }
    if (selectedOptionTextSex.value == "Hombre"){
        selectedOptionTextSex.value = "H"
    } else if (selectedOptionTextSex.value == "Mujer"){
        selectedOptionTextSex.value = "M"
    } else if (selectedOptionTextSex.value == "Otro"){
        selectedOptionTextSex.value = "O"
    }
    val query = """ 
        INSERT INTO patients (
            nif_type, 
            nif, 
            pt_name, 
            pt_surname, 
            genre, 
            bdate, 
            email, 
            phone, 
            phone_aux, 
            pt_address, 
            pt_cp, 
            pt_population,
            pt_province, 
            marital_status, 
            reg_date, 
            blood_type, 
            alergy, 
            medication, 
            disease, 
            active, 
            lopd, 
            accept_info, 
            include_mailing, 
            sms, 
            accept_email, 
            postal_shiping
        ) 
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """.trimIndent()

    try {
        MainD.conection?.prepareStatement(query).use { stmt ->
            if (stmt != null) {
                stmt.setString(1, selectedOptionText.value)
            }
            if (stmt != null) {
                stmt.setString(2, NumIdentificacion.value)
            }
            if (stmt != null) {
                stmt.setString(3, nombre.value)
            }
            if (stmt != null) {
                stmt.setString(4, apellidos.value)
            }
            if (stmt != null) {
                stmt.setString(5, selectedOptionTextSex.value)
            }
            if (stmt != null) {
                stmt.setDate(6, Date.valueOf(selectedDateGlobal.value))
            } // Suponiendo que tienes una variable para la fecha de nacimiento
            if (stmt != null) {
                stmt.setString(7, Email.value)
            }
            if (stmt != null) {
                stmt.setInt(8, TelefonoPrincipal.value.toInt())
            }
            if (TelefonoAuxiliar.value.isEmpty()) {
                if (stmt != null) {
                    stmt.setNull(9, java.sql.Types.INTEGER)
                } // Asume que el tipo de tu columna es INTEGER.
            } else {
                if (stmt != null) {
                    stmt.setInt(9, TelefonoAuxiliar.value.toInt())
                }
            }
            if (stmt != null) {
                stmt.setString(10, Direccion.value)
            }
            if (stmt != null) {
                stmt.setString(11, CP.value)
            }
            if (stmt != null) {
                stmt.setString(12, Poblacion.value)
            }
            if (stmt != null) {
                stmt.setString(13, Provincia.value)
            }
            if (stmt != null) {
                stmt.setString(14, null )
            }
            if (stmt != null) {
                stmt.setDate(15, java.sql.Date.valueOf(LocalDate.now()))
            } // Fecha de registro, asumiendo que es hoy
            if (stmt != null) {
                stmt.setString(16, selectedOptionTextBlood.value)
            }
            if (stmt != null) {
                stmt.setString(17, Alergias.value)
            }
            if (stmt != null) {
                stmt.setString(18, Medicaciones.value)
            }
            if (stmt != null) {
                stmt.setString(19, Enfermedades.value)
            }
            if (stmt != null) {
                stmt.setBoolean(20, true)
            } // Asumiendo que 'active' siempre es verdadero
            if (stmt != null) {
                stmt.setBoolean(21, LOPD.value)
            }
            if (stmt != null) {
                stmt.setBoolean(22, aceptInfo.value)
            }
            if (stmt != null) {
                stmt.setBoolean(23, includeMail.value)
            }
            if (stmt != null) {
                stmt.setBoolean(24, SMS.value)
            }
            if (stmt != null) {
                stmt.setBoolean(25, correoElectronico.value)
            }
            if (stmt != null) {
                stmt.setBoolean(26, EnvioPostal.value)
            }
            if (stmt != null) {
                stmt.executeUpdate()
            }
            println("Paciente creado con éxito.")
            totalNumPatients.value = getAllPatientsNumber()
            cleanDataPatient()
        }
    } catch (sqle: SQLException) {
        println("Error al crear el paciente: $sqle")
    }
}