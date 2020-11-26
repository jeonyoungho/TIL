# spring quartz configuration

- 주기적으로 작업이나 배치작업을 하기 위한 스케줄러기능을 위해서 Quartz라이브러리를 사용함<br>
- quartz를 통한 주기적인 작업을 실행하기 위해선 trigger와 job이 필요함<br>
- trigger는 쉽게 말하면 작업을 실행할 시간 단위고 job은 실제로 수행할 작업을 뜻함<br>
- 다음과 같이 xml설정(scheduler.xml)이 필요하며 java 설정도 가능함<br>

~~~
<!-- job 설정 -->
<bean id="testJob" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">

    <!-- 서비스 구현 객체의 빈 이름을 parameter로 설정 -->
    <property name="targetObject" ref="boardService" />

    <!-- 서비스 객체에서 주기적으로 실행될 메소드른 설정 -->
    <property name="targetMethod" value="testJobMethod" />

    <!-- 동시 실행을 방지합니다. -->
    <property name="concurrent" value="false" />

</bean>

<!-- trigger 설정 -->
<bean id="testJobTrigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
	
	<!-- 위에서 설정한 job을 설정 -->
    <property name="jobDetail" ref="testJob" />

    <!-- CronTrigger를 사용하여 2분 간격으로 실행되도록 설정, cron expression으로 설정 -->
    <property name="cronExpression" value="0 0/2 * * * ?" />

</bean>

<!-- scheduler 설정 -->
<bean id="testJobScheduler" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
    <property name="triggers">

        <!-- 앞에서 설정한 트리거를 등록, 필요하면 여러 개 만들어서 등록 할 수 있음 -->
        <list>
			<ref bean="testJobTrigger" />
		</list>
		
    </property>
</bean>
~~~