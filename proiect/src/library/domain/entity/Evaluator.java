package library.domain.entity;

public class Evaluator extends Person
{
    private int id;
    private String subject;

    public Evaluator(String name, String CNP, int id, String subject) {
        super(name, CNP);
        this.id = id;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
