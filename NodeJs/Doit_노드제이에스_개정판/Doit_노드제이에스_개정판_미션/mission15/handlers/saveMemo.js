
/*
 * 메모 RPC 함수
 * 
 */

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


// 메모 저장 함수
var saveMemo = function(params, callback) {
	console.log('JSON-RPC saveMemo 호출됨.');
	console.dir(params);
	
    
	try {
		var paramAuthor = params[0];
        var paramContents = params[1];
		var paramCreateDate = params[2];
		
		console.log('작성자 : ' + paramAuthor);
		console.log('내용 : ' + paramContents);
		console.log('일시 : ' + paramCreateDate);


        // insertMemo 함수 호출하여 메모 추가
        insertMemo(paramAuthor, paramContents, paramCreateDate, function(err, addedMemo) {
            // 에러 발생 시 - 클라이언트로 에러 전송
            if (err) {
                console.error('메모 저장 중 에러 발생 : ' + err.stack);

                callback({
		            code: 410,
		            message: err.message
		        }, null);
                 
                return;
            }

            // 결과 객체 있으면 성공 응답 전송
            if (addedMemo) {
                console.dir(addedMemo);

                console.log('inserted ' + addedMemo.affectedRows + ' rows');

                var insertId = addedMemo.insertId;
                console.log('추가한 레코드의 아이디 : ' + insertId);
 
                var output = {
                    code: 200,
		            message: 'OK',
                    contents:'메모가 저장되었습니다.'
                }
                callback(null, output);
                
            } else {
                callback({
		            code: 410,
		            message: '메모 저장에 실패했습니다.'
		        }, null);
            }
        });
          
	} catch(err) {
		console.dir(err.stack);
		
		callback({
            code: 410,
            message: '메모 저장 중 에러가 발생했습니다.'
        }, null);
	}	
		
};


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



module.exports = saveMemo;

