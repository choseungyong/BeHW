# 🛒 상품 관리 API

Spring Boot 기반으로 상품 정보를 조회, 추가, 수정, 삭제할 수 있는 간단한 HTTP API입니다.  
메모리 기반 저장소를 사용하며, JSON 형식으로 요청/응답이 이루어집니다.

---

## ✅ 사용 기술

- Java 21
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- H2 Database (in-memory)
- Lombok
- JUnit

---

## ✅ 기능 목록

- [ ] 상품 전체 조회 API (`GET /api/products`)
- [ ] 상품 단건 조회 API (`GET /api/products/{id}`)
- [ ] 상품 등록 API (`POST /api/products`)
- [ ] 상품 수정 API (`PUT /api/products/{id}`)
- [ ] 상품 삭제 API (`DELETE /api/products/{id}`)
- [ ] 단위 테스트 작성
- [ ] E2E 테스트 작성

---

## ✅ 도메인 설계

```java
Product {
  Long id;
  String name;
  int price;
  String imageUrl;
}
