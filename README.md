# api-gateway

## Overview

Spring Cloud Gateway를 이용한 프로젝트입니다. 이 프로젝트는 다음과 같은 주요 기능 및 라이브러리를 활용하고 있습니다

- Spring Cloud Gateway
- Spring Boot Actuator
- Eureka Client for service discovery
- Spring Cloud Config for centralized configuration
- Reactive Redis data access
- JWT for security

## Requirements

- Java 17
- Spring Cloud Gateway
- Spring WebFlux

## Stack

<p align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/redis/redis-original.svg" alt="redis" width="40" height="40"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" alt="spring" width="40" height="40"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" alt="java" width="40" height="40"/>
</p>

## Mechanism

<img width="382" alt="api-gateway" src="https://github.com/wooriFisa-Final-Project-F4/.github/assets/109801772/404bf0f9-77e4-42c1-812a-2cef26c32970">

사용자의 요청은 RouteValidator를 거쳐 Filtering될 요청인지 아닌지를 구분짓습니다. 이후, Filter를 통과하며, Token에서 값을 추출하고, 요청 헤더에 저희가 지정한 헤더를 추가해줍니다.
