# 📦 Member Manage API

> **카카오 OAuth2 인증 + RSA 기반 JWT + DTO/Domain 구조 분리**  
> 실전 백엔드 개발에서 API 설계 원칙과 계층 분리를 철저히 준수한 사용자 관리 시스템입니다.

---

## 🛠️ 사용 기술 스택

| 계층         | 사용 기술                                                                 |
|--------------|--------------------------------------------------------------------------|
| Backend      | Java 17, Spring Boot 3.2.1, MyBatis                                      |
| Auth         | Kakao OAuth2, JWT (RSA 알고리즘)                                          |
| DB           | PostgreSQL                                                               |
| API 문서     | Google Docs / Swagger (선택)                                              |
| Infra        | AWS 또는 로컬                                                             |

---

## 🔐 인증 방식

### ✅ Kakao OAuth2 + JWT (RSA)

- **카카오 로그인을 통한 인증 처리**
- **JWT 토큰 기반의 인증 방식 사용**
- **RSA 공개키/개인키 방식의 암호화 적용**
- **AccessToken → 사용자 식별 → JWT 생성 → 클라이언트 전달**

## 🧱 계층 구조 및 설계
- Controller → Service → Mapper(MyBatis) → DB

### 📌 구조 설계 포인트

- **DTO ↔ Domain 변환**은 `Converter` 대신 **DTO 내부 위임 메서드**로 구조 간단화  
  → Domain의 외부 의존성 최소화

- **Entity와 Domain은 거의 동일한 형태**로 유지  
  → DB 설계와 API 명세가 REST 원칙을 충실히 반영

- **MyBatis Mapper는 전부 Domain 객체 기반 처리**  
  → ORM처럼 도메인 중심으로 SQL 관리

- **JPA의 원칙** (영속성, 도메인 주도 설계 등)을 최대한 유지하려는 구조로 설계

---

## 🚨 예외 처리

모든 커스텀 예외는 `@RestControllerAdvice` 기반의 전역 핸들러에서 관리합니다.

### ✨ 예외 처리 코드 예시

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidKakaoLoginException.class)
    private ResponseEntity<ErrorCode> InvalidKakaoLoginException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorCode.builder()
                .name("카카오 로그인 에러")
                .message("카카오 로그인에 실패하였습니다.")
                .build());
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<ErrorCode> UserNotFoundException() {
        return ResponseEntity.internalServerError().body(ErrorCode.builder()
                .name("사용자 조회 에러")
                .message("해당 코드의 사용자가 조회되지 않습니다.")
                .build());
    }

    @ExceptionHandler(TokenIsNotValidException.class)
    private ResponseEntity<ErrorCode> TokenExpiredException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorCode.builder()
                .name("Token 유효성 검사 실패")
                .message("토큰이 유효하지 않습니다.")
                .build());
    }

}
```
## 🧑‍💻 작성자

박민준 (Minjun Park)
Java/Spring Boot 기반 백엔드 개발자
✉️ [alswns6541@gmail.com]
📎 [포트폴리오 페이지](https://lyrical-whip-4de.notion.site/efff09da440d4c829340ed2e10ebc7c2)
📎 [블로그 주소](https://pmj6541.tistory.com/)


## 📄 문서

- [Google Sheets API 문서](https://docs.google.com/spreadsheets/d/1HMrlLnVL813SxnZgmdXFxLfmtvMtjyJOLot9tstvuHE/edit?usp=sharing)  
  Google Sheets를 백엔드와 연동하기 위한 API 명세 및 예시를 포함하고 있습니다.

- [DB 설계 문서](https://docs.google.com/spreadsheets/d/1-gZdekZA0sZV4G4f0Ji_omD6kbXGhRuTWbtMZbJ9u08/edit?usp=sharing)  
  데이터베이스 테이블 구조와 관계를 문서화한 설계서입니다.

> 문서는 모두 링크 공개로 설정되어 있어, 누구나 열람 가능합니다.
