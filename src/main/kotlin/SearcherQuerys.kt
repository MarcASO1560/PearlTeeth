import PearlTeethDB.MainD
import java.sql.SQLException


fun getAllPatientsNumber(): Int {
    val query = "SELECT COUNT(*) AS total FROM patients"
    return try {
        val stmt = MainD.conection!!.prepareStatement(query)
        val rs = stmt.executeQuery()
        var totalPatients = 0
        if (rs.next()) {
            totalPatients = rs.getInt("total")
        }
        totalPatients
    } catch (sqle: SQLException) {
        println("Error: $sqle")
        -1
    }
}
fun getPatientsByDNI(NIF: String?): List<Patient> {
    val patients = mutableListOf<Patient>()
    // La consulta SQL base
    val query = "SELECT pt_name, nif, phone, email FROM patients WHERE nif_type LIKE ?"

    try {
        val stmt = PearlTeethDB.MainD.conection!!.prepareStatement(query)
        NIF?.let {
            stmt.setString(1, "%$it%")
        }
        val rs = stmt.executeQuery()
        while (rs.next()) {
            val patient = Patient(
                rs.getString("pt_name"),
                rs.getString("nif"),
                rs.getInt("phone"),
                rs.getString("email")
            )
            patients.add(patient)
        }
    } catch (sqle: SQLException) {
        println("Error al obtener los pacientes por DNI: $sqle")
    }
    return patients
}
fun getAllPatients(): List<Patient> {
    val patients = mutableListOf<Patient>()
    val query = "SELECT pt_name, nif, phone, email FROM patients"
    try {
        val stmt = PearlTeethDB.MainD.conection!!.prepareStatement(query)
        val rs = stmt.executeQuery()
        while (rs.next()) {
            val patient = Patient(
                rs.getString("pt_name"),
                rs.getString("nif"),
                rs.getInt("phone"),
                rs.getString("email")
            )
            patients.add(patient)
        }
    } catch (sqle: SQLException) {
        println("Error: $sqle")
    }
    return patients
}
