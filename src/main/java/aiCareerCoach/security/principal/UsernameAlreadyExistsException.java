package aiCareerCoach.security.principal;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
