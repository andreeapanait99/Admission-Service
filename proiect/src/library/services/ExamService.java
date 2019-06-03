package library.services;

import library.configuration.ConnectionFactory;
import library.configuration.RepositoryConfig;
import library.domain.AdmissionException;
import library.domain.entity.Candidate;
import library.domain.entity.Exam;
import library.domain.repository.ExamRepository;
import library.tools.DataExtractor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static library.domain.ErrorCode.EXAM_NOT_FOUND;

public class ExamService
{
    private static final String GET_EXAM_BY_CANDIDATE_ID = "SELECT * FROM exams WHERE candidate_id=?";

    private static ExamService ourInstance = new ExamService();
    AuditService auditService = AuditService.getInstance();

    public static ExamService getInstance() {
        return ourInstance;
    }

    private ExamService() {
    }

    private ExamRepository examRepository = RepositoryConfig.getInstance().getExamRepository();

    public Exam searchExamByCandidate(Candidate candidate)
    {
        auditService.printAudit("searchExamByCandidate");
        List<Exam> exams = examRepository.getExams();
        int i;
        for (i = 0; i < exams.size(); i++)
        {
            if (candidate.getId() == exams.get(i).getCandidate().getId())
                break;
        }
        if (i == exams.size()) {
            throw new AdmissionException(EXAM_NOT_FOUND, "Could not find an exam for candidate with id: " + candidate.getId());
        }
        return exams.get(i);
    }

    public Exam getExamByCandidate(Candidate candidate)
    {
        auditService.printAudit("getEvaluatorByIdDB");
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(GET_EXAM_BY_CANDIDATE_ID);
            stmt.setInt(1, candidate.getId());
            ResultSet rs = stmt.executeQuery();
            return DataExtractor.extractSingleExamFrom(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
