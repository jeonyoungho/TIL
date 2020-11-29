var fs = require('fs');

// 파일을 동기식 IO로 읽어 들입니다.
var data = fs.readFile('./package.json','utf8', function(err, data){
    // 읽어 들인 데이터를 출력합니다.
    console.log('err: ' + err + ", data: " + data);
    if(err == null) {
        console.log('성공');
    }
});

// 읽어들인 데이터 출력
console.log(data);
console.log('end');