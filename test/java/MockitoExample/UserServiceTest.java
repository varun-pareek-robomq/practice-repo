package MockitoExample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserService userService;

    @Test
    public void testShouldRegisterUserSuccessfully(){
        //Act
        boolean res = userService.registerUser("Varun", "varun@test.com");

        //Assert
        verify(userRepository).save("Varun","varun@test.com");
        verify(emailService).sendWelcomeEmail("varun@test.com");
        assert(res);

    }

    @Test
    public void testShouldRegisterUserSuccessfullyEvenAfterServerDown(){
        //Arrange
        doThrow(new RuntimeException("Email server down"))
                .when(emailService)
                .sendWelcomeEmail("varun@test.com");

        //Act
        boolean res = userService.registerUser("Varun", "varun@test.com");
        //Assert
        verify(userRepository).save("Varun","varun@test.com");
        verify(emailService).sendWelcomeEmail("varun@test.com");
        assert(res);

    }


}