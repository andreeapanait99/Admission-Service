import com.sun.glass.ui.Application;
import library.configuration.ConnectionFactory;
import library.configuration.DatabaseSetup;
import library.configuration.RepositoryConfig;
import library.configuration.ToolConfig;
import library.domain.AdmissionException;
import library.domain.entity.Evaluator;
import library.domain.entity.Exam;
import library.services.AuditService;
import library.services.CandidateService;
import library.domain.entity.Candidate;
import library.services.EvaluatorService;
import library.services.ExamService;
import library.tools.CandidatePrinter;
import library.tools.EvaluatorPrinter;
import library.tools.ExamPrinter;
import library.tools.TestData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static library.domain.ErrorCode.INVALID_EMAIL;
import static library.domain.ErrorCode.INVALID_PHONE_NUMBER;

public class Main
{
    public static void main(String[] args)
    {
        DatabaseSetup databaseSetup = new DatabaseSetup();
        databaseSetup.initDatabase();

        String name, cnp, email, phoneNumber, subject;
        double infoGrade, mathGrade, romGrade;
        int candidateId, evaluatorId, id;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to our Admission Service!");

        CandidateService candidateService = CandidateService.getInstance();
        ToolConfig toolConfig = ToolConfig.getInstance();
        RepositoryConfig repositoryConfig = RepositoryConfig.getInstance();
        EvaluatorService evaluatorService = EvaluatorService.getInstance();
        ExamService examService = ExamService.getInstance();

        repositoryConfig.getCandidateRepository().readCandidatesFromFile("D:/pao/Admission-Service/proiect/src/library/tools/files/candidates.csv");
        repositoryConfig.getEvaluatorRepository().readEvaluatorsFromFile("D:/pao/Admission-Service/proiect/src/library/tools/files/evaluators.csv");
        repositoryConfig.getExamRepository().readExamsFromFile("D:/pao/Admission-Service/proiect/src/library/tools/files/exams.csv");

        System.out.println("0 - Stop");
        System.out.println("1 - Add candidate");
        System.out.println("2 - Display candidates");
        System.out.println("3 - Add evaluator");
        System.out.println("4 - Display evaluators");
        System.out.println("5 - Add exam grades for a candidate");
        System.out.println("6 - Display exam grades for all candidates");
        System.out.println("7 - Search candidate by ID");
        System.out.println("8 - Search candidate by name");
        System.out.println("9 - Search evaluator by ID");
        System.out.println("10 - Search evaluator by name");
        System.out.println("11 - Show exam results for certain candidate");
        System.out.println("12 - Display candidates in alphabetical order");
        System.out.println("13 - Delete candidate");
        System.out.println("14 - Update candidate name");
        int option = scanner.nextInt();
        while (option != 0)
        {
            switch(option)
            {
                case 1:
                    System.out.println("Enter candidate name, cnp, id, Bac grades (rom, math, info), email and phone number:");
                    scanner.nextLine();
                    name = scanner.nextLine();
                    cnp = scanner.nextLine();
                    id = scanner.nextInt();
                    romGrade = scanner.nextDouble();
                    mathGrade = scanner.nextDouble();
                    infoGrade = scanner.nextDouble();
                    scanner.nextLine();
                    email = scanner.nextLine();
                    boolean valid = toolConfig.getEmailValidator().validate(email);
                    if (!valid)
                    {
                        throw new AdmissionException(INVALID_EMAIL, "The email entered is not valid!");
                    }
                    phoneNumber = scanner.nextLine();
                    valid = toolConfig.getPhoneNumberValidator().validate(phoneNumber);
                    if (!valid)
                    {
                        throw new AdmissionException(INVALID_PHONE_NUMBER, "The phone number entered is not valid!");
                    }
                    repositoryConfig.getCandidateRepository().addCandidate(name, cnp, id, romGrade, mathGrade, infoGrade, email, phoneNumber);
                    break;
                case 2:
                    List<Candidate> candidates;
                    candidates = repositoryConfig.getCandidateRepository().getCandidates();
                    CandidatePrinter candidatePrinter = toolConfig.getCandidatePrinter();
                    if (candidates.size() == 0)
                    {
                        System.out.println("There are no candidates!");
                        break;
                    }
                    candidatePrinter.printFile(candidates, "D:/pao/Admission-Service/proiect/src/library/tools/files/candidatesOut.csv");
                    break;
                case 3:
                    System.out.println("Enter evaluator name, cnp, id and subject:");
                    scanner.nextLine();
                    name = scanner.nextLine();
                    cnp = scanner.nextLine();
                    id = scanner.nextInt();
                    subject = scanner.nextLine();
                    repositoryConfig.getEvaluatorRepository().addEvaluator(name, cnp, id, subject);
                    break;
                case 4:
                    List<Evaluator> evaluators = repositoryConfig.getEvaluatorRepository().getEvaluators();
                    EvaluatorPrinter evaluatorPrinter = toolConfig.getEvaluatorPrinter();
                    if (evaluators.size() == 0)
                    {
                        System.out.println("There are no evaluators!");
                        break;
                    }
                    evaluatorPrinter.printFile(evaluators, "D:/pao/Admission-Service/proiect/src/library/tools/files/evaluatorsOut.csv");
                    break;
                case 5:
                    System.out.println("Enter candidate id, math grade, info grade, evaluator 1 and evaluator 2 ids:");
                    candidateId = scanner.nextInt();
                    Candidate candidate = candidateService.searchCandidateById(candidateId);
                    mathGrade = scanner.nextDouble();
                    infoGrade = scanner.nextDouble();
                    evaluatorId = scanner.nextInt();
                    Evaluator evaluator1 = evaluatorService.searchEvaluatorById(evaluatorId);
                    evaluatorId = scanner.nextInt();
                    Evaluator evaluator2 = evaluatorService.searchEvaluatorById(evaluatorId);
                    repositoryConfig.getExamRepository().addExam(candidate, mathGrade, infoGrade, evaluator1, evaluator2);
                    break;
                case 6:
                    List<Exam> exams = repositoryConfig.getExamRepository().getExams();
                    ExamPrinter examPrinter = toolConfig.getExamPrinter();
                    if (exams.size() == 0)
                    {
                        System.out.println("There are no exams!");
                        break;
                    }
                    examPrinter.printFile(exams, "D:/pao/Admission-Service/proiect/src/library/tools/files/examsOut.csv");
                    break;
                case 7:
                    System.out.println("Enter candidate id:");
                    candidateId = scanner.nextInt();
                    candidate = candidateService.getCandidateById(candidateId);
                    System.out.println("Candidate found!");
                    toolConfig.getCandidatePrinter().print(candidate);
                    break;
                case 8:
                    System.out.println("Enter candidate name:");
                    scanner.nextLine();
                    name = scanner.nextLine();
                    String[] result = candidateService.searchCandidatesByASpecificPattern(name);
                    if (result[0] == null)
                    {
                        System.out.println("There are no candidates matching name " + name);
                    }
                    else
                    {
                        System.out.println("Candidates found matching name " + name + ":");
                        int i = 0;
                        while (result[i] != null)
                        {
                            System.out.println(result[i]);
                            i++;
                        }
                    }
                    break;
                case 9:
                    System.out.println("Enter evaluator id:");
                    evaluatorId = scanner.nextInt();
                    Evaluator evaluator = evaluatorService.getEvaluatorById(evaluatorId);
                    if (evaluator == null) break;
                    System.out.println("Evaluator found!");
                    toolConfig.getEvaluatorPrinter().print(evaluator);
                    break;
                case 10:
                    System.out.println("Enter evaluator name:");
                    scanner.nextLine();
                    name = scanner.nextLine();
                    result = evaluatorService.searchEvaluatorsByASpecificPattern(name);
                    if (result[0] == null)
                    {
                        System.out.println("There are no evaluators matching name " + name);
                    }
                    else
                    {
                        System.out.println("Evaluators found matching name " + name + ":");
                        int i = 0;
                        while (result[i] != null)
                        {
                            System.out.println(result[i]);
                            i++;
                        }
                    }
                    break;
                case 11:
                    System.out.println("Enter candidate id:");
                    candidateId = scanner.nextInt();
                    candidate = candidateService.getCandidateById(candidateId);
                    Exam exam = examService.getExamByCandidate(candidate);
                    System.out.println("Exam found!");
                    toolConfig.getExamPrinter().print(exam);
                    break;
                case 12:
                    candidates = repositoryConfig.getCandidateRepository().getCandidates();
                    candidatePrinter = toolConfig.getCandidatePrinter();
                    SortedMap<String, Candidate> sortedCandidates = new TreeMap<>();
                    if (candidates.size() == 0)
                    {
                        System.out.println("There are no candidates!");
                        break;
                    }
                    for(int i = 0; i < candidates.size(); i++)
                    {
                        sortedCandidates.put(candidates.get(i).getName(), candidates.get(i));
                    }
                    for (Map.Entry<String, Candidate> entry : sortedCandidates.entrySet()) {
                        candidatePrinter.print(entry.getValue());
                        System.out.println();
                    }
                    break;
                case 13:
                    System.out.println("Enter candidate id:");
                    id = scanner.nextInt();
                    candidateService.deleteCandidate(id);
                    System.out.println("Candidate with id " + id + " deleted!");
                    break;
                case 14:
                    System.out.println("Enter candidate id:");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    name = scanner.nextLine();
                    candidateService.updateCandidateName(id, name);
                    System.out.println("Candidate with id " + id + " updated (new name: " + name + ")!");
                    break;
            }
            System.out.println("----------------");
            System.out.println("0 - Stop");
            System.out.println("1 - Add candidate");
            System.out.println("2 - Display candidates");
            System.out.println("3 - Add evaluator");
            System.out.println("4 - Display evaluators");
            System.out.println("5 - Add exam grades for a candidate");
            System.out.println("6 - Display exam grades for all candidates");
            System.out.println("7 - Search candidate by ID");
            System.out.println("8 - Search candidate by name");
            System.out.println("9 - Search evaluator by ID");
            System.out.println("10 - Search evaluator by name");
            System.out.println("11 - Show exam results for certain candidate");
            System.out.println("12 - Display candidates in alphabetical order");
            System.out.println("13 - Delete candidate");
            System.out.println("14 - Update candidate name");
            option = scanner.nextInt();
        }
    }
}
