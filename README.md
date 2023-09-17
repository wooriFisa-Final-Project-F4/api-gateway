# api-gateway
> Spring Cloud Gateway를 이용한 프로젝트입니다. 
<br>

## 목차
- [Dependency](#-dependency) <br>
- [api-gateway 흐름](#api-gateway-흐름) <br>
<br> 

## 🛠️ Dependency
|       기능       | 기술 스택                                                                       |
|:--------------:|:----------------------------------------------------------------------------|
|  Spring Boot   | - Spring Framework 2.7.15<br> - Java 17 <br> - Gradle 8.0 <br> - Spring Web <br> - WebFlux |
|  Spring Cloud  | - Eureka <br> - Config <br> - Gateway <br>                                  |
|    Database    | - Redis Client                                                              |
| Authentication | - JWT                                                                       |
|   Monitoring   | - Actuator <br> - Spring Cloud Sleuth                                       |

<br>

<br>

## 🔄 api-gateway 흐름 
<br>

<img width="382" alt="api-gateway" src="https://github.com/wooriFisa-Final-Project-F4/.github/assets/109801772/404bf0f9-77e4-42c1-812a-2cef26c32970">

사용자의 요청은 RouteValidator를 거쳐 Filtering될 요청인지 아닌지를 구분짓습니다. 이후, Filter를 통과하며, Token에서 값을 추출하고, 요청 헤더에 저희가 지정한 헤더를 추가해줍니다.
