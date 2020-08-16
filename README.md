# foslog
Spring Boot로 시작하는 블로그  
http://ec2-15-165-129-176.ap-northeast-2.compute.amazonaws.com

## issues
- [X] 서버에서 네이버 로그인 안됨

Caused by: java.sql.SQLException: Incorrect string value: '\xEA\xB9\x80\xEB\xB3\x91...' for column `foslog`.`user`.`name` at row 1  
USER Table의 Charset을 utf8mb4로 변경!