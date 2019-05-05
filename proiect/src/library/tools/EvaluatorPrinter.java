package library.tools;

import library.domain.entity.Evaluator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class EvaluatorPrinter
{
    public void print(Evaluator evaluator)
    {
        System.out.println("ID: " + evaluator.getId());
        System.out.println("Name: " + evaluator.getName());
        System.out.println("CNP: " + evaluator.getCNP());
        System.out.println("Subject: " + evaluator.getSubject());
    }

    public void printFile(List<Evaluator> evaluators, String fileName)
    {
        File file = new File(fileName);
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(file, false)))
        {
            for(int i = 0; i < evaluators.size(); i++) {
                printWriter.println("ID: " + evaluators.get(i).getId());
                printWriter.println("Name: " + evaluators.get(i).getName());
                printWriter.println("CNP: " + evaluators.get(i).getCNP());
                printWriter.println("Subject: " + evaluators.get(i).getSubject());
                printWriter.println();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
