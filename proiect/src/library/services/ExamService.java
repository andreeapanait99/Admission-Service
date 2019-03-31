package library.services;

import library.configuration.RepositoryConfig;
import library.domain.entity.Candidate;
import library.domain.entity.Exam;
import library.domain.repository.ExamRepository;

public class ExamService
{
    private static ExamService ourInstance = new ExamService();

    public static ExamService getInstance() {
        return ourInstance;
    }

    private ExamService() {
    }

    private ExamRepository examRepository = RepositoryConfig.getInstance().getExamRepository();

    public Exam searchExamByCandidate(Candidate candidate)
    {
        Exam exams[] = examRepository.getExams();
        int examsNumber = examRepository.getExamsNumber();
        int i;
        for (i = 0; i < examsNumber; i++)
        {
            if (candidate.getId() == exams[i].getCandidate().getId())
                break;
        }
        if (i == examsNumber) {
            System.out.println("There was no exam found for candidate with id " + candidate.getId() + "!");
        }
        return exams[i];
    }
}
