/*
SQL 02 - Table
Exercise - SQL-table
before doing this exercise, you have to complete 0-SQL-installation-and-user-permissions
add JDBC to IntelliJ following this tutorial, till minute 3.35
here you can find the connector mentioned in the tutorial to download
create a class where you:
create a connection to the newdb database:
using the DriverManager
following this tutorial
using the following parameters:
DB_URL: jdbc:mysql://localhost:3306/newdb database
USER: the MySQL developer user
PASSWORD: the password you use for the MySQL developer user
create a table students if not already existing with
the following columns:
student_id as integer (10 digits) that is NOT NULL AUTO_INCREMENT
last_name as string (max 30 characters)
first_name as string (max 30 characters)
the constraint that students_pk is the PRIMARY KEY using only the column student_id
populate the table with 4 random students
remember that you need only to insert last_name and first_name because the primary key is auto-incremented
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        final String DB_URL, USERdeveloper, PASSWORD;
        DB_URL = "jdbc:mysql://localhost:3306/newdb";
        USERdeveloper = "root";
        PASSWORD = "1234";

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(DB_URL, USERdeveloper, PASSWORD);

            System.out.println("Connection successful");

            //da qui in poi non viene spiegato da nessuna parte
            Statement statement = connection.createStatement();

            String studentsTable = "CREATE TABLE IF NOT EXISTS students (" +
                    "student_id INTEGER(10) NOT NULL AUTO_INCREMENT," +
                    "last_name VARCHAR(30)," +
                    "first_name VARCHAR(30)," +
                    "CONSTRAINT students_pk PRIMARY KEY (student_id)" +
                    ");";

            statement.executeUpdate(studentsTable);

            System.out.println("Table created");

            String insertStudent1= "INSERT INTO students (last_name, first_name) VALUES ('Rossi', 'Maro');";
            String insertStudent2= "INSERT INTO students (last_name, first_name) VALUES ('Verdi', 'Sara');";
            String insertStudent3= "INSERT INTO students (last_name, first_name) VALUES ('Bianchi', 'Maria');";
            String insertStudent4= "INSERT INTO students (last_name, first_name) VALUES ('Neri', 'Francesco');";

            statement.executeUpdate(insertStudent1);
            statement.executeUpdate(insertStudent2);
            statement.executeUpdate(insertStudent3);
            statement.executeUpdate(insertStudent4);

            System.out.println("Added students");

        } catch (SQLException e) {
            System.out.println("CANNOT CONNECT TO THE DB OR MODIFY IT");
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
