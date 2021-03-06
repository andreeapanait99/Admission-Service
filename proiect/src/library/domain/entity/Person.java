package library.domain.entity;

public abstract class Person
{
    private String name;
    private String CNP;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCNP()
    {
        return CNP;
    }

    public void setCNP(String CNP)
    {
        this.CNP = CNP;
    }

    public Person(String name, String CNP)
    {
        this.name = name;
        this.CNP = CNP;
    }
}
