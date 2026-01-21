package OneMoreMockitoExample;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ExamServiceStubTest {
    @Test
    public void allowExamsWhenQuestionsAreEnough(){
        QuestionBank stub = mock(QuestionBank.class);
        when(stub.getTotalQuestions()).thenReturn(15);

        ExamService examService = new ExamService(stub);
        assertTrue(examService.canStartExam());
    }
    @Test
    public void doNotAllowExamsIfQuestionsAreNotEnough(){
        QuestionBank stub = mock(QuestionBank.class);
        when(stub.getTotalQuestions()).thenReturn(2);
        ExamService examService = new ExamService(stub);
        assertFalse(examService.canStartExam());
    }

}