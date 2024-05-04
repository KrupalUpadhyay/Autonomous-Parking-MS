import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jdbcDemo {
    public static void main(String[] args) {
        try {
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Students", "Krupal", "Krupal666#@");//Establishing connection
            System.out.println("Connected With the database successfully");
            //Creating PreparedStatement object
            PreparedStatement preparedStatement=connection.prepareStatement("insert into Student values(1,Krupal,38)");
            //Setting values for Each Parameter
            preparedStatement.setString(1,"Dhairya");
                     preparedStatement.setString(2,"Mech");
                     preparedStatement.setString(3, "Mumbai");
                   
                     //Executing Query
                     preparedStatement.executeUpdate();
                     System.out.println("data inserted successfully");
            } catch (SQLException e) {
            System.out.println("Error while connecting to the database");
            }
    }

}