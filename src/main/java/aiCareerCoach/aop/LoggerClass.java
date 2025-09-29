package aiCareerCoach.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;

@Aspect
@Component
@Order(0) // outermost wrapper around controllers
public class LoggerClass {

    private static final Logger log = LoggerFactory.getLogger(LoggerClass.class);

    // Pointcut for any controller handler method
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void anyHandler() {}

    private static final ThreadLocal<List<String>> CALLS = ThreadLocal.withInitial(ArrayList::new);

    @Around("anyHandler()")
    public Object aroundController(ProceedingJoinPoint pjp) throws Throwable {
        long t0 = System.nanoTime();
        CALLS.get().clear();

        String entry = "Controller: " + pjp.getSignature().toShortString();
        CALLS.get().add(entry);

        log.info("🎯 Entering: {}", pjp.getSignature().toShortString());

        try {
            Object out = pjp.proceed(); // run the controller
            long ms = (System.nanoTime() - t0) / 1_000_000;

            log.info("✅ Exiting: {} (time={}ms)", pjp.getSignature().toShortString(), ms);
            log.debug("Full Call chain={} time={}ms", new ArrayList<>(CALLS.get()), ms);

            return out;
        } catch (Throwable ex) {
            long ms = (System.nanoTime() - t0) / 1_000_000;
            log.error("❌ Call chain failed={} time={}ms ex={}", new ArrayList<>(CALLS.get()), ms, ex.toString(), ex);
            throw ex;
        } finally {
            CALLS.remove();
        }
    }

    // Service + Repository
    @Pointcut("within(aiCareerCoach..services..*)")
    public void serviceLayer() {}

    @Pointcut("within(aiCareerCoach..repository..*)")
    public void repoLayer() {}

    @Around("serviceLayer() || repoLayer()")
    public Object logServiceAndRepo(ProceedingJoinPoint pjp) throws Throwable {
        List<String> calls = CALLS.get();
        if (calls == null) return pjp.proceed();

        String method = pjp.getSignature().toShortString();
        calls.add(method);

        log.info("➡️ Entering: {}", method);

        Object result = pjp.proceed();

        log.info("⬅️ Exiting: {}", method);

        return result;
    }
}
