package library.tools;

import library.domain.entity.Exam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

    public void printFile(List<Exam> exams, String fileName)
    {
        File file = new File(fileName);
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(file, false)))
        {
            for(int i = 0; i < exams.size(); i++) {
                printWriter.println("Candidate ID: " + exams.get(i).getCandidate().getId());
                printWriter.println("Name: " + exams.get(i).getCandidate().getName());
                printWriter.println("Math grade: " + exams.get(i).getMathGrade());
                printWriter.println("Info grade: " + exams.get(i).getInfoGrade());
                printWriter.println("Evaluator 1: ");
                printWriter.println("Name: " + exams.get(i).getEvaluator1().getName());
                printWriter.println("Subject: " + exams.get(i).getEvaluator1().getSubject());
                printWriter.println("Evaluator 2: ");
                printWriter.println("Name: " + exams.get(i).getEvaluator2().getName());
                printWriter.println("Subject: " + exams.get(i).getEvaluator2().getSubject());
                printWriter.println();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
