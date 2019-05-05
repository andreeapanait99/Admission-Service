package library.domain.repository;

import library.domain.entity.Evaluator;

import java.util.List;

public interface EvaluatorRepository
{
    public List<Evaluator> getEvaluators();

    public void addEvaluator(Evaluator evaluator);

    public void addEvaluator(String name, String CNP, String subject);

    public void readEvaluatorsFromFile(String fileName);
}
