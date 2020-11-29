/**
 * 미션 7
 * 
 * 미션 5에 데이터베이스 기능 붙이기
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


// 라우터 사용하여 라우팅 함수 등록
var router = express.Router();

// 메모 저장을 위한 라우팅 함수
router.route('/process/save').post(function(req, res) {
	console.log('/process/save 호출됨.');
	
	try {
		var paramAuthor = req.body.author;
        var paramContents = req.body.contents;
		var paramCreateDate = req.body.createDate;
		
		console.log('작성자 : ' + paramAuthor);
		console.log('내용 : ' + paramContents);
		console.log('일시 : ' + paramCreateDate);


        // insertMemo 함수 호출하여 메모 추가
        insertMemo(paramAuthor, paramContents, paramCreateDate, function(err, addedMemo) {
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
                
                res.write('<!DOCTYPE html>');
                res.write('<html>');
	            res.write('  <head>');
		        res.write('    <meta charset="UTF-8">');
                res.write('    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1">');
		        res.write('    <title>메모 페이지</title>');
		        res.write('    <link href="/public/semantic/semantic.min.css" rel="stylesheet" >');
                res.write('    <script');
                res.write('      src="https://code.jquery.com/jquery-3.2.1.min.js"');
                res.write('      integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="');
                res.write('      crossorigin="anonymous"></script>');
                res.write('      ');
                res.write('    <script src="/public/semantic/semantic.min.js"></script>');
                res.write('  </head>');
                res.write('  <body>');
         
                res.write('<div id="cardbox" class="ui blue fluid card">');
				res.write('  <div class="content">');
				res.write('    <div id="titleText" class="header">처리 결과</div>');
	   			res.write('    <div id="contentsText" class="description">');
				res.write('      메모가 저장되었습니다.');
				res.write('    </div>');
                res.write('    <div><input type="button" value="다시 작성" onclick="javascript:history.back()" class="ui primary button"></div>');       
				res.write('  </div>');
                res.write('</div>');
                 
                res.write('  </body>');
                res.write('</html>');
                 
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
var insertMemo = function(author, contents, createDate, callback) {
	console.log('insertMemo 호출됨 : ' + author + ', ' + contents + ', ' + createDate);
	
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
    	var data = {author:author, contents:contents, createDate:createDate};
    	
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


