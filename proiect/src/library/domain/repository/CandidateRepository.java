package library.domain.repository;

import library.domain.entity.Candidate;

public interface CandidateRepository
{
    public Candidate[] getCandidates();

    public int getCandidatesNumber();

    public void addCandidate(Candidate candidate);

    public void addCandidate(String name, String CNP, double romGrade, double mathGrade, double infoGrade, String email, String phoneNumber);
}
