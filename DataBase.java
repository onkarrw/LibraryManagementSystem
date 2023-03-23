package database;
import java.sql.*;

public class DataBase {


	

    public boolean login(String username, String password) throws SQLException {
    	
    	System.out.println("method getting called");
    	  String url = "jdbc:mysql://localhost:3306/CentralLibrary"; // Replace 'mydb' with your database name
          String user = "root"; // Replace 'root' with your database username
          String pass = "Onkar@21"; // Replace 'password' with your database password
          
  
              Connection conn = DriverManager.getConnection(url, user, pass);

              System.out.println("Connection to database established successfully!");

              String query = "SELECT COUNT(*) FROM ADMIN_PASSWORDS WHERE username = ? AND password = ?";
              try (PreparedStatement statement = conn.prepareStatement(query)) {
                  statement.setString(1, username);
                  statement.setString(2, password);
                  try (ResultSet resultSet = statement.executeQuery()) {
                      if (resultSet.next()) {
                          int count = resultSet.getInt(1);
                          return count == 1;
                      }
                  }
              }
              return false; // return true if there is a match, false otherwise
    }
    
    
    public void getbooks()
    {
    	
    }

   
}
