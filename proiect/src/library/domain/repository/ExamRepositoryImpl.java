package library.domain.repository;

import library.domain.entity.Candidate;
import library.domain.entity.Evaluator;
import library.domain.entity.Exam;

import java.util.ArrayList;
import java.util.List;

public class ExamRepositoryImpl implements ExamRepository
{
    private List<Exam> exams  = new ArrayList<>();

    @Override
    public List<Exam> getExams()
    {
        return exams;
    }


    @Override
    public void addExam(Candidate candidate, double mathGrade, double infoGrade, Evaluator evaluator1, Evaluator evaluator2)
    {
        exams.add(new Exam(candidate, mathGrade, infoGrade, evaluator1, evaluator2));
    }

    @Override
    public void addExam(Exam exam)
    {
        exams.add(new Exam(exam.getCandidate(), exam.getMathGrade(), exam.getInfoGrade(), exam.getEvaluator1(), exam.getEvaluator2()));
    }

    @Override
    public void readExamsFromFile(String fileName) {};
}
