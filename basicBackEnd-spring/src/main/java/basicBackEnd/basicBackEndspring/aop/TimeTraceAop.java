package basicBackEnd.basicBackEndspring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*메서드의 반환시간을 측정하는 클래스이다. AOP적용을 위해 Aspect 어노테이션을, 스프링 빈
* 등록을 위해 Component 어노테이션을 붙인다. */
@Component
@Aspect
public class TimeTraceAop {
    /*어떤 클래스의 메서드들에 해당 AOP클래스를 적용할건지 경로를 지정해줌*/
    @Around("execution(* basicBackEnd.basicBackEndspring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }
}