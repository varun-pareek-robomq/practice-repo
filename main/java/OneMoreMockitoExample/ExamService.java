package OneMoreMockitoExample;
//An exam can start only if there are at least 10 questions

// this is class under test
public class ExamService {
    private QuestionBank questionBank;

    public ExamService(QuestionBank questionBank) {
        this.questionBank = questionBank;
    }
    public boolean canStartExam(){
        return questionBank.getTotalQuestions() >=10;
    }


}
