package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
//JDK 동적 프록시에 적용할 공통 로직을 개발
public class TimeInvocationHandler implements InvocationHandler {

    //프록시는 항상 프록시가 호출한 대상이 있어야 한다.
    private final Object target;

    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();
        //리플렉션을 사용해서 target 인스턴스의 메서드를 실행한다. args 는 메서드 호출시 넘겨줄 인수이다.
        Object result = method.invoke(target, args); //target 의 call 실행

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime={}", resultTime);
        return result;
    }
}
