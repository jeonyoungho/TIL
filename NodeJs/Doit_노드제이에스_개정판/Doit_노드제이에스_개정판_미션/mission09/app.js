/**
 * 미션 9
 * 
 * 미션 7에 ejs 뷰 템플릿 사용하기
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

                res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
                
                var context = {
                    contents:'메모 저장 중 에러가 발생했습니다.',
                    button_value:'다시 작성',
                    button_action:'javascript:history.back()'
                }
                sendResult(context, res);

                return;
            }

            // 결과 객체 있으면 성공 응답 전송
            if (addedMemo) {
                console.dir(addedMemo);

                console.log('inserted ' + addedMemo.affectedRows + ' rows');

                var insertId = addedMemo.insertId;
                console.log('추가한 레코드의 아이디 : ' + insertId);

                res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
                
                var context = {
                    contents:'메모가 저장되었습니다.',
                    button_value:'다시 작성',
                    button_action:'javascript:history.back()'
                }
                sendResult(context, res);
                
            } else {
                res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
                
                var context = {
                    contents:'메모 저장에 실패했습니다.',
                    button_value:'다시 작성',
                    button_action:'javascript:history.back()'
                }
                sendResult(context, res);
            }
        });
          
	} catch(err) {
		console.dir(err.stack);
		
		res.writeHead(400, {'Content-Type':'text/html;charset=utf8'});
        
		var context = {
            contents:'메모 저장 중 에러가 발생했습니다.',
            button_value:'다시 작성',
            button_action:'javascript:history.back()'
        }
        sendResult(context, res);
	}	
		
});

function sendResult(context, res) {
    app.render('memo_result', context, function(err, html) {
        if (err) {
            console.error('뷰 렌더링 중 에러 발생 : ' + err.stack);

            res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
            res.write('<h2>뷰 렌더링 중 에러 발생</h2>');
            res.write('<p>' + err.stack + '</p>');
            res.end();

            return;
        }
        console.log('rendered : ' + html);

        res.end(html);
    });
}


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


