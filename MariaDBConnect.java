import java.sql.*;
import java.util.Scanner;

public class MariaDBConnect {
	public static void main(String[] args){
		String url = "jdbc:mariadb://localhost:3306/nation";
		String user = "lucie-hostiuk";
		String password = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Language to Search:");
		String language = sc.nextLine();
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			System.out.println("Connection to MariaDB established succesfully!");
			System.out.println("Countries where " + language + " is an official or unofficial language:");
			System.out.println("------------------");
			ResultSet rs = stmt.executeQuery("select c.name, l.language from countries c inner join country_languages cl on c.country_id = cl.country_id inner join languages l on cl.language_id = l.language_id where l.language = '" + language + "'");
			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}
		} catch (SQLException e) {
			System.err.println("Database connection failed! :(");
			e.printStackTrace();
		}
	}
}
