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

## 실습 방식
- 스프링 이니셜라이저 기본 설정을 다운로드 → XML 기반 제어의 역전
- DI 안의 파일들을 src - main - java - com.example.demo 아래로 복사
- `spring xml schemas`라고 검색해서 [스프링 홈페이지](https://docs.spring.io/spring-framework/reference/core/appendix/xsd-schemas.html)에 있는 XML Schemas라고 정의된 내용을 복사 → src - main - resource 폴더에서 beans.xml 파일을 생성한 후 스키마 샘플 붙여넣기
- `<beans>` 태그 안에서 `<bean>`태그를 통해 객체 생성 및 의존성 주입
- 메인클래스인 `DemoApplication`에 `beans.xml`의 클래스 경로를 파라미터로 하는 `@ImportResource` 애노테이션 추가