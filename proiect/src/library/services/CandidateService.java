package library.services;

import library.configuration.ConnectionFactory;
import library.configuration.RepositoryConfig;
import library.configuration.ToolConfig;
import library.domain.AdmissionException;
import library.domain.entity.Candidate;
import library.domain.repository.CandidateRepository;
import library.tools.DataExtractor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static library.domain.ErrorCode.CANDIDATE_NOT_FOUND;

public class CandidateService {
    private static final String GET_CANDIDATE_BY_ID = "SELECT * FROM candidates WHERE id=?";

    private static CandidateService instance;
    private ToolConfig toolConfig = ToolConfig.getInstance();
    private CandidateRepository candidateRepository = RepositoryConfig.getInstance().getCandidateRepository();
    private AuditService auditService = AuditService.getInstance();

    public static CandidateService getInstance() {
        if (instance == null) {
            instance = new CandidateService();
        }
        return instance;
    }

    public Candidate searchCandidateById(int id) {
        auditService.printAudit("searchCandidateById");
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

    public Candidate getCandidateById(int id) {
        auditService.printAudit("getCandidateByIdDB");
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(GET_CANDIDATE_BY_ID);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return DataExtractor.extractSingleCandidateFrom(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
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
        auditService.printAudit("searchCandidatesByASpecificPattern");
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
