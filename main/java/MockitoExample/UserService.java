package MockitoExample;

public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public boolean registerUser(String username,String email){
        userRepository.save(username,email);
        try{
            emailService.sendWelcomeEmail(email);
        } catch (Exception e) {
//            System.out.println(e);
        }
        return true;
    }
}
