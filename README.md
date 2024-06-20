# Shop-Management
✏ 부경대학교 Java 개발자 과정 1차 프로젝트<br>
✏ 미니(개인) 프로젝트('24. 4. 10. ~ '24. 4. 19. 1차 프로젝트) - 쇼핑몰 관리 기능 구현

# 🎈 결과

## 기능(권한별) 개요 및 결과(이미지)
***💡 ▶ 버튼을 클릭하여 첨부된 이미지를 참고 해 주세요 💡***
- ***회원가입***
  - 일반 회원 / 관리자 구분
    
  <details>
    <summary>관리자 등록</summary>
    
    ![관리자 등록](https://github.com/Koeyh/Shop-Management/assets/156414715/75534864-aa02-4b5d-8d42-1f4e5d2c3456)
    - 기존에 DB에 저장된 관리자 코드를 통해 인증을 거침
  </details>
  <details>
    <summary>일반 회원 등록</summary>

    ![일반회원 가입](https://github.com/Koeyh/Shop-Management/assets/156414715/681b21ba-2703-4ac3-b225-7861b684da36)
    
  </details>

- ***관리자***
  - 상품 등록 가능
    <details>
      <summary>관리자 상품등록</summary>

      ![관리자_상품등록](https://github.com/Koeyh/Shop-Management/assets/156414715/23d76cbd-4ec7-4473-9266-0412ae5b723a)
    </details>
  - 전체상품 조회 가능
    <details>
      <summary>관리자 - 전체상품 조회</summary>
 
      ![관리자_상품조회](https://github.com/Koeyh/Shop-Management/assets/156414715/66876b82-0f4f-4bf8-a51e-34bae504d65e)
    </details>
  - 전체 주문내역 조회 가능
    <details>
      <summary>관리자 - 주문내역 전체 조회</summary>
 
      ![관리자_전체주문내역](https://github.com/Koeyh/Shop-Management/assets/156414715/b3a10e0a-d8e4-41ee-b889-ad690b264198)
    </details>
  - 매출액 누계 조회 가능
    <details>
      <summary>관리자 - 주문금액 합계(총 매출액) 조회</summary>
 
      ![관리자_총매출확인](https://github.com/Koeyh/Shop-Management/assets/156414715/be62b79c-0f3d-41be-b740-947a0f95ad24)
    </details>
- ***일반 회원***
  - 상품 조회 가능
    - 브랜드별 조회 가능
      <details>
        <summary>사용자 - 상품 브랜드별 조회</summary>
 
        ![회원_상품조회_브랜드_카테고리](https://github.com/Koeyh/Shop-Management/assets/156414715/e93c6d4d-e3d5-4296-b966-ac06ffbdee8e)
      </details>
    - 분류(상/하의 등)별 조회 가능
      <details>
        <summary>사용자 - 상품 분류별 조회</summary>

        ![회원_상품조회_카테고리별](https://github.com/Koeyh/Shop-Management/assets/156414715/0fe4a771-d850-4b66-9f7e-660140597507)
      </details>
    - 전체 조회 가능
      <details>
        <summary>사용자 - 전체 상품 조회</summary>

        ![회원_상품조회_전체상품](https://github.com/Koeyh/Shop-Management/assets/156414715/dd7b42d6-0f1c-4ee9-bc43-e38c6b03b52d)
      </details>
    - 본인 계정의 구매내역 조회 가능
      <details>
        <summary>사용자 - 구매내역 조회(본인)</summary>
   
        ![회원1](https://github.com/Koeyh/Shop-Management/assets/156414715/55dbc791-0cf3-45d9-a452-a5d461b9b2d3)
        ![회원2](https://github.com/Koeyh/Shop-Management/assets/156414715/03a84d78-e443-4b9f-ae24-37aee54c17b9)
      </details>



# 🔔 착수 전
## 구현 목표
- JDBC를 이용하여 Java 프로그램과 DB간 데이터 연동
- DB의 데이터와 Java의 사용자 입력을 비교하여 로그인 기능 구현
- Java 단에서 쿼리를 작성하여 DB에 저장된 데이터에서 원하는 값을 뽑아쓰기
- 웹 페이지 관련 학습 이전이니 모든 입출력은 콘솔에서 진행

## 개선 방안
- DB 연동을 위해 클래스마다 중복되는 JDBC 객체 호출이 너무 많았다.
- 전반적으로 코드가 많이 지저분하다.
- 가독성이 떨어져 점검이 필요한 상황에 어려움을 겪은적이 있다.
- Mapping, ORM 방식 등을 통한 DB 연동에 대한 학습의 필요성이 느껴짐
