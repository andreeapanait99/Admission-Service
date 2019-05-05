package library.domain.repository;

import library.domain.entity.Candidate;
import library.domain.entity.Evaluator;
import library.domain.entity.Exam;

import java.util.List;

public interface ExamRepository
{
    public List<Exam> getExams();

    public void addExam(Exam exam);

    public void addExam(Candidate candidate, double mathGrade, double infoGrade, Evaluator evaluator1, Evaluator evaluator2);

    public void readExamsFromFile(String fileName);
}
