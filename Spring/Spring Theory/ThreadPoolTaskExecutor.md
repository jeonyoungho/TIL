
# ThreadPoolTaskExecutor

### ThreadPoolTaskExecutor
- 스레드 풀을 사용하는 Executor
- java.util.concurrent.Executor를 spring에서 구현한 것
    - org.springframework.scheduling.concurrent 패키지에서 제공
- 주로 spring에서 비동기처리를 위해 사용
    - 스레드풀을 사용하여 멀티스레드 구현을 쉽게 해줌
- 기본 생성자 하나만 존재

## Configuration
### Pool size configuration
~~~
@Bean("simpleTaskExecutor")
public TaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(5);	// 기본 스레드 수
    taskExecutor.setMaxPoolSize(10);	// 최대 스레드 수
    return taskExecutor;
}
~~~

- core와 max 사이즈를 설정할 수 있다.
- <b>최초 core사이즈만큼 동작하다가 더 이상 처리할 수 없을 경우 max사이즈만큼 스레드가 증가할 것이라 일반적으로 생각하지만 사실 그렇지 않다.</b>
- <b>내부적으로는 Integer.MAX_VALUE사이즈의 LinkedBlockingQueue를 생성해서 core사이즈만큼의 스레드에서 task를 처리할 수 없을 경우 queue에서 대기하게 됩니다. queue가 꽉 차게 되면 그때 max 사이즈만큼 스레드를 생성해서 처리하게 된다.</b>

### Capacity configuration
~~~
@Bean("simpleTaskExecutor")
public TaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(5);	// 기본 스레드 수
    taskExecutor.setMaxPoolSize(10);	// 최대 스레드 수
    taskExecutor.setQueueCapacity(100);	// Queue 사이즈
    return taskExecutor;
}
~~~
- core 사이즈 보다 많은 요청이 발생할 경우 Integer.MAX_VALUE 사이즈만큼의 queue의 용량이 너무 크다고 생각된다면 queueCapacity사이즈를 변경할 수 있다.
- 위의 예시 코드와 같이 설정한다면 최초 5개의 스레드에서 처리하다가 처리 속도가 밀릴 경우 100개 사이즈 queue에서 대기하고 그보다 많은 요청이 들어올 경우 최대 10개의 스레드까지 생성해서 처리하게 된다.

### RejectedExecutionHandler configuration
- max 스레드까지 생성하고 queue까지 꽉 찬 상태에서 추가 요청이 오면 RejectedExecutionException 예외가 발생합니다. 더 이상 처리할 수 없다는 오류이다.
- 오류가 발생하는 걸 손 놓고 지켜봐야만 하는게 아니라 몇 가지 예외 정책을 설정해줘야합니다.
- <b>기본적으로 RejectedExecutionHandler 인터페이스를 구현한 몇 가지 클래스가 존재한다.</b>
    - <b>AbortPolicy</b>
        - 기본 설정(Default)
        - RejectedExecutionException을 발생시킨다
    - <b>DiscardOldestPolicy</b>
        - 오래된 작업을 skip
        - 모든 task가 무조건 처리되어야 할 필요가 없을 경우 사용
    - <b>DiscardPolicy</b>
        - 처리하려는 작업을 skip
        - 역시 모든 task가 무조건 처리되어야 할 필요가 없을 경우 사용
    - <b>CallerRunsPolicy</b>
        - shutdown 상태가 아니라면 ThreadPoolTaskExecutor에 요청한 thread에서 직접 처리
        - 예외와 누락 없이 최대한 처리하려면 CallerRunsPolicy로 설정하는 것이 좋음
        ~~~
        @Bean("simpleTaskExecutor")
        public TaskExecutor taskExecutor() {
            ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
            taskExecutor.setCorePoolSize(5);	// 기본 스레드 수
            taskExecutor.setMaxPoolSize(10);	// 최대 스레드 수
            taskExecutor.setQueueCapacity(100);	// Queue 사이즈
            taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            return taskExecutor;
        }
        ~~~
    
### Shutdown configuration
- 위와 같이 정의한 스레드 풀에서 열심히 작업이 이루어이고 있을 때 애플리케이션 종료를 요청하면 어떻게 될까?
- Spring Boot Actuator를 이용해서 종료를 시켜보면 호출 즉시 애플리케이션이 바로 종료 되는 것을 확인할 수 있다.
~~~
POST http://localhost:8888/actuator/shutdown
~~~

