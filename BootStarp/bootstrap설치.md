<h1>bootstrap설치</h1>
https://getbootstrap.com/<br>

1.Download버튼 클릭<br>

<img width="844" alt="스크린샷 2020-03-17 오후 6 38 40" src="https://user-images.githubusercontent.com/44339530/76843082-9e58a900-687e-11ea-99e0-840edc01d34d.png"><br>

-download받아서 local에 저장하는 방식<br>
->이 방식 사용<br>
<img width="844" alt="스크린샷 2020-03-17 오후 6 40 10" src="https://user-images.githubusercontent.com/44339530/76843267-e2e44480-687e-11ea-9c0e-caeceed7cec2.png"><br>
-CDN을 활용하는 방식<br>
->CDN에는 컴파일된 css와 javascript에 대한 cached 버전을 가지고 있어서 동적으로 url을 통해 가져오는 방식<br>
<img width="844" alt="스크린샷 2020-03-17 오후 6 41 11" src="https://user-images.githubusercontent.com/44339530/76843280-e5df3500-687e-11ea-8417-c064140ff0fe.png">
<br>

-bootstrap.css 와 boostrap.min.css 둘 중 아무거나 사용해도 상관없음<br>
-bootstrap.js 와 boostrap.min.js 둘 중 아무거나 사용해도 상관없음<br>
-min.js가 사이즈가 더 작아서 사용예정<br>

2.Carousel템플릿 사용<br>
-페이지 소스 보기로 코드 복사<br>
-다운로드 받은 부트스트랩폴더 bootstrap-4.4.1-dist에 index.html생성<br>
<img width="844" alt="스크린샷 2020-03-17 오후 6 48 36" src="https://user-images.githubusercontent.com/44339530/76843971-f348ef00-687f-11ea-90a8-391792e3a3a6.png"><br>

3.에디터로 title 변경 및 css 파일 재설정<br>
<img width="844" alt="스크린샷 2020-03-17 오후 6 58 36" src="https://user-images.githubusercontent.com/44339530/76844895-59824180-6881-11ea-94db-16a4693e1c41.png"><br>

4.Favicons설정  -> title옆의 이미지<br>
-소스보기에서 바탕화면으로 다운로드 후 경로지정<br>
<img width="844" alt="스크린샷 2020-03-17 오후 7 00 30" src="https://user-images.githubusercontent.com/44339530/76845098-a2d29100-6881-11ea-9582-b911ff61d072.png"><br>
<img width="844" alt="스크린샷 2020-03-17 오후 7 06 18" src="https://user-images.githubusercontent.com/44339530/76845566-69e6ec00-6882-11ea-877a-ea0b4898860e.png">
<br>

5.소스보기에서 복사해서 Carousel.css 생성 후 경로 지정<br>
<img width="844" alt="스크린샷 2020-03-17 오후 7 11 04" src="https://user-images.githubusercontent.com/44339530/76845959-15903c00-6883-11ea-80cd-3b4bd1d1cfa4.png">
<br>

6. feature부분 삭제 및 footer부분 js 경로 재지정<br>
<img width="844" alt="스크린샷 2020-03-17 오후 7 15 58" src="https://user-images.githubusercontent.com/44339530/76846385-c1d22280-6883-11ea-99f8-000c0506430e.png"><br>

7.테스트<br>
-서버에 올려놓으면 문제없이 작동하지만 로컬에 css, js파일이 있기때문에 제대로 로드를 하지 못함<br><br>
->따라서 크롬이 로컬에 있는 파일들을 접근 할 수 있도록 설정해줘야함<br>
--allow-file-access-from-files 옵션을 설정 <br>

①맥 -> 터미널로 다음과 같이 옵션을 줘서 실행 <br> 
open /Applications/Google\ Chrome.app --args --allow-file-access-from-files<br>
<img width="844" alt="스크린샷 2020-03-17 오후 7 46 45" src="https://user-images.githubusercontent.com/44339530/76849105-1bd4e700-6888-11ea-82ff-a0494d5469ac.png">
<br>

②window -> 크롬 속성의 대상 부분에 다음 옵션 추가<br>
<img width="844" alt="스크린샷 2020-03-17 오후 7 43 31" src="https://user-images.githubusercontent.com/44339530/76848774-994c2780-6887-11ea-935a-079bd8492682.png">
<br>

8.결과<br>
<img width="844" alt="스크린샷 2020-03-17 오후 7 46 12" src="https://user-images.githubusercontent.com/44339530/76849129-268f7c00-6888-11ea-8710-fe06aa8f9259.png"><br>

※참고자료
http://kb.madcapsoftware.com/Content/Misc/GEN1011Z_-_Loading_local_files_in_Google_Chrome.htm
