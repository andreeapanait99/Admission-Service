package library.domain.repository;

import library.domain.entity.Candidate;

import java.util.ArrayList;
import java.util.List;

public class CandidateRepositoryImpl implements CandidateRepository
{
    private List<Candidate> candidates = new ArrayList<>();

    @Override
    public List<Candidate> getCandidates()
    {
        return candidates;
    }

    @Override
    public void addCandidate(String name, String CNP, double romGrade, double mathGrade, double infoGrade, String email, String phoneNumber)
    {
        candidates.add(new Candidate(name, CNP, romGrade, mathGrade, infoGrade, candidates.size(), email, phoneNumber));
    }

    @Override
    public void addCandidate(Candidate candidate)
    {
        candidates.add(new Candidate(candidate.getName(), candidate.getCNP(), candidate.getBacGrade().getRomGrade(), candidate.getBacGrade().getMathGrade(), candidate.getBacGrade().getInfoGrade(), candidates.size(), candidate.getEmail(), candidate.getPhoneNumber()));
    }

    @Override
    public void readCandidatesFromFile(String fileName){}
}
