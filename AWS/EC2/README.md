<h1> ec2 접속 </h1>
1.키 파일의 접근권한 변경<br>
$ chmod 400 ~/Desktop/AWSkey/keyfile.pem

2.맥 터미널을 이용한 접속<br>
$ ssh -i ~/Desktop/AWSkey/keyfile.pem ec2-user@[서버 아이피 또는 도메인]

<h1> ec2 시간대 변경 </h1>
1.기본 시간대 설정은 UTC로 설정되있는데 이거를 한국표준시간대로 변경해줘야함
<img width="844" alt="스크린샷 2020-03-06 오후 3 45 47" src="https://user-images.githubusercontent.com/44339530/76059218-9583ef80-5fc1-11ea-9aa7-53550a9dfb2c.png"><br>

2.적용 가능한 시간대 확인
<img width="844" alt="스크린샷 2020-02-11 오전 2 27 31" src="https://user-images.githubusercontent.com/44339530/76059311-e0056c00-5fc1-11ea-9aac-9370359397e3.png">

3.시스템 표준시간대 설정 파일을 Asia/Seoul로 변경
<img width="844" alt="스크린샷 2020-02-11 오전 2 25 37" src="https://user-images.githubusercontent.com/44339530/76059363-ff03fe00-5fc1-11ea-80ef-a0e186b5d49c.png">

<img width="844" alt="스크린샷 2020-02-11 오전 2 25 25" src="https://user-images.githubusercontent.com/44339530/76059424-26f36180-5fc2-11ea-846b-16254514c934.png">

4./etc/localtime을 해당 표준시간대 파일로 소프트링크로 연결
<img width="844" alt="스크린샷 2020-02-11 오전 2 26 52" src="https://user-images.githubusercontent.com/44339530/76059511-6326c200-5fc2-11ea-963e-e6e16192aa03.png">

5.시스템 재부팅
<img width="844" alt="스크린샷 2020-02-11 오전 2 29 18" src="https://user-images.githubusercontent.com/44339530/76059618-a8e38a80-5fc2-11ea-8944-47314889f29d.png">

6.변경된 표준시간대 확인
<img width="844" alt="스크린샷 2020-02-11 오전 2 29 54" src="https://user-images.githubusercontent.com/44339530/76059647-c0227800-5fc2-11ea-8f86-da68618808da.png">

<h1> FileZilla를 통한 파일 전송 </h1>
1.설정 -> SFTP -> 키 파일 추가
<img width="844" alt="스크린샷 2020-03-06 오후 3 57 30" src="https://user-images.githubusercontent.com/44339530/76059851-3c1cc000-5fc3-11ea-80c1-457e604a285c.png">

2.파일 -> 사이트관리자에서 다음과 같이 설정 후 연결 후 ec2로 파일 복사
(호스트에는 AWS 에 있는 Pubilc IP 주소를 넣어주면 됨)
<img width="844" alt="스크린샷 2020-03-06 오후 4 13 50" src="https://user-images.githubusercontent.com/44339530/76060883-a8002800-5fc5-11ea-8b94-39144288253c.png">

※ TIP
Amazon Linux 2 또는 Amazon Linux AMI의 경우 사용자 이름은 ec2-user
Centos AMI의 경우 사용자 이름은 centos
Debian AMI의 경우 사용자 이름은 admin 또는 root
Fedora AMI의 경우 사용자 이름은 ec2-user 또는 fedora
RHEL AMI의 경우 사용자 이름은 ec2-user 또는 root
SUSE AMI의 경우 사용자 이름은 ec2-user 또는 root
Ubuntu AMI의 경우 사용자 이름은 ubuntu

※ 참고자료
https://rumor1993.tistory.com/29

<h1> jdk 설치 </h1>
1. rpm을 통한 설치
<img width="844" alt="스크린샷 2020-03-06 오후 4 26 13" src="https://user-images.githubusercontent.com/44339530/76061601-39bc6500-5fc7-11ea-9727-b0aa657caf12.png">

