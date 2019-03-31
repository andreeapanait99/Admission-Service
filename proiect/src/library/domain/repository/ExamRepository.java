package library.domain.repository;

import library.domain.entity.Candidate;
import library.domain.entity.Evaluator;
import library.domain.entity.Exam;

public interface ExamRepository
{
    public Exam[] getExams();

    public int getExamsNumber();

    public void addExam(Exam exam);

    public void addExam(Candidate candidate, double mathGrade, double infoGrade, Evaluator evaluator1, Evaluator evaluator2);
}
