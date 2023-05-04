# Shortify 🔗

![](https://velog.velcdn.com/images/balparang/post/7e605732-79ae-4442-bc23-86e8f2d75f6d/image.png)

_"긴 URL을 짧은 URL로 단축시켜주는 애플리케이션"_

### 기술 스택

- Java 17
- Spring Boot 2.7.8
- Spring Data JPA
- Junit5
- Spring REST Docs
- MySQL

## 📜 주요기능

### 단축 URL 생성

- 원본 URL을 DB에 저장하고, PK를 기준으로 [BASE62 인코딩](src/main/java/com/haero77/urlshortener/domain/url/util/Base62Encoder.java)을 통해
  단축 URL 생성

### 단축 URL에 대한 통계 제공

```json
HTTP/1.1 200 OK
Content-Type: application/json

{
  "shortenedUrl" : "shortenedUrl",
  "originUrl" : "https://github.com/haero77",
  "viewCount" : {
    "totalViewCount" : 3,
    "viewCountPerDate" : [ {
      "date" : "2023-04-28",
      "viewCount" : 2
    }, {
      "date" : "2023-04-29",
      "viewCount" : 1
    } ],
    "viewCountPerReferer" : [ {
      "referer" : "GOOGLE",
      "viewCount" : 1
    }, {
      "referer" : "DIRECT",
      "viewCount" : 2
    } ]
  }
}
```

- 전체 조회수, 최근 7일간의 일자별 조회수, HTTP Referer 별 조회수 조회 기능 제공

(기타 세부 구현사항은 [여기](https://github.com/haero77/Shortify/wiki/%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD)서 확인하실 수 있습니다.)

## 🎨 설계

### ERD

<img src="https://velog.velcdn.com/images/balparang/post/49acc071-bbc1-45f2-b63d-893bcf013658/image.png" width="500">

- URL
  - 원본 URL, 단축된 URL, 유효기간 등을 기록
- URL_CALL
  - 단축 URL이 생성될 때마다 Row가 추가되며, 호출 시간과 HTTP Referer를 기록

##  

### 클래스 다이어그램

![](https://velog.velcdn.com/images/balparang/post/01cf46d8-e8d3-4e55-9a5f-9955ff4e3dfb/image.png)

레이어드 아키텍처를 적용하고, Presentation to Domain 으로 의존성이 단방향으로 흐르도록 설계.

- **_하나의 엔드포인트를 갖는 컨트롤러 객체_**
  - 컨트롤러 객체 하나당 하나의 엔드포인트를 담당하여 SRP를 준수
- **_단 하나의 메소드를 제공하는 서비스 객체_**
  - Data Read를 담당하는 `UrlReader`를 제외한 서비스 객체는 **단 하나의 public 메소드**를 갖도록 설계하여 SRP를 준수하고, Command와 Query를 명확히 분리.
  - `UrlReader`는 다른 서비스 객체를 의존하지 않게하여 **순환참조 문제를 방지**
- 캡슐화를 지키는 도메인 객체
  - 비즈니스 로직을 최대한 도메인 객체가 수행하게 설계하고, 상태와 기능을 묶음으로써 `캡슐화`를 준수

## 📝 프로젝트 문서

### 트러블 슈팅

### 회고록