2.jdk가 여러 설정되있을 경우  
<img width="844" alt="스크린샷 2020-03-06 오후 4 28 48" src="https://user-images.githubusercontent.com/44339530/76062538-3e821880-5fc9-11ea-81d6-1c0ddfed317b.png">

3.환경 변수 설정
<img width="844" alt="스크린샷 2020-03-06 오후 4 43 38" src="https://user-images.githubusercontent.com/44339530/76062743-a46ea000-5fc9-11ea-9919-cccee4b627cc.png">

<img width="844" alt="스크린샷 2020-03-06 오후 4 43 05" src="https://user-images.githubusercontent.com/44339530/76062732-9c166500-5fc9-11ea-9d9a-b4f215a8e38e.png">

<img width="844" alt="스크린샷 2020-03-06 오후 4 44 55" src="https://user-images.githubusercontent.com/44339530/76062830-d4b63e80-5fc9-11ea-9947-533792739d6c.png">

<h1> 톰캣 설치 </h1>
1.tomcat그룹 및 유저생성
<img width="844" alt="스크린샷 2020-03-06 오후 4 53 59" src="https://user-images.githubusercontent.com/44339530/76063462-1b586880-5fcb-11ea-9240-86ba6a73ada2.png">
2.톰캣 디렉토리 생성
<img width="844" alt="스크린샷 2020-03-06 오후 4 49 00" src="https://user-images.githubusercontent.com/44339530/76063123-70e04580-5fca-11ea-8304-caa4d6f3bd0a.png">

3.tar를 통한 압축풀기
<img width="844" alt="스크린샷 2020-03-06 오후 4 50 06" src="https://user-images.githubusercontent.com/44339530/76063183-8e151400-5fca-11ea-9830-6a9638e985cc.png">

4.퍼미션 변경
<img width="844" alt="스크린샷 2020-03-06 오후 4 55 40" src="https://user-images.githubusercontent.com/44339530/76063568-58bcf600-5fcb-11ea-9c3a-4eee47fc3f3d.png">

5.톰캣을 서비스로 만들기 위한 Systemd Unit File생성
<img width="844" alt="스크린샷 2020-03-06 오후 4 57 08" src="https://user-images.githubusercontent.com/44339530/76063692-9457c000-5fcb-11ea-8ff9-08e4e34930be.png">
<img width="844" alt="스크린샷 2020-03-06 오후 4 57 28" src="https://user-images.githubusercontent.com/44339530/76063697-96218380-5fcb-11ea-8008-056a73ccf2db.png">

6.톰캣unit file을 로드시키기위해 reload수행
<img width="844" alt="스크린샷 2020-03-06 오후 4 58 51" src="https://user-images.githubusercontent.com/44339530/76063816-c832e580-5fcb-11ea-8e2a-dcae96d0c43f.png">

7.tomcat 시스템 설정
sudo systemctl start tomcat
sudo systemctl stop tomcat
sudo systemctl status tomcat
sudo systemctl enable tomcat


8.tomcat web management 인터페이스 설정
<img width="844" alt="스크린샷 2020-03-06 오후 5 07 12" src="https://user-images.githubusercontent.com/44339530/76064411-f36a0480-5fcc-11ea-8dcc-a649c77d55ed.png">
<img width="844" alt="스크린샷 2020-03-06 오후 5 06 28" src="https://user-images.githubusercontent.com/44339530/76064416-f49b3180-5fcc-11ea-980b-1e394cf3d850.png">

