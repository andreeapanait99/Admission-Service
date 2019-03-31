package library.services;

import library.configuration.RepositoryConfig;
import library.configuration.ToolConfig;
import library.domain.entity.Candidate;
import library.domain.repository.CandidateRepository;

public class CandidateService {
    private static CandidateService instance;
    private ToolConfig toolConfig = ToolConfig.getInstance();
    private CandidateRepository candidateRepository = RepositoryConfig.getInstance().getCandidateRepository();

    public static CandidateService getCandidateService() {
        if (instance == null) {
            instance = new CandidateService();
        }
        return instance;
    }

    public Candidate searchCandidateById(int id) {
        int i;
        int candidatesNumber = candidateRepository.getCandidatesNumber();
        Candidate[] candidates = candidateRepository.getCandidates();
        for (i = 0; i < candidatesNumber; i++) {
            if (candidates[i].getId() == id)
                break;
        }
        if (i == candidatesNumber) {
            System.out.println("Candidate with id " + id + " does not exist!");
        }
        return candidates[i];
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
        Candidate[] candidates = candidateRepository.getCandidates();
        String[] result = new String[candidates.length];
        int counter = 0;
        String pattern = createPattern(partialCandidateName);
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] != null && candidates[i].getName().matches(pattern)) {
                result[counter++] = candidates[i].getName()+ " - " + candidates[i].getId();
            }
        }
        return result;
    }
}
