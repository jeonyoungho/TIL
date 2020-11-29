var fs = require('fs');

// 파일을 동기식 IO로 읽어 들입니다.
var data = fs.readFileSync('./package.json','utf8');

// 읽어들인 데이터 출력
console.log(data);