- 이렇게 즉시 종료되면 아직 처리되지 못한 task는 유실된다. 유실 없이 마지막까지 다 처리하고 종료되길 원한다면 설정을 추가해야 합니다.
- waitForTasksToCompleteOnShutdown을 true로 하게 되면 queue에 남아 있는 모든 작업이 완료될 때 까지 기다리게 된다.
~~~
@Bean("simpleTaskExecutor")
public TaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(5); // 기본 스레드 수
    taskExecutor.setMaxPoolSize(10); // 최대 스레드 수
    taskExecutor.setQueueCapacity(100); // Queue 사이즈
    taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
    return taskExecutor;
}
~~~

### Timeout configuration
- 만약 모든 작업이 처리되길 기다리기 힘든 경우라면 setAwaitTerminationSeconds메서드를 통해 최대 종료 대기 시간을 설정할 수 있다.
~~~
@Bean("simpleTaskExecutor")
public TaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(5);	// 기본 스레드 수
    taskExecutor.setMaxPoolSize(10);	// 최대 스레드 수
    taskExecutor.setQueueCapacity(100);	// Queue 사이즈
    taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
    taskExecutor.setAwaitTerminationSeconds(60);	// shutdown 최대 60초 대기
    return taskExecutor;
}
~~~

### 기타 Configuration
|메서드|설명|기본값|
|-----|-----|-----|
|setAllowCoreThreadTimeout|코어 스레드의 타임아웃을 허용할 것인지에 대한 설정 메서드. true로 설정할 경우 코어 슬데ㅡ를 10으로 설정했어도 일정시간(keepAliveSeconds)이 지나면 코어 스레드 개수가 줄어듦|false|
|setkeepAliveSeconds|코어 스레드 타임아웃을 허용했을 경우 사용되는 설정값으로, 여기 설정된 식나이 지날 때까지 코어 스레드 풀의 스레드가 사용되지 않을 경우 해당 스레드는 terminate된다.|60초|


## 프로젝트에 적용하기
### Autowired로 ThreadPoolTaskExecutor 사용하기
~~~
package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    @Qualifier("executor")
    private ThreadPoolTaskExecutor executor;

    public void executeThreads() {
        System.out.println("executing threads");

        for(int i=0;i<10;i++) {
            executor.execute(new Job());
        }

    }

    class Job implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
~~~
- TestService라는 서비스 클래스에서 멤버변수 executor에 위에서 빈으로 등록한 객체를 쓰도록 @Qualifier로 executor이름을 명시주었다.
- execute메소드로 Runnable을 수행한 것을 알 수 있다.

### @Async로 ThreadPoolTaskExecutor 사용하기
- 1)ThreadPoolTaskExecutor bean 설정 클래스에 @EnableAsync도 추가해야 함
~~~
package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class TaskExecutorConfig {
    @Bean(name = "executor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);	// 기본 스레드 수
        taskExecutor.setMaxPoolSize(10);	// 최대 스레드 수
        taskExecutor.setQueueCapacity(100);	// Queue 사이즈
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60);	// shutdown 최대 60초 대기
        return taskExecutor;
    }

}
~~~

