/**
 * 미션 6
 * 
 * 미션 4에 데이터베이스 기능 붙이기
 *
 * MySQL 사용 : memo_table.sql 파일로 테이블 생성 가능
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


// 익스프레스 객체 생성
var app = express();

// 포트 설정
app.set('port', process.env.PORT || 3000);

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


// 라우터 사용하여 라우팅 함수 등록
var router = express.Router();

// 메모 저장을 위한 라우팅 함수
router.route('/process/save').post(upload.array('photo', 1), function(req, res) {
	console.log('/process/save 호출됨.');
	
	try {
		var paramAuthor = req.body.author;
        var paramContents = req.body.contents;
		var paramCreateDate = req.body.createDate;
		
		console.log('작성자 : ' + paramAuthor);
		console.log('내용 : ' + paramContents);
		console.log('일시 : ' + paramCreateDate);
 
        var files = req.files;
	
        console.dir('#===== 업로드된 첫번째 파일 정보 =====#')
        console.dir(req.files[0]);
        console.dir('#=====#')
        
		// 현재의 파일 정보를 저장할 변수 선언
		var originalname = '',
			filename = '',
			mimetype = '',
			size = 0;
		
		if (Array.isArray(files)) {   // 배열에 들어가 있는 경우 (설정에서 1개의 파일도 배열에 넣게 했음)
	        console.log("배열에 들어있는 파일 갯수 : %d", files.length);
	        
	        for (var index = 0; index < files.length; index++) {
	        	originalname = files[index].originalname;
	        	filename = files[index].filename;
	        	mimetype = files[index].mimetype;
	        	size = files[index].size;
	        }

            console.log('현재 파일 정보 : ' + originalname + ', ' + filename + ', ' + mimetype + ', ' + size);

	    } else {
            console.log('업로드된 파일이 배열에 들어가 있지 않습니다.');
	    }
		
        
        
        // insertMemo 함수 호출하여 메모 추가
        insertMemo(paramAuthor, paramContents, paramCreateDate, filename, function(err, addedMemo) {
            // 에러 발생 시 - 클라이언트로 에러 전송
            if (err) {
                console.error('메모 저장 중 에러 발생 : ' + err.stack);

                res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
                res.write('<h2>메모 저장 중 에러 발생</h2>');
                res.write('<p>' + err.stack + '</p>');
                res.end();

                return;
            }

            // 결과 객체 있으면 성공 응답 전송
            if (addedMemo) {
                console.dir(addedMemo);

                console.log('inserted ' + addedMemo.affectedRows + ' rows');

                var insertId = addedMemo.insertId;
                console.log('추가한 레코드의 아이디 : ' + insertId);

                res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
                res.write('<div><p>메모가 저장되었습니다.</p></div>');
                res.write('<img src="/uploads/' + filename + '" width="200px">');
                res.write('<div><input type="button" value="다시 작성" onclick="javascript:history.back()"></div>');
                res.end();
            } else {
                res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
                res.write('<h2>메모 저장 실패</h2>');
                res.end();
            }
        });
          
        
	} catch(err) {
		console.dir(err.stack);
		
		res.writeHead(400, {'Content-Type':'text/html;charset=utf8'});
		res.write('<div><p>메모 저장 시 에러 발생</p></div>');
		res.end();
	}	
		
});


app.use('/', router);



// 메모 추가 함수
var insertMemo = function(author, contents, createDate, filename, callback) {
	console.log('insertMemo 호출됨 : ' + author + ', ' + contents + ', ' + createDate + ', ' + filename);
	
	// 커넥션 풀에서 연결 객체를 가져옴
	pool.getConnection(function(err, conn) {
        if (err) {
        	if (conn) {
                conn.release();  // 반드시 해제해야 함
            }
            
            callback(err, null);
            return;
        }   
        console.log('데이터베이스 연결 스레드 아이디 : ' + conn.threadId);

    	// 데이터를 객체로 만듦
    	var data = {author:author, contents:contents, createDate:createDate, filename:filename};
    	
        // SQL 문을 실행함
        var exec = conn.query('insert into memo set ?', data, function(err, result) {
        	conn.release();  // 반드시 해제해야 함
        	console.log('실행 대상 SQL : ' + exec.sql);
        	
        	if (err) {
        		console.log('SQL 실행 시 에러 발생함.');
        		console.dir(err);
        		
        		callback(err, null);
        		
        		return;
        	}
        	
        	callback(null, result);
        	
        });
        
        conn.on('error', function(err) {      
              console.log('데이터베이스 연결 시 에러 발생함.');
              console.dir(err);
              
              callback(err, null);
        });
    });
	
}


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


