package library.domain.repository;

import library.domain.entity.Evaluator;

import java.util.ArrayList;
import java.util.List;

public class EvaluatorRepositoryImpl implements EvaluatorRepository
{
    private List<Evaluator> evaluators = new ArrayList<>();

    @Override
    public List<Evaluator> getEvaluators()
    {
        return evaluators;
    }

    @Override
    public void addEvaluator(String name, String CNP, int id, String subject)
    {
        evaluators.add(new Evaluator(name, CNP, evaluators.size(), subject));
    }

    @Override
    public void addEvaluator(Evaluator evaluator)
    {
        evaluators.add(new Evaluator(evaluator.getName(), evaluator.getCNP(), evaluators.size(), evaluator.getSubject()));
    }

    @Override
    public void readEvaluatorsFromFile(String fileName) {};
}
