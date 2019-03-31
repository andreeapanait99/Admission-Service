package library.tools;

import library.domain.entity.Candidate;

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
}
