public class AdmissionService
{
    private Candidate[] candidates = new Candidate[50];
    private int candidatesNumber = 0;
    private static AdmissionService instance;

    public static AdmissionService getAdmissionService()
    {
        if (instance == null)
        {
            instance = new AdmissionService();
        }
        return instance;
    }

    public void addCandidate(String name, String cnp, double infoGrade, double mathGrade, String email, String phoneNumber)
    {
        candidates[candidatesNumber] = new Candidate(name, cnp, infoGrade, mathGrade, this.candidatesNumber, email, phoneNumber);
        candidatesNumber++;
    }
    public Candidate[] getCandidates()
    {
        return candidates;
    }

    public int getCandidatesNumber()
    {
        return candidatesNumber;
    }
}
