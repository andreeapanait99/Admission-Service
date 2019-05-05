package library.tools;

import library.domain.entity.Candidate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CandidatePrinter
{
    public void print(Candidate candidate)
    {
        System.out.println("ID: " + candidate.getId());
        System.out.println("Name: " + candidate.getName());
        System.out.println("CNP: " + candidate.getCNP());
        System.out.println("Bac grade:");
        System.out.println("Rom: " + candidate.getBacGrade().getRomGrade());
        System.out.println("Math: " + candidate.getBacGrade().getMathGrade());
        System.out.println("Info: " + candidate.getBacGrade().getInfoGrade());
        System.out.println("Email: " + candidate.getEmail());
        System.out.println("Phone Number: " + candidate.getPhoneNumber());
    }

    public void printFile(List<Candidate> candidates, String fileName)
    {
        File file = new File(fileName);
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(file, false)))
        {
            for(int i = 0; i < candidates.size(); i++) {
                printWriter.println("ID: " + candidates.get(i).getId());
                printWriter.println("Name: " + candidates.get(i).getName());
                printWriter.println("CNP: " + candidates.get(i).getCNP());
                printWriter.println("Bac grade:");
                printWriter.println("Rom: " + candidates.get(i).getBacGrade().getRomGrade());
                printWriter.println("Math: " + candidates.get(i).getBacGrade().getMathGrade());
                printWriter.println("Info: " + candidates.get(i).getBacGrade().getInfoGrade());
                printWriter.println("Email: " + candidates.get(i).getEmail());
                printWriter.println("Phone Number: " + candidates.get(i).getPhoneNumber());
                printWriter.println();
            }
        }
        catch (IOException e) {
        e.printStackTrace();
        }
    }
}
