# foslog
Spring Boot로 시작하는 블로그  
http://ec2-15-165-129-176.ap-northeast-2.compute.amazonaws.com

## issues and development
- [X] 서버에서 네이버 로그인 안됨

Caused by: java.sql.SQLException: Incorrect string value: '\xEA\xB9\x80\xEB\xB3\x91...' for column `foslog`.`user`.`name` at row 1  
해결 -> USER Table의 Charset을 utf8mb4로 변경!

- [ ] 로그인 화면 만들기!

- [ ] 고객 관리 시스템 만들기!

- [ ] Gmail Service 중 Deprecated 된 함수가 있는것 같으니 확인하기