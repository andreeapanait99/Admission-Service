package library.configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup
{
    public void initDatabase() {
        try (Connection connection = ConnectionFactory.getConnection())
        {
            Statement stmt = connection.createStatement();
            stmt.execute("drop table if exists candidates;");
            stmt.execute(
                    "create table " +
                            "candidates (" +
                            "name varchar2(50)," +
                            "cnp varchar2(15)," +
                            "id number(4)," +
                            "rom_grade number(4,2)," +
                            "math_grade number(4,2)," +
                            "info_grade number(4,2)," +
                            "email varchar2(200)," +
                            "phone_number varchar2(200)," +
                            "PRIMARY KEY (id));");
            stmt.execute("drop table if exists evaluators;");
            stmt.execute(
                    "create table " +
                            "evaluators (" +
                            "name varchar2(50)," +
                            "CNP varchar2(15)," +
                            "id number(4)," +
                            "subject varchar2(30)," +
                            "PRIMARY KEY (id));");
            stmt.execute("drop table if exists exams;");
            stmt.execute(
                    "create table " +
                            "exams (" +
                            "candidate_id number(4)," +
                            "math_grade number(4,2)," +
                            "info_grade number(4,2)," +
                            "evaluator1_id number(4)," +
                            "evaluator2_id number(4)," +
                            "FOREIGN KEY (candidate_id) REFERENCES candidates (id)," +
                            "FOREIGN KEY (evaluator1_id) REFERENCES evaluators (id)," +
                            "FOREIGN KEY (evaluator2_id) REFERENCES evaluators (id)," +
                            "PRIMARY KEY (candidate_id, evaluator1_id, evaluator2_id));");
            stmt.close();
        }
        catch(SQLException e) {
            System.out.println("Could not finish database setup!");
        }
    }
}
