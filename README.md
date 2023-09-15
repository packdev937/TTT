# TTT
TTT는 **Transfer To Tistory**의 약자로 노션 페이지를 티스토리 블로그에 업로드 하는 스프링 부트 기반 서비스입니다.

## 버전 
v1.0.0-beta: Notion To Tistory에 대한 핵심 기능 구현

v1.0.1-beta: Notion Page를 Upload 시 Status가 UPLOADING에서 COMPLETED로 자동 변환
## 사전 준비
서비스를 이용하기 위해서는 Notion API Integration에 Notion 페이지 등록을 진행해야 합니다.

서비스를 이용하기 위해서는 Tistory OPEN API에 블로그를 등록해야 합니다.

## 기능 설명
노션 데이터베이스 테이블에 POST 요청하여 데이터베이스 내 페이지의 정보를 JSON 형태로 응답 받습니다.

이를 내부 로직을 통해 적절하게 HTML 형식의 문서로 변환한 후 Tistory API를 통해 글 작성 POST 요청을 보냅니다.

