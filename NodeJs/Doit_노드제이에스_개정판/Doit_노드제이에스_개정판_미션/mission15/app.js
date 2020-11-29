/**
 * 미션 15
 * 
 * 미션 9에서 JSON-RPC 적용하기
 * 
 * 1. 웹서버 실행 : 명령프롬프트에서 node app.js 실행
 * 2. 웹페이지 열기 : 웹브라우저에서 http://localhost:3000/public/memo.html 열기
 */

// Express 기본 모듈 불러오기
var express = require('express');
var http = require('http');
var path = require('path');

// Express의 미들웨어 불러오기
var bodyParser = require('body-parser');
var static = require('serve-static');

// 에러 핸들러 모듈 사용
var expressErrorHandler = require('express-error-handler');

// 파일 처리
var fs = require('fs');

//===== MySQL 데이터베이스를 사용할 수 있도록 하는 mysql 모듈 불러오기 =====//
var mysql = require('mysql');

//===== MySQL 데이터베이스 연결 설정 =====//
var pool      =    mysql.createPool({
    connectionLimit : 10, 
    host     : 'localhost',
    user     : 'root',
    password : 'admin',
    database : 'test',
    debug    :  false
});


// JsonRpc 핸들러 로딩을 위한 파일 불러오기
var handler_loader = require('./handlers/handler_loader');

// JsonRpc 사용을 위한 jayson 모듈 불러오기
var jayson = require('jayson');



// 익스프레스 객체 생성
var app = express();

//===== 뷰 엔진 설정 =====//
app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');
console.log('뷰 엔진이 ejs로 설정되었습니다.');


// 포트 설정
app.set('port', process.env.PORT || 3000);

// body-parser 설정
app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())
 
// public 폴더를 static으로 오픈
app.use('/public', static(path.join(__dirname, 'public')));


// 라우터 사용하여 라우팅 함수 등록
var router = express.Router();


app.use('/', router);

 

//===== jayson 미들웨어 사용 =====//

//JSON-RPC 핸들러 정보를 읽어들여 핸들러 설정
var jsonrpc_api_path = '/api';
handler_loader.init(jayson, app, jsonrpc_api_path);

console.log('JSON-RPC를 [' + jsonrpc_api_path + '] 패스에서 사용하도록 설정함.');





// 404 에러 페이지 처리
var errorHandler = expressErrorHandler({
    static: {
      '404': './public/404.html'
    }
});

app.use( expressErrorHandler.httpError(404) );
app.use( errorHandler );


// 웹서버 시작
var server = http.createServer(app).listen(app.get('port'), function(){
  console.log('웹 서버 시작됨 -> %s, %s', server.address().address, server.address().port);
});


