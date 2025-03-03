# MSA 기반 뉴스 및 채팅 서비스

## 프로젝트 소개

이 프로젝트는 마이크로서비스 아키텍처(MSA)를 기반으로 개발된 시스템으로, 실시간 주식 및 암호화폐 뉴스 제공(News Service)과 실시간 채팅(Chat Service) 기능을 제공합니다. Spring Boot와 Spring Cloud 기술 스택을 활용하여 개발되었으며, Docker 컨테이너화 및 GitHub Actions를 통한 CI/CD 파이프라인을 구축하여 확장 가능하고 유지보수가 용이한 시스템을 구현했습니다.

## 주요 서비스 구성

### News Service (msa-news)
- **역할**: 주식 및 암호화폐 관련 뉴스 제공
- **포트**: 8086
- **주요 기능**:
  - 주식 관련 뉴스 검색 (Naver API 활용)
  - 암호화폐 관련 뉴스 검색 (Naver API 활용)
  - 웹 인터페이스 제공 (news.html)

### Chat Service (msa-news 내 포함)
- **역할**: 실시간 사용자 채팅 기능
- **포트**: 8086 (News Service와 동일)
- **주요 기능**:
  - WebSocket 기반 실시간 메시지 전송
  - 로그인/비로그인 사용자 모두 참여 가능
  - 채팅 내역 저장 및 조회
  - 웹 인터페이스 제공 (chat.html)

## 기술 스택

- **백엔드**: Spring Boot 3.4.2, Java 17
- **보안**: Spring Security
- **데이터베이스**: MySQL, Redis
- **서비스 통신**: WebClient, WebSocket
- **빌드/배포**: Docker, Docker Compose, GitHub Actions
- **서비스 디스커버리**: Eureka
- **인프라**: AWS EC2

## 주요 구현 사항

1. **뉴스 검색 기능**
   - Naver Open API를 활용한 주식 및 암호화폐 뉴스 검색
   - WebClient를 통한 비동기 API 요청 처리
   - 검색 결과 렌더링을 위한 웹 인터페이스

2. **실시간 채팅 시스템**
   - STOMP 프로토콜을 활용한 WebSocket 메시징
   - 사용자 인증 연동 (로그인 시 닉네임 표시)
   - 채팅 메시지 영구 저장 및 이력 조회
   - 실시간 메시지 브로드캐스팅

3. **인증 및 보안**
   - Spring Security 기반 보안 설정
   - CORS 설정을 통한 프론트엔드 연동
   - 채팅 시 사용자 정보 추출 및 활용

4. **자동화된 CI/CD 파이프라인**
   - GitHub Actions를 통한 빌드 및 배포 자동화
   - Docker Compose를 통한 서비스 관리
   - 민감 정보 보안을 위한 GitHub Secrets 활용

## 아키텍처 다이어그램

```
클라이언트 → API Gateway → 마이크로서비스 → Naver API
                  ↓
            Eureka Server
                  ↓
          ┌───────┴───────┐
          ↓               ↓
   News Service       User Service
     (8086)             (외부)
          ↓
      MySQL DB
```

## 배포 환경

AWS EC2 인스턴스에 Docker Compose를 통해 배포되며, GitHub Actions 워크플로우를 통한 자동 배포가 구성되어 있습니다. 코드 변경 시 main 브랜치에 병합되면 자동으로 빌드 및 배포가 진행됩니다.

## 주요 API 기능

### News Service
- `/api/news/stock` - 주식 관련 뉴스 검색
- `/api/news/crypto` - 암호화폐 관련 뉴스 검색

### Chat Service
- `/ws` - WebSocket 연결 엔드포인트
- `/topic/messages` - 메시지 구독 주제
- `/app/message` - 메시지 전송 엔드포인트
- `/chat/all` - 모든 채팅 내역 조회

## 웹 인터페이스

- `/news.html` - 주식 및 암호화폐 뉴스 검색 인터페이스
- `/chat.html` - 실시간 채팅 인터페이스

## 개발자 참고사항

- 네이버 API 키는 보안을 위해 GitHub Secrets에 저장되어 있음
- 서비스는 Eureka 서버(34.210.11.121:8761)에 자동 등록됨
- 프론트엔드는 52.38.38.236:3000에서 접근 가능
- WebSocket 연결은 SockJS를 통해 이루어지며 폴백 메커니즘 지원

## 시스템 운영

시스템은 Docker Compose를 통해 관리되며, 서비스는 독립적인 컨테이너로 실행됩니다. 서비스 로그는 Docker logs 명령을 통해 확인할 수 있으며, 배포 과정에서 자동으로 수집됩니다. 자동 재시작 설정이 활성화되어 있어 서비스 장애 시 자동으로 복구됩니다.
