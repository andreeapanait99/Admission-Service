package library.services;

import library.configuration.RepositoryConfig;
import library.domain.entity.Evaluator;
import library.domain.repository.EvaluatorRepository;

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
        int evaluatorsNumber = evaluatorRepository.getEvaluatorsNumber();
        Evaluator[] evaluators = evaluatorRepository.getEvaluators();
        for (i = 0; i < evaluatorsNumber; i++) {
            if (evaluators[i].getId() == id)
                break;
        }
        if (i == evaluatorsNumber) {
            System.out.println("Evaluator with id " + id + " does not exist!");
        }
        return evaluators[i];
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
        Evaluator[] evaluators = evaluatorRepository.getEvaluators();
        String[] result = new String[evaluators.length];
        int counter = 0;
        String pattern = createPattern(partialEvaluatorName);
        for (int i = 0; i < evaluators.length; i++) {
            if (evaluators[i] != null && evaluators[i].getName().matches(pattern)) {
                result[counter++] = evaluators[i].getName();
            }
        }
        return result;
    }
}
