package PearlTeethDB

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class MainD {
	companion object {
		var conection: Connection? = null

		fun conecta(onSuccess: () -> Unit, onError: (SQLException) -> Unit) {
			try {
				Class.forName("com.microsoft.sqlserver:mysql-jdbc")
				conection = DriverManager.getConnection(
					"jdbc:mysql://192.168.56.104:3306/PearlTeeth", "admin", "1560"
				)
				println("You have been connected :)")
				onSuccess()
			} catch (e: ClassNotFoundException) {
				println("No driver detected! $e")
			} catch (sqle: SQLException) {
				println("ERROR: $sqle")
				onError(sqle)
			}
		}

		fun desconecta() {
			conection?.let {
				try {
					it.close()
					println("You have been disconnected ;) BYE BYE")
				} catch (sqle: SQLException) {
					println("CLOSE CONN ERROR: $sqle")
				}
			}
		}
	}
}