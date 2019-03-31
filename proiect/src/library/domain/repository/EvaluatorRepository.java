package library.domain.repository;

import library.domain.entity.Evaluator;

public interface EvaluatorRepository
{
    public Evaluator[] getEvaluators();

    public int getEvaluatorsNumber();

    public void addEvaluator(Evaluator evaluator);

    public void addEvaluator(String name, String CNP, String subject);
}
