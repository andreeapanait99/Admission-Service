package library.tools;

import library.services.AuditService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator
{
    public final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    AuditService auditService = AuditService.getInstance();

    public boolean validate(String emailStr)
    {
        auditService.printAudit("validateEmail");
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
}
