## 🚂놀이공원 기구 예약 시스템
> 놀이공원에서 불필요한 대기시간을 최소화 시키기 위한 놀이기구 예약 시스템

## 팀원 : 방지원👩🏻‍💻, 이기환👨🏻‍💻, 정은진(B)👩🏻‍💻, 최해림👩🏻‍💻


## 1. Modeling 🖥️

![Untitled](https://user-images.githubusercontent.com/87870107/130889940-fea8bf25-f9a3-4c42-bad7-c374418e445d.png)

## 2. Function🛠

[놀이기구관련 기능]🎢

1. 놀이기구 전체 리스트 조회하기
2. 하나의 놀이기구 정보 조회하기
3. 놀이기구 정보 추가하기
4. 놀이기구 정보 수정하기
5. 놀이기구 정보 삭제하기

[고객관련 기능]🙋‍♀️🙋‍♂️

1. 고객 전체 리스트 조회하기
2. 한 명의 고객 정보 조회하기
3. 고객 정보 추가하기
4. 고객 정보 수정하기
5. 고객 정보 삭제하기

[예약관련 기능]⏰

1. 전체 예약 리스트 조회하기
2. 하나의 예약 정보 조회하기
3. 예약 추가하기
4. 예약 정보 수정하기
5. 예약 정보 삭제하기

## 3. Code Review by 민경준, 박세은, 신지혜💻✍🏻

- 신지혜님

    1) persistence.xml 에서 콘솔에 Hibernate 출력 부분 고민 요망

    #️⃣해결점 → show_sql : false로 수정

    2) 클래스와 메소드 설명 주석부분이 깔끔해서 좋습니다

- 박세은님

    1) 이미 존재하는 데이터 추가 되지 않도록 구현 요망

    #️⃣해결점 → DAO에서 parameter값 find로 찾은 후 null일 경우 중복 추가 방지

- 민경준님

    1) toString 재정의를 통해 조금 더 예쁘게 출력될 수 있으면 좋을 것 같음

## 4. 아쉬운 점😥

1. 예외 처리 구현이 미흡해서 조금 아쉽다.
2. Entity별로 기능을 구현하다보니 관리자 입장과 사용자 입장을 구분해놓지 않았던 점이 아쉽다.
3. 본인 실력 부족으로 인해 키 제한에 관련된 Attraction을 구현할 때 과도기에 있는 키를 제대로 구현하지 못 한 것이 아쉽다.
4. GitHub 사용이 미숙해 개발 이외에 작업한 내용을 통합하는 부분에 시간을 많이 할당 하였다. 그렇다 보니 리팩토링 시간이 너무 부족해 이전과 비교했을 때 견고한 코드를 구성하기에 어려움이 있었다.
5. 다음 프로젝트 때 GitHub 사용시에는 팀원들끼리 서로 겹치는 기능이 없도록 역할 분담을 정확히 하면 더 좋은 결과를 얻을 수 있을것으로 예상된다!



