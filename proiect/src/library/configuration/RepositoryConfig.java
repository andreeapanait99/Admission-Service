package library.configuration;

import library.domain.repository.*;

public class RepositoryConfig
{
    private final CandidateRepository candidateRepository = new CandidateRepositoryFileImpl();

    private final EvaluatorRepository evaluatorRepository = new EvaluatorRepositoryFileImpl();

    private final ExamRepository examRepository = new ExamRepositoryFileImpl();

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
