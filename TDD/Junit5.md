# Junit5
- Spring boot 2.2부터는 기본으로 junit5모듈을 사용하게 된다.

### Junit5란?
- Junit4 는 단일 모듈이였던 반면, Junit5는 크게 Junit Jupiter, Junit Platform, Junit Vintage모듈로 구성되어진다.

#### Junit Platform
- 테스트들을 실행하기 위한 뼈대
- 테스트를 발견하고 테스트 계획을 생성하는 TestEngine인터페이스를 가지고 있다.
- Platform은 TestEngine을 통해서 테스트를 발견하고, 실행하고, 결과를 보고한다. 또한 콘솔출력, 각종 IDE들의 연동을 보조하는 역할을 수행한다.

#### Junit Jupiter
- TestEngine의 실제 구현체는 별도 모듈의 역할을 수행한다.
- 모듈 중 하나가 junpiter-engine이다. 이 모듈은 jupiter-api를 사용해서 작성한 테스트 코드를 발견하고 실행한다.
- Jupiter API는 Junit5에 새롭게 추가된 테스트 코드용 API로서, Jupiter API를 사용해서 테스트 코드를 작성할 수 있다.
- Junit5 버전 테스트를 작성할 때 사용된다.

#### Junit Vintage
- 기존에 Junit4 버전으로 작성한 테스트 코드를 실행할 때에는 vintage-engine 모듈을 사용한다. 만약 사용하지 않는다면 의존성을 제거할 수 있다.

### Junit Annotation
- 스프링 부트 2.2를 기준으로 Junit에서 사용하는 Annotation이 변경되었다.

|버전|Spring MVC 테스트|단위테스트(Mockito)|Spring 통합테스트|
|-----|-----|-----|-----|
|Spring boot 2.2이전|@RunWith(MockitoJUnitRunner.class|@RunWith(MockitoJUnitRunner.class)|@RunWith(SpringRunner.class)|
|Spring boot 2.2이후|@WebMvcTest|@ExtendWith(MockitoExtension.class)|@SpringBootTest|

#### Spring MVC 테스트
- 2.2이전에 @RunWith(MocktoJUnitRunner.class)과 MockMvcBuilders를 이용해 가볍게 필요한 빈들만 올려 mvc테스트를 했었다.
    - MockMvcBuilders는 필터, resolver등을 설정하기가 까다로웠다.

- 2.2이후에 @WebMvcTest는 Controller, @ControllerAdvice, @JsonComponent, Converter, GenericConverter, Filter, WebMvcConfigurer, and HandlerMethodArgumentResolver을 스프링 빈으로 올리게 된다.
    - @SpringBootTest어노테이션을 올리는 것보다 훨씬 가볍게 테스트를 진행할 수 있다.
~~~
@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Test
    void getMember() throws Exception {
        // given

        given(memberService.getMember(anyLong()))
                .willReturn(new Member(1L, "test"));
        // when
        // then
        mockMvc.perform(get("/members/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
~~~

#### 단위 테스트
- RunWith(MockitoJUnitRunner.class)를 이용했는데 Junit5부터는 좀 더 강력한 확장어노테이션인 ExtendWith를 사용한다.
- ExtendWith 어노테이션은 메인으로 실행 될 class를 직접 지정할 수 있다.
- 간단한 service 단위 테스트 예제를 만든다면 아래와 같을 수 있다.
~~~
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    public void getMember(){
        // given
        given(memberRepository.findById(anyLong()))
                .willReturn(new Member(1L, "test"));
        // when
        Member member = memberService.getMember(1L);
        // then
        assertEquals(1L, member.getId());
        assertEquals("test", member.getName());
    }
}
~~~

#### Spring 통합 테스트
- 마찬가지로 RunWith(SpringRunner.class)도 @ExtendWith(SpringExtension.class)로 변경됐다.
- @ExtendWith(SpringExtension.class)은 @SpringBootTest안에 들어 있기 때문에 따로 선언할 필요가 없다. SpringExtension은 SpringTestContext Framework를 Junit5의 Jupiter 프로그래밍 모델에 통합하는 역할을 한다.
- 간단한 mvc 통합 테스트 예제를 만든다면 아래와 같을 수 있다.
~~~
@SpringBootTest
class MemberIntegrationControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void getMember() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(get("/members/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
~~~

#### 출처
- https://wan-blog.tistory.com/71