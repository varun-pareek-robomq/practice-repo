package OneMoreMockitoExample;

public class FakeQuestionBank implements  QuestionBank{

    private int questions;

    public FakeQuestionBank(int questions) {
        this.questions = questions;
    }

    @Override
    public int getTotalQuestions() {
        return questions;
    }
}