- 2)서비스의 메서드에 @Async("스레드풀네임")어노테이션 추가
~~~
package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class TestService2 {

    @Async("executor")
    public void executeThreads() {
        System.out.println("executing threads");

        try {
            Thread.sleep(3000);
            System.out.println("[TestService2]" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
~~~

#### 정리
- 1)@Configuration으로 등록한 클래스에 executor @Bean추가 (@Async를 이용할 경우 @EnableSync도 추가)
- 2)@Autowired, @Qualifier로 주입하여 사용하거나 또는 메서드 레벨에 @Async를 붙여 사용

## @Async 어노테이션 사용시 주의사항
### Async is not a silver bullet(@Async는 탄환이 아니다)
- private 메서드에는 적용이 안된다. public만 된다.
- self-invocation(자가 호출)해서는 안된다. -> 같은 클래스 내부의 메서드를 호출하는 것은 안된다

#### 예시
- TestController.java
~~~
@RestController 
public class TestController { 
    @Autowired private TestService testService; 
    
    @GetMapping("/test2") 
    public void test2() { 
        for(int i=0;i<10000;i++) { 
            testService.innerMethodCall(i); 
        } 
    } 
}
~~~

- TestService.java
~~~
@Service 
public class TestService { 
    private static final Logger logger
     = LoggerFactory.getLogger(TestService.class); 
     
     @Async public void innerMethod(int i) { 
         logger.info("async i = " + i); 
     } 
    
    public void innerMethodCall(int i) { 
        innerMethod(i); 
    } 
}
~~~

- 위 코드로 테스트 해보면 controller에서 testService.innerMethodCall()를 동기로 호출하지만 내부에서 하는 작업이 비동기 @Async가 걸린 innerMethod를 호출하니까 결국에는 비동기로 로그가 찍힐 것을 예상할 수 있다.
- <b>하지만 틀렸다. 아래 처럼 하나의 스레드로 동기 처리됨을 볼 수 있다.</b><br>
![1](https://user-images.githubusercontent.com/44339530/117565692-892b8300-b0ed-11eb-93f9-d9aa52c25cfc.png)<br>

### 위와 같은 결과가 발생하는 이유
- https://dzone.com/articles/effective-advice-on-spring-async-part-1
- 위의 출처에서 제대로 설명해준다.
- 결론부터 말하면 AOP가 적용되어 spring context에 등록되어 있는 빈 객체의 메서드가 호출되었을 때 스프링이 끼어들 수 있고 @Async가 적용되어 있따면 스프링이 메서드를 가로채서 다른 스레드(풀)에서 실행시켜주는 메커니즘이라는 것이다.<br>
![2](https://user-images.githubusercontent.com/44339530/117565752-df98c180-b0ed-11eb-8a09-f843f55ccd16.jpeg)<br>

- 그렇기 때문에 위에 제약조건이었던 것들이 이해가 된다.
- public이어야 가로챈 스프링의 다른 클래스에서 호출이 가능할 것이고, self-invocation이 불가능 했던 이유도 spring context에 등록된 메소드 호출이어야 프록시를 적용받을 수 있기에 내부 메서드 호출은 프록시 영향을 받지 않기 때문이다.

#### 해결책
~~~
@Service public class AsyncService { 
    @Async 
    public void run(Runnable runnable) { 
        runnable.run(); 
    }     
}
~~~
- 위의 코드와 같이 AsyncService를 하나 두고 해당 서비스는 유틸 클래스처럼 전역에서 사용하도록 두는 것이다.
- @Async메서드 run을 통해 들어오는 Runnable을 그냥 실행만 해주는 메서드다.

~~~
@Service
public class TestService {
	private static final Logger logger = LoggerFactory.getLogger(TestService.class);
	@Autowired
	private AsyncService asyncService;
	
	public void innerMethod(int i) {
		logger.info("async i = " + i);
	}
	
	public void innerMethodCall(int i) {
		asyncService.run(()->innerMethod(i));
		
	}
}
~~~
- 그 다음에 비동기 메서드 호출이 필요할 때 해당 서비스로 메서드를 호출해버리는 것이다.
- 저렇게 하니깐 결과도 비동기로 처리하는 모습을 확인할 수 있다.
- <b>위와 같은 해결책은 service의 메서드는 동기로 호출되길 원하지만 내부에서 하는 기능(동작)에서 일부만 비동기로 실행되기를 원할때 사용하면 좋다</b>
- (블로거 작성자)[https://jeong-pro.tistory.com/187]님의 생각으로는 차라리 CompletableFuture를 쓰되 해당 스레드 풀에서 실행되기를 바라면 아래와 같이 Executor를 주입받고 호출하는 것이 나을 것 같다고 한다.
~~~
@Service public class TestService { 
    private static final Logger logger 
    = LoggerFactory.getLogger(TestService.class); 
    
    @Autowired private Executor executor; 
    
    public void innerMethod(int i) { 
        logger.info("async i = " + i); 
    } 
        
    public void innerMethodCall(int i) { 
        CompletableFuture.runAsync(()->innerMethod(i),executor); 
    } 
}
~~~

- 위 코드를 실행해도 executor로 등록한 스레드풀이 주입되어 해당 풀에서 작업들이 수행된다.
- 참고로, void나 future가 아닌 String 리턴 값을 가진 메서드에 @Async를 달았는데도 잘 수행되었다.

#### 출처
- https://kapentaz.github.io/spring/Spring-ThreadPoolTaskExecutor-%EC%84%A4%EC%A0%95/#
- https://keichee.tistory.com/455
- https://jeong-pro.tistory.com/187