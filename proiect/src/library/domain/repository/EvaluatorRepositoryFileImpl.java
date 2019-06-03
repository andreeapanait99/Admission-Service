package library.domain.repository;

import library.domain.entity.Candidate;
import library.domain.entity.Evaluator;
import library.services.AuditService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EvaluatorRepositoryFileImpl implements EvaluatorRepository
{
    private List<Evaluator> evaluators = new ArrayList<>();
    AuditService auditService = AuditService.getInstance();

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
        evaluators.add(new Evaluator(evaluator.getName(), evaluator.getCNP(), evaluator.getId(), evaluator.getSubject()));
    }

    @Override
    public void readEvaluatorsFromFile(String fileName)
    {
        auditService.printAudit("readEvaluatorsFromFile");
        File file = new File(fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                Evaluator newEvaluator = new Evaluator(values[0], values[1], Integer.parseInt(values[2]), values[3]);
                addEvaluator(newEvaluator);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
