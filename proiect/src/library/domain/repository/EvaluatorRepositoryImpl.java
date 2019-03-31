package library.domain.repository;

import library.domain.entity.Evaluator;

public class EvaluatorRepositoryImpl implements EvaluatorRepository
{
    private Evaluator[] evaluators = new Evaluator[50];
    private int evaluatorsNumber = 0;

    @Override
    public Evaluator[] getEvaluators()
    {
        return evaluators;
    }

    @Override
    public int getEvaluatorsNumber()
    {
        return evaluatorsNumber;
    }

    @Override
    public void addEvaluator(String name, String CNP, String subject)
    {
        evaluators[evaluatorsNumber] = new Evaluator(name, CNP, this.evaluatorsNumber, subject);
        this.evaluatorsNumber++;
    }

    @Override
    public void addEvaluator(Evaluator evaluator)
    {
        evaluators[evaluatorsNumber] = new Evaluator(evaluator.getName(), evaluator.getCNP(), this.evaluatorsNumber, evaluator.getSubject());
        this.evaluatorsNumber++;
    }
}
