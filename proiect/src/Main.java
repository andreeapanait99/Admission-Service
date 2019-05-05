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

import java.util.*;

import static library.domain.ErrorCode.INVALID_EMAIL;
import static library.domain.ErrorCode.INVALID_PHONE_NUMBER;

public class Main
{
    public static void main(String[] args)
    {
        String name, cnp, email, phoneNumber, subject, actionName;
        double infoGrade, mathGrade, romGrade;
        int candidateId, evaluatorId;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our Admission Service!");
        CandidateService candidateService = CandidateService.getInstance();
        ToolConfig toolConfig = ToolConfig.getInstance();
        RepositoryConfig repositoryConfig = RepositoryConfig.getInstance();
        EvaluatorService evaluatorService = EvaluatorService.getInstance();
        ExamService examService = ExamService.getInstance();
        AuditService auditService = AuditService.getInstance();

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
        int option = scanner.nextInt();
        while (option != 0)
        {
            actionName = "";
            switch(option)
            {
                case 1:
                    System.out.println("Enter candidate name, cnp, Bac grades (rom, math, info), email and phone number:");
                    scanner.nextLine();
                    name = scanner.nextLine();
                    cnp = scanner.nextLine();
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
                    repositoryConfig.getCandidateRepository().addCandidate(name, cnp, romGrade, mathGrade, infoGrade, email, phoneNumber);
                    actionName = "add candidate";
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
                    candidatePrinter.printFile(candidates, "D:/pao/proiect/src/library/tools/files/candidatesOut.csv");
                    actionName = "display candidates";
                    break;
                case 3:
                    System.out.println("Enter evaluator name, cnp and subject:");
                    scanner.nextLine();
                    name = scanner.nextLine();
                    cnp = scanner.nextLine();
                    subject = scanner.nextLine();
                    repositoryConfig.getEvaluatorRepository().addEvaluator(name, cnp, subject);
                    actionName = "add evaluator";
                    break;
                case 4:
                    List<Evaluator> evaluators = repositoryConfig.getEvaluatorRepository().getEvaluators();
                    EvaluatorPrinter evaluatorPrinter = toolConfig.getEvaluatorPrinter();
                    if (evaluators.size() == 0)
                    {
                        System.out.println("There are no evaluators!");
                        break;
                    }
                    evaluatorPrinter.printFile(evaluators, "D:/pao/proiect/src/library/tools/files/evaluatorsOut.csv");
                    actionName = "display evaluators";
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
                    actionName = "add exam";
                    break;
                case 6:
                    List<Exam> exams = repositoryConfig.getExamRepository().getExams();
                    ExamPrinter examPrinter = toolConfig.getExamPrinter();
                    if (exams.size() == 0)
                    {
                        System.out.println("There are no exams!");
                        break;
                    }
                    examPrinter.printFile(exams, "D:/pao/proiect/src/library/tools/files/examsOut.csv");
                    actionName = "display exams";
                    break;
                case 7:
                    System.out.println("Enter candidate id:");
                    candidateId = scanner.nextInt();
                    candidate = candidateService.searchCandidateById(candidateId);
                    System.out.println("Candidate found!");
                    toolConfig.getCandidatePrinter().print(candidate);
                    actionName = "search candidate by id";
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
                    actionName = "search candidate by name";
                    break;
                case 9:
                    System.out.println("Enter evaluator id:");
                    evaluatorId = scanner.nextInt();
                    Evaluator evaluator = evaluatorService.searchEvaluatorById(evaluatorId);
                    if (evaluator == null) break;
                    System.out.println("Evaluator found!");
                    toolConfig.getEvaluatorPrinter().print(evaluator);
                    actionName = "search evaluator by id";
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
                    actionName = "search evaluator by name";
                    break;
                case 11:
                    System.out.println("Enter candidate id:");
                    candidateId = scanner.nextInt();
                    candidate = candidateService.searchCandidateById(candidateId);
                    Exam exam = examService.searchExamByCandidate(candidate);
                    System.out.println("Exam found!");
                    toolConfig.getExamPrinter().print(exam);
                    actionName = "search exam by candidate";
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
                    actionName = "display candidates in alphabetical order";
                    break;
            }
            auditService.printAudit(actionName);
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
            option = scanner.nextInt();
        }
    }
}
