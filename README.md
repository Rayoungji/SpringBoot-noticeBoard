
# springboot study - noticedBoard(CRUD)

### 1. DataBase란?

*-mysql, oracle 은 우리가 사용하는 디비*  
 -mysql을 사용하는 도구로 workbench를 사용.  
 -스키마 만들고 사용자권한에서 권한준다  

### 2. spring boot란?  

*-웹 서버 프레임 워크*

### 2.1. spring boot 패키지 기본 구성

***1) org.zerock***

*-main (서버 실행함수)*

***2) org.zerock.domain.entity***  

```
User.java

@Entity  //엔티티임을 명시
@Getter  //롬북 생성
@Setter  //롬북 생성
@NoArgsConstructor  
@AllArgsConstructor

-> 컬럼
   @Id  //프라이머리 키 명시
   @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto
   private Long mno;
   
   private String name;
   private String age;
   private String sex;
   private String address;
   private String phone;
```

 ***3)org.zerock.dto***
  
  *-직접적인 데이터 교환을 위한 테이블*  
  -여기서 엔티티를 다 가져올 필요가 없이 필요한 데이터만 생성하기 위해 dto를 생성한다.
```
@Getter
@Setter
public class SignUpDto {  
필요한 변수들만 선언해준다
```

***4) org.zerock.repository***

  *-레파지토리 선언부분*  
 ex) extends CrudRepository<엔티티이름, 아이디타입>  
     JAPRepository<엔티티이름, 아이디타입>  

***5) org.zerock.service***

*-dto를 파라미터로한 메소드 구현 & 레파지토리에서 생성한 메소드 사용*  
-레파지토리를 주입 할 때 방법은 @Autowired어노테이션 방법, 생성자 주입 방법이 있다.  
-주로 생성자 주입 방법을 사용한다.

**_@Autowired어노테이션 방법_**

```
@Autowired
 private MemberRepository memberRepository;
 ```


**_생성자 주입 방법_**

```
final private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
      this.userRepository = userRepository;
  }
  ```
  
  
**_service 메소드 예시 (create)_**

```
public void createUser(SignUpDto signupDto) {
     User user = new User();
     
     user.setName(signupDto.getName());
     user.setAge(signupDto.getAge());
     user.setSex(signupDto.getSex());
     user.setPhone(signupDto.getPhone());
     user.setAddress(signupDto.getAddress());

     userRepository.save(user); 
    
  }
  ```

***6)org.zerock.controller***

*-http 통신이 직접적으로 이루어지는 부분*  
-서비스를 사용하므로 서비스를 생성자 주입하고 사용함

어노테이션 중  
@RestController 과 @Controller이 있다
