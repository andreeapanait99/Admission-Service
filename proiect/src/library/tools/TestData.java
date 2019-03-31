package library.tools;

import library.domain.entity.Candidate;
import library.domain.entity.Evaluator;
import library.domain.entity.Exam;

public class TestData
{

    public Candidate[] candidates = new Candidate[]
        {
            new Candidate("Bell Iglesiaz", "6000301015650", 10, 9.85, 9.60, 0,"biglesiaz0@so-net.ne.jp", "0751147239"),
            new Candidate("Niyah Portillo", "5000801018142", 8.30, 10, 8.20, 1,"niyah.portillo@gmail.com", "0751147238"),
            new Candidate("Talitha Bond", "6011004096841", 9.30, 8.40, 7.40, 2,"talitha_bond1@yahoo.com", "0751147237"),
            new Candidate("Jac Hewitt", "6011004096841", 8.80, 6, 7.25, 3, "jac.hewitt@gmail.com", "0751147236"),
            new Candidate("Maisha Cotton", "6011004096841", 6.60, 10, 10, 4, "maisha_cotton123@yahoo.com", "0751147235"),
        };

    public Evaluator[] evaluators = new Evaluator[]
            {
                    new Evaluator("Leja Ryder", "2820629245479", 0, "Math"),
                    new Evaluator("Yasemin Kelley", "2770301169506", 1, "Info"),
                    new Evaluator("Iwan Marks", "1890615089192", 2, "Math"),
                    new Evaluator("Nate Brock", "1890727449102", 3, "Info"),
            };

    public Exam[] exams = new Exam[]
            {
                 new Exam(candidates[0], 10, 7.25, evaluators[0], evaluators[1]),
                    new Exam(candidates[1], 9.10, 8.70, evaluators[0], evaluators[1]),
                    new Exam(candidates[2], 6.30, 9, evaluators[2], evaluators[3]),
                    new Exam(candidates[3], 9, 7, evaluators[0], evaluators[3]),
                    new Exam(candidates[4], 6, 10, evaluators[0], evaluators[1])
            };
}
