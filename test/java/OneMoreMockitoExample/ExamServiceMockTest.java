package OneMoreMockitoExample;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class ExamServiceMockTest {
    @Test
    public void shouldAskQuestionBankBeforeStartingExam(){
        QuestionBank mockBank = mock(QuestionBank.class);
        when(mockBank.getTotalQuestions()).thenReturn(1);
        ExamService service = new ExamService(mockBank);

        service.canStartExam();
        verify(mockBank).getTotalQuestions();
    }
}
