/**
 * 미션 16
 * 
 * 미션 10의 기능을 RPC로 바꾸기
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

// 파일 업로드용 미들웨어
var multer = require('multer');

//클라이언트에서 ajax로 요청 시 CORS(다중 서버 접속) 지원
var cors = require('cors');

// mime 모듈
var mime = require('mime');



// 모듈로 분리한 설정 파일 불러오기
var config = require('./config/config');

// 모듈로 분리한 데이터베이스 파일 불러오기
var database = require('./database/database');

// 모듈로 분리한 라우팅 파일 불러오기
var routes = require('./routes/routes');


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
console.log('config.server_port : %d', config.server_port);
app.set('port', config.server_port || process.env.PORT);

// body-parser 설정
app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())
 
// public 폴더를 static으로 오픈
app.use('/public', static(path.join(__dirname, 'public')));
app.use('/uploads', static(path.join(__dirname, 'uploads')));

//클라이언트에서 ajax로 요청 시 CORS(다중 서버 접속) 지원
app.use(cors());


//multer 미들웨어 사용 : 미들웨어 사용 순서 중요  body-parser -> multer -> router
// 파일 제한 : 10개, 1G
var storage = multer.diskStorage({
    destination: function (req, file, callback) {
        callback(null, 'uploads')
    },
    filename: function (req, file, callback) {
        var extension = path.extname(file.originalname);
        var basename = path.basename(file.originalname, extension);
        callback(null, basename + Date.now() + extension);
    }
});

var upload = multer({ 
    storage: storage,
    limits: {
		files: 10,
		fileSize: 1024 * 1024 * 1024
	}
});



//라우팅 정보를 읽어들여 라우팅 설정
routes.init(app, express.Router(), upload);

 

//===== jayson 미들웨어 사용 =====//

//JSON-RPC 핸들러 정보를 읽어들여 핸들러 설정
var jsonrpc_api_path = config.jsonrpc_api_path || '/api';
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



//확인되지 않은 예외 처리 - 서버 프로세스 종료하지 않고 유지함
process.on('uncaughtException', function (err) {
	console.log('uncaughtException 발생함 : ' + err);
	console.log('서버 프로세스 종료하지 않고 유지함.');
	
	console.log(err.stack);
});



// 웹서버 시작
var server = http.createServer(app).listen(app.get('port'), function(){
    console.log('웹 서버 시작됨 -> %s, %s', server.address().address, server.address().port);
    
	// 데이터베이스 초기화
	database.init(app, config);
   
});


