package library.tools;

import library.domain.entity.Candidate;
import library.domain.entity.Evaluator;
import library.domain.entity.Exam;
import library.services.CandidateService;
import library.services.EvaluatorService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataExtractor {

    public static Candidate extractSingleCandidateFrom(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Candidate candidate = new Candidate(rs.getString("name"), rs.getString("cnp"),
                    rs.getFloat("rom_grade"), rs.getFloat("math_grade"),
                    rs.getFloat("info_grade"), rs.getInt("id"),
                    rs.getString("email"), rs.getString("phone_number"));
            return candidate;
        } else {
            return null;
        }
    }

    public static List<Candidate> extractCandidatesFrom(ResultSet rs) throws SQLException {
        List<Candidate> candidates = new ArrayList<>();
        while (rs.next()) {
            Candidate candidate = new Candidate(rs.getString("name"), rs.getString("cnp"),
                    rs.getFloat("rom_grade"), rs.getFloat("math_grade"),
                    rs.getFloat("info_grade"), rs.getInt("id"),
                    rs.getString("email"), rs.getString("phone_number"));
            candidates.add(candidate);
        }
        return candidates;
    }

    public static Evaluator extractSingleEvaluatorFrom(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Evaluator evaluator = new Evaluator(rs.getString("name"), rs.getString("cnp"),
                    rs.getInt("id"), rs.getString("subject"));
            return evaluator;
        } else {
            return null;
        }
    }

    public static List<Evaluator> extractEvaluatorsFrom(ResultSet rs) throws SQLException {
        List<Evaluator> evaluators = new ArrayList<>();
        while (rs.next()) {
            Evaluator evaluator = new Evaluator(rs.getString("name"), rs.getString("cnp"),
            rs.getInt("id"), rs.getString("subject"));
            evaluators.add(evaluator);
        }
        return evaluators;
    }

    public static Exam extractSingleExamFrom(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Candidate candidate = CandidateService.getInstance().getCandidateById(rs.getInt("candidate_id"));
            Evaluator evaluator1 = EvaluatorService.getInstance().getEvaluatorById(rs.getInt("evaluator1_id"));
            Evaluator evaluator2 = EvaluatorService.getInstance().getEvaluatorById(rs.getInt("evaluator2_id"));
            Exam exam = new Exam(candidate, rs.getFloat("math_grade"),
                    rs.getFloat("info_grade"), evaluator1, evaluator2);
            return exam;
        } else {
            return null;
        }
    }

    public static List<Exam> extractExamsFrom(ResultSet rs) throws SQLException {
        List<Exam> evaluators = new ArrayList<>();
        while (rs.next()) {
            Candidate candidate = CandidateService.getInstance().getCandidateById(rs.getInt("candidate_id"));
            Evaluator evaluator1 = EvaluatorService.getInstance().getEvaluatorById(rs.getInt("evaluator1_id"));
            Evaluator evaluator2 = EvaluatorService.getInstance().getEvaluatorById(rs.getInt("evaluator2_id"));
            Exam exam = new Exam(candidate, rs.getFloat("math_grade"),
                    rs.getFloat("info_grade"), evaluator1, evaluator2);
            evaluators.add(exam);
        }
        return evaluators;
    }
}