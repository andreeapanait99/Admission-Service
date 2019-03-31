package library.tools;

import library.domain.entity.Evaluator;

public class EvaluatorPrinter
{
    public void print(Evaluator evaluator)
    {
        System.out.println("ID: " + evaluator.getId());
        System.out.println("Name: " + evaluator.getName());
        System.out.println("CNP: " + evaluator.getCNP());
        System.out.println("ID: " + evaluator.getId());
        System.out.println("Subject: " + evaluator.getSubject());
    }
}
