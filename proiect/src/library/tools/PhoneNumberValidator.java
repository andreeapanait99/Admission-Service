package library.tools;

import library.services.AuditService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator
{
    public final Pattern VALID_PHONE_NUMBER_REGEX = Pattern.compile("\\d{10}",  Pattern.CASE_INSENSITIVE);

    AuditService auditService = AuditService.getInstance();

    public boolean validate(String str)
    {
        auditService.printAudit("validatePhoneNumber");
        Matcher matcher = VALID_PHONE_NUMBER_REGEX .matcher(str);
        return matcher.find();
    }
}
