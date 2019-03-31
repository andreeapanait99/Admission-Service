package library.domain.entity;

public class Candidate extends Person
{
    private final int id;
    private BacGrade bacGrade;
    private String email;
    private String phoneNumber;

    public Candidate(String name, String CNP, double romGrade, double mathGrade, double infoGrade, int id, String email, String phoneNumber) {
        super(name, CNP);
        this.bacGrade = new BacGrade(romGrade, mathGrade, infoGrade);
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public BacGrade getBacGrade() {
        return bacGrade;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
