<h1>Advanced Encryption Standard</h1>

※대칭키 암호 알고리즘(암호화키 = 복호화키)<br>

1)DES(56bit key)<br>
exhaustive key<br> search:2^56<br>

solution?
2)3DES(112bit key) : 2^112<br>
3)AES(128,192,256 bit Key)<br>
-반복적으로 block cipher(like DES)<br>
-Not a Feistel cipher(unlike DES)<br>
<img width="327" alt="스크린샷 2020-04-03 오전 11 43 33" src="https://user-images.githubusercontent.com/44339530/78318687-5a64e400-75a0-11ea-9895-b17f0e463206.png"><br>
-각 라운드마다 4개의 function사용<br>
<img width="350" alt="스크린샷 2020-04-03 오전 11 45 19" src="https://user-images.githubusercontent.com/44339530/78318782-9a2bcb80-75a0-11ea-80de-b250acd1fbd0.png"><br>
-한 바이트를 S-Box를 통해서 대치된다<br>
<img width="308" alt="스크린샷 2020-04-03 오전 11 47 33" src="https://user-images.githubusercontent.com/44339530/78318915-e8d96580-75a0-11ea-8767-8c5723490238.png"><br>
-Ex) 9a(16진수) ->b8(16진수)<br>




