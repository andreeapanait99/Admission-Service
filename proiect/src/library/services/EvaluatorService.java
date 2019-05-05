package library.services;

import library.configuration.RepositoryConfig;
import library.domain.AdmissionException;
import library.domain.entity.Evaluator;
import library.domain.repository.EvaluatorRepository;

import java.util.List;

import static library.domain.ErrorCode.EVALUATOR_NOT_FOUND;

public class EvaluatorService
{
    private EvaluatorRepository evaluatorRepository = RepositoryConfig.getInstance().getEvaluatorRepository();

    private static EvaluatorService ourInstance = new EvaluatorService();

    public static EvaluatorService getInstance() {
        return ourInstance;
    }

    private EvaluatorService() {
    }

    public Evaluator searchEvaluatorById(int id) {
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
