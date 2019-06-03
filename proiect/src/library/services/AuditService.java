package library.services;

import java.io.*;

import library.domain.AdmissionException;
import java.sql.Timestamp;

import static library.domain.ErrorCode.PRINT_AUDIT;

public class AuditService
{
    private static AuditService instance;

    public static AuditService getInstance() {
        if (instance == null) {
            instance = new AuditService();
        }
        return instance;
    }

    public void printAudit(String actionName)
    {
        File file = new File("D:/pao/Admission-Service/proiect/src/library/tools/files/audit.csv");
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(file, true)))
        {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Thread t = Thread.currentThread();
            printWriter.println(actionName + " " + timestamp + " " + t.getName());
        }
        catch (IOException e)
        {
            throw new AdmissionException(PRINT_AUDIT, e.getMessage());
        }
    }
}
