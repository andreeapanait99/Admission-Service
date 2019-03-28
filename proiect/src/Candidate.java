public class Candidate extends Persoana
{
    private final int id;
    private Grade grade;
    private String email;
    private String phoneNumber;

    public Candidate(String name, String CNP, double infoGrade, double mathGrade, int id, String email, String phoneNumber) {
        super(name, CNP);
        grade = new Grade(infoGrade, mathGrade);
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void print()
    {
        System.out.println("Name: " + super.getName());
        System.out.println("CNP: " + super.getCNP());
        System.out.println("Info Grade: " + grade.getInfoGrade());
        System.out.println("Math Grade: " + grade.getMathGrade());
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
    }
}
