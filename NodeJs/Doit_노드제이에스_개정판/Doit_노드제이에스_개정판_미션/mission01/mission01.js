/**
 * 미션 1
 * 
 * 파일의 내용을 한 줄씩 읽어들여 출력하는 기능
 
 * 외장모듈 : fs, readline
 * 읽어들일 파일 : customer.txt
 */


// fs 모듈 사용
var fs = require('fs');
var readline = require('readline');

// 한 줄씩 읽어들이는 함수 정의
function processFile(filename) {
    var instream = fs.createReadStream(filename);
    var reader = readline.createInterface(instream, process.stdout);
     
    var count = 0;
    
    // 한 줄씩 읽어들인 후에 발생하는 이벤트
    reader.on('line', function(line) {
        console.log('한 줄 읽음 : ' + line);
        
        count += 1;
        
        // 공백으로 구분
        var tokens = line.split(' ');
        
        if (tokens != undefined && tokens.length > 0) {
            console.log('#' + count + ' -> ' + tokens[0]);
        }
    });
    
    // 모두 읽어들였을 때 발생하는 이벤트
    reader.on('close', function(line) {
        console.log('파일을 모두 읽음.');
    });
}

// 함수 실행
var filename = './customer.txt';
processFile(filename);