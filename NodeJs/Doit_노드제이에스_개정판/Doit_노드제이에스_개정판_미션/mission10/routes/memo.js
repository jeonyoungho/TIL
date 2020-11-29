/*
 * 메모 처리 모듈
 * 데이터베이스 관련 객체들을 req.app.get('database')로 참조
 */

var saveMemo = function(req, res) {
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
		
        
        // 데이터베이스 객체 참조
        var database = req.app.get('database');

        // 데이터베이스의 pool 객체가 있는 경우
        if (database.pool) {

            // memo의 insertMemo 함수 호출하여 메모 추가
            var data = {
                author:paramAuthor,
                contents:paramContents,
                createDate:paramCreateDate,
                filename:filename
            };
            
            console.dir(database.memo);
            
            database.memo.insertMemo(database.pool, data, function(err, added) {
                // 에러 발생 시 - 클라이언트로 에러 전송
                if (err) {
                    console.error('메모 저장 중 에러 발생 : ' + err.stack);

                    res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
                    
                    var context = {
                        contents:'메모 저장 중 에러가 발생했습니다.',
                        button_value:'다시 작성',
                        button_action:'javascript:history.back()'
                    }
                    sendResult(context, req, res);

                    return;
                }

                // 결과 객체 있으면 성공 응답 전송
                if (added) {
                    console.dir(added);

                    console.log('inserted ' + added.affectedRows + ' rows');

                    var insertId = added.insertId;
                    console.log('추가한 레코드의 아이디 : ' + insertId);

                    res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});

                    var context = {
                        contents:'메모가 저장되었습니다.',
                        button_value:'다시 작성',
                        button_action:'javascript:history.back()',
                        filename:'/uploads/' + filename
                    }
                    sendResult(context, req, res);
                    
                } else {
                    res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
                    
                    var context = {
                        contents:'메모 저장에 실패했습니다.',
                        button_value:'다시 작성',
                        button_action:'javascript:history.back()'
                    }
                    sendResult(context, req, res);
                }
            });

        }
        
	} catch(err) {
		console.dir(err.stack);
		
		res.writeHead(400, {'Content-Type':'text/html;charset=utf8'});
		
        var context = {
            contents:'메모 저장 시 에러가 발생했습니다.',
            button_value:'다시 작성',
            button_action:'javascript:history.back()'
        }
        sendResult(context, req, res);
	}	
		
};


function sendResult(context, req, res) {
    req.app.render('memo_result', context, function(err, html) {
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



// module.exports에 속성으로 추가
module.exports.saveMemo = saveMemo;


