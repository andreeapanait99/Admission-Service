package library.services;

import library.configuration.RepositoryConfig;
import library.domain.AdmissionException;
import library.domain.entity.Candidate;
import library.domain.entity.Exam;
import library.domain.repository.ExamRepository;

import java.util.List;

import static library.domain.ErrorCode.EXAM_NOT_FOUND;

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
        List<Exam> exams = examRepository.getExams();
        int i;
        for (i = 0; i < exams.size(); i++)
        {
            if (candidate.getId() == exams.get(i).getCandidate().getId())
                break;
        }
        if (i == exams.size()) {
            throw new AdmissionException(EXAM_NOT_FOUND, "Could not find an exam for candidate with id: " + candidate.getId());
        }
        return exams.get(i);
    }
}
