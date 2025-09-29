package aiCareerCoach.aop;


public class LlmServiceUnavailableException extends RuntimeException {
    public LlmServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
