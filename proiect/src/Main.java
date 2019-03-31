import library.configuration.RepositoryConfig;
import library.configuration.ToolConfig;
import library.domain.entity.Evaluator;
import library.domain.entity.Exam;
import library.services.CandidateService;
import library.domain.entity.Candidate;
import library.services.EvaluatorService;
import library.services.ExamService;
import library.tools.CandidatePrinter;
import library.tools.EvaluatorPrinter;
import library.tools.ExamPrinter;
import library.tools.TestData;

import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        String name, cnp, email, phoneNumber, subject;
        double infoGrade, mathGrade, romGrade;
        int candidateId, evaluatorId;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our Admission Service!");
        CandidateService candidateService = CandidateService.getCandidateService();
        ToolConfig toolConfig = ToolConfig.getInstance();
        RepositoryConfig repositoryConfig = RepositoryConfig.getInstance();
        EvaluatorService evaluatorService = EvaluatorService.getInstance();
        ExamService examService = ExamService.getInstance();
        TestData testData = new TestData();

        for (int i = 0; i < 5; i++)
            RepositoryConfig.getInstance().getCandidateRepository().addCandidate(testData.candidates[i]);

        for (int i = 0; i < 4; i++)
            RepositoryConfig.getInstance().getEvaluatorRepository().addEvaluator(testData.evaluators[i]);

        for (int i = 0; i < 5; i++)
            RepositoryConfig.getInstance().getExamRepository().addExam(testData.exams[i]);

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
        int option = scanner.nextInt();
        while (option != 0)
        {
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
                        System.out.println("The email entered is not valid!");
                        break;
                    }
                    phoneNumber = scanner.nextLine();
                    valid = toolConfig.getPhoneNumberValidator().validate(phoneNumber);
                    if (!valid)
                    {
                        System.out.println("The phone number entered is not valid!");
                        break;
                    }
                    repositoryConfig.getCandidateRepository().addCandidate(name, cnp, romGrade, mathGrade, infoGrade, email, phoneNumber);
                    break;
                case 2:
                    Candidate[] candidates;
                    candidates = repositoryConfig.getCandidateRepository().getCandidates();
                    int candidatesNumber = repositoryConfig.getCandidateRepository().getCandidatesNumber();
                    CandidatePrinter candidatePrinter = toolConfig.getCandidatePrinter();
                    if (candidatesNumber == 0)
                    {
                        System.out.println("There are no candidates!");
                        break;
                    }
                    for(int i = 0; i < candidatesNumber; i++)
                    {
                        System.out.println("Candidate " + candidates[i].getId() + ":");
                        candidatePrinter.print(candidates[i]);
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println("Enter evaluator name, cnp and subject:");
                    scanner.nextLine();
                    name = scanner.nextLine();
                    cnp = scanner.nextLine();
                    subject = scanner.nextLine();
                    repositoryConfig.getEvaluatorRepository().addEvaluator(name, cnp, subject);
                    break;
                case 4:
                    Evaluator[] evaluators = repositoryConfig.getEvaluatorRepository().getEvaluators();
                    int evaluatorsNumber = repositoryConfig.getEvaluatorRepository().getEvaluatorsNumber();
                    EvaluatorPrinter evaluatorPrinter = toolConfig.getEvaluatorPrinter();
                    if (evaluatorsNumber == 0)
                    {
                        System.out.println("There are no evaluators!");
                        break;
                    }
                    for(int i = 0; i < evaluatorsNumber; i++)
                    {
                        System.out.println("Evaluator " + evaluators[i].getId() + ":");
                        evaluatorPrinter.print(evaluators[i]);
                        System.out.println();
                    }
                    break;
                case 5:
                    System.out.println("Enter candidate id, math grade, info grade, evalutor 1 and evaluator 2 ids:");
                    candidateId = scanner.nextInt();
                    Candidate candidate = candidateService.searchCandidateById(candidateId);
                    if (candidate == null) break;
                    mathGrade = scanner.nextDouble();
                    infoGrade = scanner.nextDouble();
                    evaluatorId = scanner.nextInt();
                    Evaluator evaluator1 = evaluatorService.searchEvaluatorById(evaluatorId);
                    if (evaluator1 == null) break;
                    evaluatorId = scanner.nextInt();
                    Evaluator evaluator2 = evaluatorService.searchEvaluatorById(evaluatorId);
                    if (evaluator2 == null) break;
                    repositoryConfig.getExamRepository().addExam(candidate, mathGrade, infoGrade, evaluator1, evaluator2);
                    break;
                case 6:
                    Exam[] exams = repositoryConfig.getExamRepository().getExams();
                    int examsNumber = repositoryConfig.getExamRepository().getExamsNumber();
                    ExamPrinter examPrinter = toolConfig.getExamPrinter();
                    if (examsNumber == 0)
                    {
                        System.out.println("There are no exams!");
                        break;
                    }
                    for(int i = 0; i < examsNumber; i++)
                    {
                        System.out.println("Exam " + i + ":");
                        examPrinter.print(exams[i]);
                        System.out.println();
                    }
                    break;
                case 7:
                    System.out.println("Enter candidate id:");
                    candidateId = scanner.nextInt();
                    candidate = candidateService.searchCandidateById(candidateId);
                    if (candidate == null) break;
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
                    Evaluator evaluator = evaluatorService.searchEvaluatorById(evaluatorId);
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
                    candidate = candidateService.searchCandidateById(candidateId);
                    if (candidate == null) break;
                    Exam exam = examService.searchExamByCandidate(candidate);
                    if (exam == null) break;
                    System.out.println("Exam found!");
                    toolConfig.getExamPrinter().print(exam);
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
            option = scanner.nextInt();
        }
    }
}
