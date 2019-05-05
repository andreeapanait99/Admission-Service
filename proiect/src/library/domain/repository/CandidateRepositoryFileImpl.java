package library.domain.repository;

import library.domain.entity.Candidate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CandidateRepositoryFileImpl implements CandidateRepository
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
        candidates.add(new Candidate(candidate.getName(), candidate.getCNP(), candidate.getBacGrade().getRomGrade(), candidate.getBacGrade().getMathGrade(), candidate.getBacGrade().getInfoGrade(), candidate.getId(), candidate.getEmail(), candidate.getPhoneNumber()));
    }

    public void readCandidatesFromFile(String fileName) {
        File file = new File(fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                Candidate newCandidate = new Candidate(values[0], values[1], Double.parseDouble(values[2]), Double.parseDouble(values[3]), Double.parseDouble(values[4]), Integer.parseInt(values[5]), values[6], values[7]);
                addCandidate(newCandidate);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
