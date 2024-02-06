package PearlTeethDB
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class MainD {
	init {
		conecta()
	}
	private fun conecta() {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver")
			} catch (exc: ClassNotFoundException) {
				println("No driver detected! $exc")
			}
			conection = DriverManager.getConnection("jdbc:mysql://192.168.56.104:3306/PearlTeeth", "admin", "1560")
			println("You have been connected :)")
		} catch (sqle: SQLException) {
			println("ERROR: $sqle")
		}
	}
	companion object {
		protected var conection: Connection? = null
		fun desconecta() {
			if (conection != null) {
				try {
					conection!!.close()
					println("You have been desconnected ;) BYE BYE ")
				} catch (sqle: SQLException) {
					println("CLOSE CONN ERROR: $sqle")
				}
			}
		}
	}
}
