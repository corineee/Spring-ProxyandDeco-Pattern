package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        //공통 로직1 시작
        log.info("start");
        String result1 = target.callA(); //호출하는 메서드가 다름
        log.info("result={}", result1);

        //공통 로직2 시작
        log.info("start");
        String result2 = target.callB(); //호출하는 메서드가 다름
        log.info("result={}", result2);
        //공통 로직1 종료
    }

    @Slf4j
    static class Hello {
    // reflection 은 컴파일 오류가 안나고 런타임 시 오류가 발생하기 때문에 가급적 사용안하는 것이 좋다.
        @Test
        void reflection1() throws Exception {
            //클래스 정보
            Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello"); //내부 클래스는 구분을 위해 $ 를 사용

            Hello target = new Hello();
            //callA 메서드 정보
            Method methodCallA = classHello.getMethod("callA");
            Object result1 = methodCallA.invoke(target); //target 인스턴스에 있는 callA 를 호출
            log.info("result1={}", result1);

            //callB 메서드 정보
            Method methodCallB = classHello.getMethod("callB");
            Object result2 = methodCallB.invoke(target); //target 인스턴스에 있는 callA 를 호출
            log.info("result2={}", result2);
        }

        @Test
        void reflection2() throws Exception {
            //클래스 정보
            Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello"); //내부 클래스는 구분을 위해 $ 를 사용

            Hello target = new Hello();
            //callA 메서드 정보
            Method methodCallA = classHello.getMethod("callA");
            dynamicCall(methodCallA, target);

            //callB 메서드 정보
            Method methodCallB = classHello.getMethod("callB");
            dynamicCall(methodCallB, target);
        }

        private void dynamicCall(Method method, Object target) throws Exception {
            log.info("start");
            Object result = method.invoke(target);
            log.info("result={}", result);

        }

        public String callA() {
            log.info("callA");
            return "A";
        }
        public String callB() {
            log.info("callB");
            return "B";
        }
    }

}
