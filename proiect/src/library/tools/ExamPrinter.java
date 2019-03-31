package library.tools;

import library.domain.entity.Exam;

public class ExamPrinter
{
    public void print(Exam exam)
    {
        System.out.println("Candidate ID: " + exam.getCandidate().getId());
        System.out.println("Name: " + exam.getCandidate().getName());
        System.out.println("Math grade: " + exam.getMathGrade());
        System.out.println("Info grade: " + exam.getInfoGrade());
        System.out.println("Evaluator 1: ");
        System.out.println("Name: " + exam.getEvaluator1().getName());
        System.out.println("Subject: " + exam.getEvaluator1().getSubject());
        System.out.println("Evaluator 2: ");
        System.out.println("Name: " + exam.getEvaluator2().getName());
        System.out.println("Subject: " + exam.getEvaluator2().getSubject());
    }
}
