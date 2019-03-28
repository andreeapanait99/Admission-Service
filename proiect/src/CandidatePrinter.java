public class CandidatePrinter
{
    public void print(Candidate candidate)
    {
        System.out.println("Name: " + candidate.getName());
        System.out.println("CNP: " + candidate.getCNP());
        System.out.println("Info Grade: " + candidate.getGrade().getInfoGrade());
        System.out.println("Math Grade: " + candidate.getGrade().getMathGrade());
        System.out.println("Email: " + candidate.getEmail());
        System.out.println("Phone Number: " + candidate.getPhoneNumber());
    }
}
