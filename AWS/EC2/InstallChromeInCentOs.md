<h2> Chrome 설치 </h2>
1.yum 저장소 생성<br>
<img width="844" alt="스크린샷 2020-05-07 오후 4 36 04" src="https://user-images.githubusercontent.com/44339530/81267116-e32fdd80-9080-11ea-929e-8c1b503f291c.png"><br>
sudo vi /etc/yum.repos.d/google-chrome.repo<br>

-아래와 같이 파일 내용작성<br>
<img width="844" alt="스크린샷 2020-05-07 오후 4 37 14" src="https://user-images.githubusercontent.com/44339530/81267188-035f9c80-9081-11ea-9ee2-e653460728ef.png"><br>
[google-chrome]<br>
name=google-chrome<br>
baseurl=http://dl.google.com/linux/chrome/rpm/<br>stable/$basearch<br>
enabled=1<br>
gpgcheck=1<br>
gpgkey=https://dl-ssl.google.com/linux/<br>linux_signing_key.pub<br>

2.설치<br>
<img width="844" alt="스크린샷 2020-05-07 오후 4 40 08" src="https://user-images.githubusercontent.com/44339530/81267420-6b15e780-9081-11ea-8e44-c81545489a18.png"><br>
-><br>
sudo yum install google-chrome-stable<br>

3.확인<br>
<img width="844" alt="스크린샷 2020-05-07 오후 4 41 05" src="https://user-images.githubusercontent.com/44339530/81267497-8c76d380-9081-11ea-8244-db02636c6a04.png"><br>
google-chrome --version<br>

<h2> Chrome Driver 설치 </h2>
-아래 코드 실행(chrome 버전에 따라 78.0.3904.70 이부분은 수정해야함)<br>
<img width="844" alt="스크린샷 2020-05-07 오후 4 48 39" src="https://user-images.githubusercontent.com/44339530/81268204-9c42e780-9082-11ea-99a8-fada3eada9fa.png"><br>
wget -N http://chromedriver.storage.googleapis.com/78.0.3904.70/chromedriver_linux64.zip -P ~/Downloads<br>
unzip ~/Downloads/chromedriver_linux64.zip<br>
sudo mv /usr/local/bin/chromedriver<br>

<h2> unzip 설치 </h2>
rpm -qa | grep unzip<br>
yum list unzip<br>
sudo mv ~/Downloads/chromedriver /usr/local/bin/<br>