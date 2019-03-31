package library.domain.repository;

import library.domain.entity.Candidate;
import library.domain.entity.Evaluator;
import library.domain.entity.Exam;

public class ExamRepositoryImpl implements ExamRepository
{
    private Exam[] exams  = new Exam[200];
    private int examsNumber = 0;

    @Override
    public Exam[] getExams()
    {
        return exams;
    }

    @Override
    public int getExamsNumber()
    {
        return examsNumber;
    }

    @Override
    public void addExam(Candidate candidate, double mathGrade, double infoGrade, Evaluator evaluator1, Evaluator evaluator2)
    {
        exams[examsNumber] = new Exam(candidate, mathGrade, infoGrade, evaluator1, evaluator2);
        examsNumber++;
    }

    @Override
    public void addExam(Exam exam)
    {
        exams[examsNumber] = new Exam(exam.getCandidate(), exam.getMathGrade(), exam.getInfoGrade(), exam.getEvaluator1(), exam.getEvaluator2());
        examsNumber++;
    }
}
