public class Grade
{
    private double infoGrade;
    private double mathGrade;

    public Grade(double infoGrade, double mathGrade) {
        this.infoGrade = infoGrade;
        this.mathGrade = mathGrade;
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
