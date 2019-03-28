import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        String name, cnp, email, phoneNumber;
        double infoGrade, mathGrade;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our Admission Service!");
        AdmissionService admissionService = AdmissionService.getAdmissionService();
        System.out.println("0 - Stop");
        System.out.println("1 - Add candidate");
        System.out.println("2 - Display candidates");
        int option = scanner.nextInt();
        while (option != 0)
        {
            switch(option)
            {
                case 1:
                    System.out.println("Enter candidate name, cnp, info grade and math grade, email and phone number:");
                    scanner.nextLine();
                    name = scanner.nextLine();
                    cnp = scanner.nextLine();
                    infoGrade = scanner.nextDouble();
                    mathGrade = scanner.nextDouble();
                    scanner.nextLine();
                    email = scanner.nextLine();
                    phoneNumber = scanner.nextLine();
                    admissionService.addCandidate(name, cnp, infoGrade, mathGrade, email, phoneNumber);
                    break;
                case 2:
                    Candidate[] candidates;
                    candidates = admissionService.getCandidates();
                    int candidatesNumber = admissionService.getCandidatesNumber();
                    CandidatePrinter candidatePrinter = new CandidatePrinter();
                    if (candidatesNumber == 0)
                    {
                        System.out.println("There are no candidates!");
                    }
                    for(int i = 0; i < candidatesNumber; i++)
                    {
                        System.out.println("Candidate " + i + ":");
                        candidatePrinter.print(candidates[i]);
                    }
                    break;
                case 3:

                    break;
            }
            System.out.println("----------------");
            System.out.println("0 - Stop");
            System.out.println("1 - Add candidate");
            System.out.println("2 - Display candidates");
            option = scanner.nextInt();
        }
    }
}
