# <img src="https://user-images.githubusercontent.com/52441923/152638342-dcb81449-8568-4711-9334-a5a77d406a6a.png" width="40"></img> CHRESH PET!

## backend 실행

1. build

   ```shell
   cd backend
   gradlew clean build
   ```

2. 실행

   ```shell
   cd build/libs
   java -jar backend-0.0.1-SNAPSHOT.jar
   ```

3. swagger port  
   http://localhost:8080/swagger-ui.html
    
4. API Documentation  
    -- **MEMBER** --  
    1) 회원가입    
        [POST] `localhost:8080/api/v1/members`   
        [RequestBody]   
        ```shell 
        {
            "username":"qwe123",
            "password":"qwe123",
            "name":"qwe123"
        }
        ```
    2) 로그인   
        [POST] `localhost:8080/api/v1/authenticate`  
        [RequestBody]    
        ```shell
        {
            "username": "admin",
            "password": "admin"
        }
        ```
        +) admin 관리자 계정은 미리 생성해놓았으므로 테스트시 admin으로 바로 로그인 가능   
        +) API 요청시 ResponseBody에 JWT 토큰 리턴   
        +) swagger test시 오른쪽 측면에 있는 Authrization 버튼 누르고 "Bearer" + " " + 발급받은 Token 키 입력 -> 다른 API 사용 가능  
        +) 헤더에 토큰 추가하지 않을 경우 UnAuthorized error 발생   

  
    -- **POST** --  
    3) 게시물 생성  
        [POST] `localhost:8080/api/v1/post` 
        [RequestBody] 
        ```shell
        {
            "title":"test",
            "place":"test",
            "region":"region",
            "phoneNum":"1234",
            "volunteerDate":"2019-12-11",
            "volunteerTime":"23:59:59",
            "requiredNum":1,
            "content":"test",
            "imageURL":"zbcde",
            "category":"Facility"
        }
        ```
    4) 게시물 삭제  
        [DELETE] `localhost:8080/api/v1/post/{postId}`  
        게시물 아이디를 pathVariable로 함께 넣는다.   
    5) 나의 게시물 조회   
        [GET] `localhost:8080/api/v1/post`  
    
    -- **APPLICATION** --  
    5) 봉사 신청   
        [POST] `localhost:8080/api/v1/application/{postId}`  
        신청하고자 하는 게시물 아이디를 pathVariable로 함께 넣는다.  
    6) 봉사 취소  
        [DELETE] `localhost:8080/api/v1/application/{applicationId}`  
        취소하고자 하는 봉사신청아이디를 pathVariable로 함께 넣는다.   
        --> 신청 아이디를 FE가 저장할 수 없다면 변경될수도!!   
    7) 나의 신청내역 조회
        [GET] `localhost:8080/api/v1/application`

      
     
