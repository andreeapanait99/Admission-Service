package library.domain.repository;

import library.domain.entity.Candidate;
import library.domain.entity.Evaluator;
import library.domain.entity.Exam;
import library.services.AuditService;
import library.services.CandidateService;
import library.services.EvaluatorService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExamRepositoryFileImpl implements ExamRepository
{
    private List<Exam> exams  = new ArrayList<>();
    AuditService auditService = AuditService.getInstance();

    @Override
    public List<Exam> getExams()
    {
        return exams;
    }


    @Override
    public void addExam(Candidate candidate, double mathGrade, double infoGrade, Evaluator evaluator1, Evaluator evaluator2)
    {
        exams.add(new Exam(candidate, mathGrade, infoGrade, evaluator1, evaluator2));
    }

    @Override
    public void addExam(Exam exam)
    {
        exams.add(new Exam(exam.getCandidate(), exam.getMathGrade(), exam.getInfoGrade(), exam.getEvaluator1(), exam.getEvaluator2()));
    }

    @Override
    public void readExamsFromFile(String fileName)
    {
        auditService.printAudit("readExamsFromFile");
        File file = new File(fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                Candidate candidate = CandidateService.getInstance().searchCandidateById(Integer.parseInt(values[0]));
                Evaluator evaluator1 = EvaluatorService.getInstance().searchEvaluatorById(Integer.parseInt(values[3]));
                Evaluator evaluator2 = EvaluatorService.getInstance().searchEvaluatorById(Integer.parseInt(values[4]));
                addExam(candidate, Double.parseDouble(values[1]), Double.parseDouble(values[2]), evaluator1, evaluator2);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
