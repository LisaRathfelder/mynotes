package com.lisarathfelder.mynotes.server;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {


	private static Connection con = null;

	private static String localUrl = "jdbc:mysql://localhost:3306/mynotes?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";


	public static Connection connection() {

		/**
		 * Pruefen, ob es bisher eine Connection aufgebaut ist.
		 */

		if (con == null) {

			String url = null;

			String user = "root";
			String password = "rootroot";

			try {

					Class.forName("com.mysql.jdbc.Driver");
					url = localUrl;

					con = DriverManager.getConnection(url, user, password); 

			}

			/**
			 * Falls die Verbindung fehlschlaegt, soll die dazugehoerige
			 * Exception ausgegeben werden.
			 */

			catch (Exception e) {
				con = null;
				e.printStackTrace(); 
			}
		}

		/**
		 * Zurueckgegeben der Verbindung
		 */
		return con;
	}

}