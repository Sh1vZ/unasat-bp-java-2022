package sr.unasat.jdbc.crud.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionInstance {
    private Connection connection;
    private static ConnectionInstance conn_instance=null;
    private ConnectionInstance(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("De driver is geregistreerd!");
            String URL = "jdbc:mysql://localhost:3306/adres_boek";
            String USER = "root";
            String PASS = "root123!@#";
            this.connection = DriverManager.getConnection(URL, USER, PASS);
//            System.out.println(connection);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getInstance()
    {
        if (conn_instance == null)
            conn_instance = new ConnectionInstance();
        return conn_instance.connection;
    }

    public static void closeConnection() throws SQLException {
        if (conn_instance != null){
            conn_instance.connection.close();
            conn_instance=null;
        }
    }

}
