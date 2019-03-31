package library.domain.entity;

public class BacGrade
{
    private double romGrade;
    private double mathGrade;
    private double infoGrade;

    public BacGrade(double romGrade, double mathGrade, double infoGrade) {
        this.romGrade = romGrade;
        this.mathGrade = mathGrade;
        this.infoGrade = infoGrade;
    }

    public double getRomGrade() {
        return romGrade;
    }

    public void setRomGrade(double romGrade) {
        this.romGrade = romGrade;
    }

    public double getInfoGrade() {
        return infoGrade;
    }

    public void setInfoGrade(double infoGrade) {
        this.infoGrade = infoGrade;
    }

    public double getMathGrade() {
        return mathGrade;
    }

    public void setMathGrade(double mathGrade) {
        this.mathGrade = mathGrade;
    }
}