9.(디폴트로 톰캣은 서버자체에서 오는 connection에 대해 제한하기 때문에) 
원격으로 톰캣매니저를 접근하는 경우는 막혀져있기 때문에 풀어줘야함 설정 파일 두개가서 해당부분 주석처리해줘야함 
<img width="844" alt="스크린샷 2020-03-06 오후 5 09 01" src="https://user-images.githubusercontent.com/44339530/76064562-40e67180-5fcd-11ea-93c9-3bb30b23ed06.png">
<img width="844" alt="스크린샷 2020-03-06 오후 5 08 34" src="https://user-images.githubusercontent.com/44339530/76064566-42b03500-5fcd-11ea-8a4f-755bf10f2780.png">

10.변경 사항 반영을 위한 톰캣 재실행
<img width="844" alt="스크린샷 2020-03-06 오후 5 12 38" src="https://user-images.githubusercontent.com/44339530/76064794-b8b49c00-5fcd-11ea-8f02-5c905999284b.png">

※ 참고자료
https://www.digitalocean.com/community/tutorials/how-to-install-apache-tomcat-8-on-centos-7

<h2> mysql 설치 </h2>
1.Yum repository설치
<img width="844" alt="스크린샷 2020-02-11 오전 1 38 36" src="https://user-images.githubusercontent.com/44339530/76160247-c9027d80-616b-11ea-869b-79249aa7d2f6.png">

2.Yum repository에는 다양한 mysql버전들이 있기에 mysql repo파일의 모든 레포지들을 비활성화 시켜야한다.
<img width="844" alt="스크린샷 2020-02-11 오전 1 39 23" src="https://user-images.githubusercontent.com/44339530/76160286-36161300-616c-11ea-9ff4-4137d933aac4.png">

3.mysql community server설치
<img width="844" alt="스크린샷 2020-02-11 오전 1 39 47" src="https://user-images.githubusercontent.com/44339530/76160295-4e862d80-616c-11ea-8817-fd5cdf520273.png">

4.mysql systemd를 사용한 서비스시작 및 상태확인
<img width="844" alt="스크린샷 2020-02-11 오전 1 41 11" src="https://user-images.githubusercontent.com/44339530/76160321-80978f80-616c-11ea-97f3-b6c9e29f4384.png"><br>
<img width="536" alt="스크린샷 2020-02-11 오전 1 41 31" src="https://user-images.githubusercontent.com/44339530/76160341-a886f300-616c-11ea-9bcb-f824307439a8.png">

5.mysql default root password 확인
<img width="844" alt="스크린샷 2020-03-08 오후 6 45 07" src="https://user-images.githubusercontent.com/44339530/76160364-fb60aa80-616c-11ea-8f32-c5e8be0401ee.png">

6.secure mysql server를 위한 command 실행
<img width="844" alt="스크린샷 2020-02-11 오전 1 45 42" src="https://user-images.githubusercontent.com/44339530/76160385-424ea000-616d-11ea-8c77-d49d1dcf954d.png">

7.root password 변경
<img width="844" alt="스크린샷 2020-02-11 오전 1 45 42" src="https://user-images.githubusercontent.com/44339530/76160452-c012ab80-616d-11ea-8645-0ea51df34a2c.png"><br>
<img width="844" alt="스크린샷 2020-02-11 오전 1 46 12" src="https://user-images.githubusercontent.com/44339530/76160467-db7db680-616d-11ea-8df2-c3a1683cd531.png">

8.mysql server 재시작
<img width="844" alt="스크린샷 2020-02-11 오전 1 46 44" src="https://user-images.githubusercontent.com/44339530/76160493-f51efe00-616d-11ea-94c0-aeffba10ac42.png">

9.mysql 자동활성화
<img width="844" alt="스크린샷 2020-02-11 오전 1 47 04" src="https://user-images.githubusercontent.com/44339530/76160518-27c8f680-616e-11ea-942a-68012296a98f.png">

10.mysql 접속
<img width="844" alt="스크린샷 2020-02-11 오전 1 47 35" src="https://user-images.githubusercontent.com/44339530/76160525-3fa07a80-616e-11ea-8ede-adc6b43a26ce.png">

※ 참고자료
https://tecadmin.net/install-mysql-8-on-centos/

