package library.domain.repository;

import library.domain.entity.Candidate;

import java.util.List;

public interface CandidateRepository
{
    public List<Candidate> getCandidates();

    public void addCandidate(Candidate candidate);

    public void addCandidate(String name, String CNP, double romGrade, double mathGrade, double infoGrade, String email, String phoneNumber);

    public void readCandidatesFromFile(String fileName);
}
