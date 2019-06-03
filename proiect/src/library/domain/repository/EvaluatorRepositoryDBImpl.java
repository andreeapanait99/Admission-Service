package library.domain.repository;

import library.configuration.ConnectionFactory;
import library.domain.entity.Evaluator;
import library.services.AuditService;
import library.tools.DataExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class EvaluatorRepositoryDBImpl implements EvaluatorRepository
{
    AuditService auditService = AuditService.getInstance();
    private static final String GET_ALL_EVALUATORS = "SELECT * FROM evaluators";
    private static final String CREATE_NEW_EVALUATOR = "INSERT INTO evaluators VALUES (?,?,?,?)";

    public List<Evaluator> getEvaluators()
    {
        auditService.printAudit("getEvaluatorsDB");
        try (Connection connection = ConnectionFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_EVALUATORS);
            return DataExtractor.extractEvaluatorsFrom(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void addEvaluator(Evaluator evaluator)
    {
        auditService.printAudit("addEvaluatorDB");
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(CREATE_NEW_EVALUATOR);
            stmt.setString(1, evaluator.getName());
            stmt.setString(2, evaluator.getCNP());
            stmt.setInt(3, evaluator.getId());
            stmt.setString(4, evaluator.getSubject());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addEvaluator(String name, String CNP, int id, String subject)
    {
        auditService.printAudit("addEvaluatorDB");
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(CREATE_NEW_EVALUATOR);
            stmt.setString(1, name);
            stmt.setString(2, CNP);
            stmt.setInt(3, id);
            stmt.setString(4, subject);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void readEvaluatorsFromFile(String fileName)
    {
        auditService.printAudit("readEvaluatorsFromFileDB");
        File file = new File(fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                Evaluator newEvaluator = new Evaluator(values[0], values[1], Integer.parseInt(values[2]), values[3]);
                addEvaluator(newEvaluator);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
