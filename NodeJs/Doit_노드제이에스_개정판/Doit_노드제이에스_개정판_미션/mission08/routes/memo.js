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

                    res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
                    res.write('<h2>메모 저장 중 에러 발생</h2>');
                    res.write('<p>' + err.stack + '</p>');
                    res.end();

                    return;
                }

                // 결과 객체 있으면 성공 응답 전송
                if (added) {
                    console.dir(added);

                    console.log('inserted ' + added.affectedRows + ' rows');

                    var insertId = added.insertId;
                    console.log('추가한 레코드의 아이디 : ' + insertId);

                    res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});

                    res.write('<html>');
                    res.write('  <head>');
                    res.write('    <meta charset="UTF-8">');
                    res.write('    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1">');
                    res.write('    <title>메모 페이지</title>');

                    res.write('    <!-- 부트스트랩 CSS -->');
                    res.write('    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">');

                    res.write('    <!-- 부트스트랩 테마 CSS -->');
                    res.write('    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">');

                    res.write('    <!-- 제이쿼리 JS -->');
                    res.write('    <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>');

                    res.write('    <!-- 부트스트랩 JS -->');
                    res.write('    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>');


                    res.write('    </head>');
                    res.write('    <body>');

                    res.write('<div class="container">');
                    res.write('  <div class="form-group">');
                    res.write('    <label>메모가 저장되었습니다.</label>');
                    res.write('    <a href="#" class="thumbnail"><img src="/uploads/' + filename + '" width="200px"></a>');
                    res.write('    <br>');
                    res.write('    <input type="button" value="다시 작성" onclick="javascript:history.back()" class="btn btn-primary">');
                    res.write('  </div>');


                    res.write('    </div>');    
                    res.write('  </body>');
                    res.write('</html>');

                    res.end();
                } else {
                    res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
                    res.write('<h2>메모 저장 실패</h2>');
                    res.end();
                }
            });

        }
        
	} catch(err) {
		console.dir(err.stack);
		
		res.writeHead(400, {'Content-Type':'text/html;charset=utf8'});
		res.write('<div><p>메모 저장 시 에러 발생</p></div>');
		res.end();
	}	
		
};




// module.exports에 속성으로 추가
module.exports.saveMemo = saveMemo;


