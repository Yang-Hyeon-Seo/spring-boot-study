# IoC(Inversion of Control, 제어의 역전)
- 의존성 주입을 통해 새로운 커피머신을 개발하더라도 커피 메이커의 소스코드를 변경하지 않고 사용할 수 있게 됨
- 하지만 여전히 새로운 제품이 나올 떄마다 수정해야 하는 코드가 존재
- 클래스 간 의존 관계가 많고 복잡하다면 매번 모든 클래스 간의 의존성을 생성하고 주입해주는 코드를 수정해야 함 → 이럴 때 사용하는 것이 제어의 역전
- 메인 클래스에서 직접 의존성 생성과 주입을 위한 코드를 작성하는 것이 아니라, 설정을 통해 클래스 객체를 만들고 필요한 의존성 객체를 주입하는 것
- 스프링 프레임워크에서 XML파일이나 애노테이션을 통해 제어의 역전을 구현할 수 있음

## 방식 비교
- 전통적인 방식
  - 개발자가 new로 객체를 만들고, 직접 메서드를 호출하며 흐름을 제어
- IoC방식
  - 프레임워크가 객체를 만들어서 필요한 곳에 주입하고 간접적으로 흐름 제어

## XML 통한 IoC 실습 방식
- 스프링 이니셜라이저 기본 설정을 다운로드 → XML 기반 제어의 역전
- DI 안의 파일들을 src - main - java - com.example.demo 아래로 복사
- `spring xml schemas`라고 검색해서 [스프링 홈페이지](https://docs.spring.io/spring-framework/reference/core/appendix/xsd-schemas.html)에 있는 XML Schemas라고 정의된 내용을 복사 → src - main - resource 폴더에서 beans.xml 파일을 생성한 후 스키마 샘플 붙여넣기
- `<beans>` 태그 안에서 `<bean>`태그를 통해 객체 생성 및 의존성 주입
- 메인클래스인 `DemoApplication`에 `beans.xml`의 클래스 경로를 파라미터로 하는 `@ImportResource` 애노테이션 추가

## 애노테이션 통한 IoC 실습 방식(권장)
- 클래스에 `@Component` 애노테이션 추가(tab을 통해서 필요한 라이브러리 import)
- CoffeMaker 클래스에서 의존성을 주입 받을 coffeeMachine 프로퍼티에 `@Autowired` 애노테이션을 추가하면 CoffeeMaker 클래스를 사용해 스프링 빈 객체를 만들고, 스프링 컨테이너에서 CoffeeMachine 인터페이스 규칙을 구현한 EspressoMachine 객체를 주입함
- `setCoffeeMachine()`은 더 이상 필요하지 X
- `makeCoffee()` 메서드에 `@PostConstruct` 애노테이션을 추가하면 모든 스프링 빈 객체를 생성하고, 필요한 의존성이 주입된 후에 자동으로 호출함
- 스프링 부트가 자동으로 `@Component`가 사용된 클래스를 스캔해 객체를 만들고 필요한 의존성을 주입하는 것은 애플리케이션의 시작점인 `DemoApplication.java` 클래스에 `@SpringBootApplication` 애노테이션이 있기 때문임
- 메인 클래스에 `@SpringBootApplication` 애노테이션이 있는 경우, 프로젝트에 포함된 클래스를 모두 검색해 `@Component` 애노테이션이 붙은 클래스의 객체들을 생성하고, 스프링 컨테이너라는 가상의 고간에 넣어 관리함. 그리고 `@Autowired` 애노테이션을 검색해 해당 인터페이스 또는 클래스를 구현한 객체를 스프링 컨테이너에서 찾아 의존성을 주입함으로써 간편하게 의존성 주입과 제어의 역전을 구현함
### `@Autowired`
- `@Autowired`를 통해 스프링은 해당 인터페이스를 구현한 객체를 찾아 의존성 주입
  - 만약 동일한 인터페이스를 구현한 객체가 여러개 있다면?
    - 에스프레소 머신에만 `@Component`를 추가해 스프링 빈 객체로 만듦 → 드립 커피 머신에도 `@Component`애노테이션을 추가한다면 동일한 커피 머신 인터페이스를 구현한 객체가 2개가 됨
    - 이 경우 `@Autowired`를 통해 의존성을 주입할 대상을 하나로 특정할 수 없어서 오류가 발생함
    - 방법 1 : `@Primary` 애노테이션을 붙여 우선순위를 높일 수 있음
    - 방법 2 : `@Component("이름")"` 애노테이션을 붙인 후, `@Autowired` 애노테이션을 통해 의존성을 주입받을 때 `@Qualifier` 애노테이션을 추가해 주입받을 빈 객체의 이름을 지정하면 스프링 컨테이너 안에서 해당 이름으로 등록된 객체를 찾아 주입할 수 있음
    - 방법 3 : `@Autowired` 애노테이션으로 리스트로 주입받게 되면 모두 리스트로 주입받을 수 있음
      ```java
      public class CoffeeMaker {
        @Autowired
        private List<CoffeeMachine> coffeeMachines;
        ...
      }
      ```
