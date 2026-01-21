package OneMoreMockitoExample;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExamServiceFakeTest {
// Did not require for this scenario
    @Test
    public void shouldAllowExamsWhenQuestionsAreEnough() {
        QuestionBank fake = new FakeQuestionBank(15);
        ExamService examService = new ExamService(fake);

        assertTrue(examService.canStartExam());

    }@Test
    public void shouldNotAllowExamsWhenQuestionsAreNotEnough() {
        QuestionBank fake = new FakeQuestionBank(9);
        ExamService examService = new ExamService(fake);

        assertFalse(examService.canStartExam());

    }
}