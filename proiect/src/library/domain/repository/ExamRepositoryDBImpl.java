package library.domain.repository;

import library.configuration.ConnectionFactory;
import library.domain.entity.Candidate;
import library.domain.entity.Evaluator;
import library.domain.entity.Exam;
import library.services.AuditService;
import library.services.CandidateService;
import library.services.EvaluatorService;
import library.tools.DataExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class ExamRepositoryDBImpl implements ExamRepository
{
    AuditService auditService = AuditService.getInstance();
    private static final String GET_ALL_EXAMS = "SELECT * FROM exams";
    private static final String CREATE_NEW_EXAM = "INSERT INTO exams VALUES (?,?,?,?,?)";

    @Override
    public List<Exam> getExams()
    {
        auditService.printAudit("getExamsDB");
        try (Connection connection = ConnectionFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_EXAMS);
            return DataExtractor.extractExamsFrom(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void addExam(Candidate candidate, double mathGrade, double infoGrade, Evaluator evaluator1, Evaluator evaluator2)
    {
        auditService.printAudit("addExamDB");
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(CREATE_NEW_EXAM);
            stmt.setInt(1, candidate.getId());
            stmt.setDouble(2, mathGrade);
            stmt.setDouble(3, infoGrade);
            stmt.setInt(4, evaluator1.getId());
            stmt.setInt(5, evaluator2.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addExam(Exam exam)
    {
        auditService.printAudit("addExamDB");
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(CREATE_NEW_EXAM);
            stmt.setInt(1, exam.getCandidate().getId());
            stmt.setDouble(2, exam.getMathGrade());
            stmt.setDouble(3, exam.getInfoGrade());
            stmt.setInt(4, exam.getEvaluator1().getId());
            stmt.setInt(5, exam.getEvaluator2().getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void readExamsFromFile(String fileName)
    {
        auditService.printAudit("readExamsFromFileDB");
        File file = new File(fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                Candidate candidate = CandidateService.getInstance().getCandidateById(Integer.parseInt(values[0]));
                Evaluator evaluator1 = EvaluatorService.getInstance().getEvaluatorById(Integer.parseInt(values[3]));
                Evaluator evaluator2 = EvaluatorService.getInstance().getEvaluatorById(Integer.parseInt(values[4]));
                addExam(candidate, Double.parseDouble(values[1]), Double.parseDouble(values[2]), evaluator1, evaluator2);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
