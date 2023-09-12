# Auction-Service

## Overview

Confluent Kafka를 이용한 프로젝트입니다. 이 프로젝트는 다음과 같은 주요 기능 및 라이브러리를 활용하고 있습니다

- Confluent Kafka
- OpenFeign Client
- Eureka Client for service discovery
- Spring Cloud Config for centralized configuration

## Requirements

- Java 17
- Spring Boot
- Confluent Kafka
- OpenFeign Client

## Stack

<p align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" alt="java" width="40" height="40"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" alt="spring" width="40" height="40"/>
  <img src="https://companieslogo.com/img/orig/CFLT-c4a50286.png?t=1627024622" alt="redis" width="40" height="40"/>
</p>

## Mechanism

![auction](https://github.com/wooriFisa-Final-Project-F4/auction-service/assets/119636839/54d4818e-381c-4d23-a8ef-7ed69e29c302)

사용자의 입찰 요청은 api-gateway를 통해 auction-service에 전달된 후 입찰 요청된 상품이 Product-service를 통해 경매가 진행중인 상품인지, Mock API를 통해 입찰 요청 금액을 보유하고 있는지 확인<br>
- 입찰 요청이 유효하지 않은 경우 입찰 요청 실패 메세지를 반환<br>
- 입찰 요청이 유효한 경우 Kafka에 이벤트를 발행합니다.

사용자의 요청은 RouteValidator를 거쳐 Filtering될 요청인지 아닌지를 구분짓습니다. 이후, Filter를 통과하며, Token에서 값을 추출하고, 요청 헤더에 저희가 지정한 헤더를 추가해줍니다.
<br><br>

## 참여자/기여자 목록

|                                                         김혁준<br>LL(Laugh Leader)            |엄수혁<br>TL(Technical Leader)                                                         |
| :-------------------------------------------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------------------------------------: |
| <img alt="김혁준" src="https://github.com/Jimoou/Coding-Test/assets/109801772/8390ed2c-aef6-41cc-a4cb-9f57bc899794" height="100" width="100"> | <img alt="엄수혁" src="https://github.com/Jimoou/Coding-Test/assets/109801772/df375954-fd4b-45ce-b363-d792b02c3400" height="100" width="100"> |
|                                                  [@rlagurnws](https://github.com/rlagurnws)                                                   |                                                                                                     [@endlessmomo](https://github.com/endlessmomo)                                                 |                                                    
|                               안녕하세요 백엔드 개발자를 꿈꾸는 김혁준입니다.<br> 새로운걸 배우는건 늘 짜릿하네요!                                |                                                   항상 궁금증이 가득한 개발자 엄수혁입니다                                                    |                                      
---
