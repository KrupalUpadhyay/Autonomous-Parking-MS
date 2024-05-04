import com.fazecast.jSerialComm.*; // Import jSerialComm library
//import com.fazecast.jSerialComm.SerialPort.*;
import java.sql.*; // Import for JDBC connection

public class ParkingDataHandler {

    public static void main(String[] args) throws Exception {
    	
    	System.out.println(System.getProperty("java.class.path"));


        // Database connection details (replace with yours)
        String url = "jdbc:postgresql://localhost:5432/ytdemo";
        String user = "postgres";
        String password = "1234";

        Connection connection = null;
        PreparedStatement statement = null;
        SerialPort serialPort = null;

        try {
            // Connect to database
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);

            // Open serial port (replace with your Arduino's port)
            serialPort = SerialPort.getCommPort("COM7");
            if (serialPort.openPort()) {
                // Set serial port parameters
                serialPort.setComPortParameters(9600,8,1,0,false);

                System.out.println("Serial port connected successfully.");

                while (true) {
                	// Read data from serial port
                    byte[] buffer = new byte[128]; // Assuming data length is less than 128 bytes
                    int bytesRead = serialPort.readBytes(buffer, buffer.length);

                    if (bytesRead > 0) {
                        // Process the read bytes
                        String data = new String(buffer, 0, bytesRead); // Convert bytes to String
                        System.out.println("Received Data: " + data);
                        // Parse the data (assuming comma-separated format)
                        String[] lines = data.split("\n"); // Split by newline

                        for (String line : lines) {
                            if (line.isEmpty()) {
                                continue; // Skip empty lines
                            }
                            line = line.trim();
                            String[] parts = line.split(",");
                            if (parts.length == 2) { // Check for unexpected array length
                            
                            int slotNumber = Integer.parseInt(parts[0]);
                            int available = Integer.parseInt(parts[1]);
                            boolean check=false;
                            if(available==0) {
                            	check=false;
                            }
                            else {
                            	check=true;
                            }
                          
                            System.out.println(check);

//                             Update database logic using slotNumber and available...
                            String sql = "UPDATE parking_data SET available = ? WHERE slot_number = ?";
                            statement = connection.prepareStatement(sql);
                            statement.setBoolean(1, check);
                            statement.setInt(2, slotNumber);
                            statement.executeUpdate();

                            System.out.println("Slot " + slotNumber + " - Available: " + check + " - Data Updated");
                            }
                        }
                    }
                }
            } else {
                System.err.println("Failed to open serial port!");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print any errors for troubleshooting
        } finally {
            // Close resources
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (serialPort != null) {
                serialPort.closePort();
            }
        }
    }
}
