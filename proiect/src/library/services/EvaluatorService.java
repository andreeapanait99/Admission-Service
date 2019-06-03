package library.services;

import library.configuration.ConnectionFactory;
import library.configuration.RepositoryConfig;
import library.domain.AdmissionException;
import library.domain.entity.Evaluator;
import library.domain.repository.EvaluatorRepository;
import library.tools.DataExtractor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static library.domain.ErrorCode.EVALUATOR_NOT_FOUND;

public class EvaluatorService
{
    private static final String GET_EVALUATOR_BY_ID = "SELECT * FROM evaluators WHERE id=?";

    private EvaluatorRepository evaluatorRepository = RepositoryConfig.getInstance().getEvaluatorRepository();
    AuditService auditService = AuditService.getInstance();

    private static EvaluatorService ourInstance = new EvaluatorService();

    public static EvaluatorService getInstance() {
        return ourInstance;
    }

    private EvaluatorService() {
    }

    public Evaluator searchEvaluatorById(int id) {
        auditService.printAudit("searchEvaluatorById");
        int i;
        List<Evaluator> evaluators = evaluatorRepository.getEvaluators();
        for (i = 0; i < evaluators.size(); i++) {
            if (evaluators.get(i).getId() == id)
                break;
        }
        if (i == evaluators.size()) {
            throw new AdmissionException(EVALUATOR_NOT_FOUND, "Could not find the evaluator with id: " + id);
        }
        return evaluators.get(i);
    }

    public Evaluator getEvaluatorById(int id) {
        auditService.printAudit("getEvaluatorByIdDB");
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(GET_EVALUATOR_BY_ID);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return DataExtractor.extractSingleEvaluatorFrom(rs);
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

    public String[] searchEvaluatorsByASpecificPattern(String partialEvaluatorName) {
        auditService.printAudit("searchEvaluatorsByASpecificPattern");
        List<Evaluator> evaluators = evaluatorRepository.getEvaluators();
        String[] result = new String[evaluators.size()];
        int counter = 0;
        String pattern = createPattern(partialEvaluatorName);
        for (int i = 0; i < evaluators.size(); i++) {
            if (evaluators.get(i).getName().matches(pattern)) {
                result[counter++] = evaluators.get(i).getName();
            }
        }
        return result;
    }
}
