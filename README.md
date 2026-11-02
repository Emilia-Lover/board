# 📋 Spring Boot 게시판

Spring Boot와 MariaDB로 만든 게시판입니다.

## 주요 기능
- 글 목록 / 작성 / 상세보기 / 수정 / 삭제 (CRUD)
- 조회수
- 공지사항 (목록 상단 고정)
- 글 번호 자동 정렬 (삭제해도 1번부터 이어짐)
- 입력값 검증 (제목·내용·작성자 필수)

## 기술 스택
- Java 21
- Spring Boot 4
- Spring Data JPA
- MariaDB
- Thymeleaf
- Lombok

## 실행 방법
1. MariaDB에 데이터베이스를 만듭니다.

   ```sql
   CREATE DATABASE board_db DEFAULT CHARACTER SET utf8mb4;
   ```

2. `src/main/resources/application-secret.properties.example`을 복사해서
   `application-secret.properties`로 이름을 바꾸고 DB 계정 정보를 입력합니다.

3. `BoardApplication`을 실행하고 http://localhost:8080 에 접속합니다.