package library.configuration;

import library.tools.*;

public class ToolConfig
{
    private CandidatePrinter candidatePrinter = new CandidatePrinter();
    private ExamPrinter examPrinter = new ExamPrinter();
    private EvaluatorPrinter evaluatorPrinter = new EvaluatorPrinter();
    private EmailValidator emailValidator = new EmailValidator();
    private PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();

    public PhoneNumberValidator getPhoneNumberValidator() {
        return phoneNumberValidator;
    }

    public EmailValidator getEmailValidator() {
        return emailValidator;
    }

    public CandidatePrinter getCandidatePrinter() {
        return candidatePrinter;
    }

    public ExamPrinter getExamPrinter() {
        return examPrinter;
    }

    public EvaluatorPrinter getEvaluatorPrinter() {
        return evaluatorPrinter;
    }

    private static ToolConfig ourInstance = new ToolConfig();

    public static ToolConfig getInstance() {
        return ourInstance;
    }

    private ToolConfig() {
    }
}
