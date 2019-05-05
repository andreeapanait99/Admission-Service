package library.services;

import library.configuration.RepositoryConfig;
import library.configuration.ToolConfig;
import library.domain.AdmissionException;
import library.domain.entity.Candidate;
import library.domain.repository.CandidateRepository;

import java.util.List;

import static library.domain.ErrorCode.CANDIDATE_NOT_FOUND;

public class CandidateService {
    private static CandidateService instance;
    private ToolConfig toolConfig = ToolConfig.getInstance();
    private CandidateRepository candidateRepository = RepositoryConfig.getInstance().getCandidateRepository();

    public static CandidateService getInstance() {
        if (instance == null) {
            instance = new CandidateService();
        }
        return instance;
    }

    public Candidate searchCandidateById(int id) {
        int i;
        List<Candidate> candidates = candidateRepository.getCandidates();
        for (i = 0; i < candidates.size(); i++) {
            if (candidates.get(i).getId() == id)
                break;
        }
        if (i == candidates.size()) {
            throw new AdmissionException(CANDIDATE_NOT_FOUND, "Could not find the candidate with id: " + id);
        }
        return candidates.get(i);
    }

    private String createPattern(String partialUserName) { // example JDo, JoD, JoDo -> for John Doe
        String[] splitedPartialName = partialUserName.split("(?=[A-Z])"); //Split partial user name: {J, Do} {Jo D}
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < splitedPartialName.length; i++) {
            resultBuilder.append(splitedPartialName[i]);
            resultBuilder.append("[a-z]*"); //append any number of lower case characters

            resultBuilder.append("[[A-z]{1}[a-z]+\\s]*"); //any number of other names
            if (!(i == splitedPartialName.length - 1)) { //if it's not the last part
                resultBuilder.append("\\s"); //append one space
            }
        }
        return resultBuilder.toString();
    }

    public String[] searchCandidatesByASpecificPattern(String partialCandidateName) {
        List<Candidate> candidates = candidateRepository.getCandidates();
        String[] result = new String[candidates.size()];
        int counter = 0;
        String pattern = createPattern(partialCandidateName);
        for (int i = 0; i < candidates.size(); i++) {
            if (candidates.get(i).getName().matches(pattern)) {
                result[counter++] = candidates.get(i).getName()+ " - " + candidates.get(i).getId();
            }
        }
        return result;
    }

}
