public abstract class Persoana
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

    public Persoana(String name, String CNP)
    {
        this.name = name;
        this.CNP = CNP;
    }
}
