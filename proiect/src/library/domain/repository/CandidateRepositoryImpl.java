package library.domain.repository;

import library.domain.entity.Candidate;

public class CandidateRepositoryImpl implements CandidateRepository
{
    private Candidate[] candidates = new Candidate[200];
    private int candidatesNumber = 0;

    @Override
    public Candidate[] getCandidates()
    {
        return candidates;
    }

    @Override
    public int getCandidatesNumber()
    {
        return candidatesNumber;
    }

    @Override
    public void addCandidate(String name, String CNP, double romGrade, double mathGrade, double infoGrade, String email, String phoneNumber)
    {
        candidates[candidatesNumber] = new Candidate(name, CNP, romGrade, mathGrade, infoGrade, this.candidatesNumber, email, phoneNumber);
        candidatesNumber++;
    }

    @Override
    public void addCandidate(Candidate candidate)
    {
        candidates[candidatesNumber] = new Candidate(candidate.getName(), candidate.getCNP(), candidate.getBacGrade().getRomGrade(), candidate.getBacGrade().getMathGrade(), candidate.getBacGrade().getInfoGrade(), this.candidatesNumber, candidate.getEmail(), candidate.getPhoneNumber());
        candidatesNumber++;
    }
}
