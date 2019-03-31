package library.domain.entity;

public class Exam
{
    private Candidate candidate;
    private double mathGrade;
    private double infoGrade;
    private Evaluator evaluator1;
    private Evaluator evaluator2;

    public Exam(Candidate candidate, double mathGrade, double infoGrade, Evaluator evaluator1, Evaluator evaluator2) {
        this.candidate = candidate;
        this.mathGrade = mathGrade;
        this.infoGrade = infoGrade;
        this.evaluator1 = evaluator1;
        this.evaluator2 = evaluator2;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public double getMathGrade() {
        return mathGrade;
    }

    public void setMathGrade(double mathGrade) {
        this.mathGrade = mathGrade;
    }

    public double getInfoGrade() {
        return infoGrade;
    }

    public void setInfoGrade(double infoGrade) {
        this.infoGrade = infoGrade;
    }

    public Evaluator getEvaluator1() {
        return evaluator1;
    }

    public void setEvaluator1(Evaluator evaluator1) {
        this.evaluator1 = evaluator1;
    }

    public Evaluator getEvaluator2() {
        return evaluator2;
    }

    public void setEvaluator2(Evaluator evaluator2) {
        this.evaluator2 = evaluator2;
    }
}
