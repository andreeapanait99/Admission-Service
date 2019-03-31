package library.configuration;

import library.domain.repository.*;

public class RepositoryConfig
{
    private final CandidateRepository candidateRepository = new CandidateRepositoryImpl();

    private final EvaluatorRepository evaluatorRepository = new EvaluatorRepositoryImpl();

    private final ExamRepository examRepository = new ExamRepositoryImpl();

    private static RepositoryConfig ourInstance = new RepositoryConfig();

    public static RepositoryConfig getInstance()
    {
        return ourInstance;
    }

    public CandidateRepository getCandidateRepository()
    {
        return candidateRepository;
    }

    private RepositoryConfig() {}

    public EvaluatorRepository getEvaluatorRepository() {
        return evaluatorRepository;
    }

    public ExamRepository getExamRepository() {
        return examRepository;
    }
}
