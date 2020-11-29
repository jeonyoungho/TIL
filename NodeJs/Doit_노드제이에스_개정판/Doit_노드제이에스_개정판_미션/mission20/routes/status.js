/*
 * 통화상태를 위한 라우팅 함수 정의
 *
 */

var config = require('../config/config.js');


var addStatus = function(req, res) {
	console.log('status 모듈 안에 있는 addStatus 호출됨.');
	
	var database = req.app.get('database');
	 
    var paramMobile = req.body.mobile || req.query.mobile;
    var paramDirection = req.body.direction || req.query.direction;
    var paramState = req.body.state || req.query.state;
     
    console.log('요청 파라미터 : ' + paramMobile + ', ' + paramDirection + ', ' + 
               paramState);
    
	// 데이터베이스 객체가 초기화된 경우
	if (database.db) {
		
		// StatusModel 인스턴스 생성
		var status = new database.StatusModel({"mobile":paramMobile, "direction":paramDirection, "state":paramState});

		// save()로 저장
		status.save(function(err) {
			if (err) {
                console.error('통화상태 정보 추가 중 에러 발생 : ' + err.stack);
                
                res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
				res.write('<h2>통화상태 정보 추가 중 에러 발생</h2>');
                res.write('<p>' + err.stack + '</p>');
				res.end();
                
                return;
            }
			
		    console.log("통화상태 정보 추가함.");
		    
		    console.dir(status);
		    
			res.writeHead('200', {'Content-Type':'application/json;charset=utf8'});
			res.write("{code:'200', 'message':'통화상태 정보 추가 성공'}");
			res.end();
		     
		});
		
	} else {
		res.writeHead('200', {'Content-Type':'application/json;charset=utf8'});
		res.write("{code:'400', 'message':'데이터베이스 연결 실패'}");
		res.end();
	}
	
};


var updateStatus = function(req, res) {
	console.log('status 모듈 안에 있는 updateStatus 호출됨.');
	
	var database = req.app.get('database');
	 
    var paramMobile = req.body.mobile || req.query.mobile;
    var paramDirection = req.body.direction || req.query.direction;
    var paramState = req.body.state || req.query.state;
     
    console.log('요청 파라미터 : ' + paramMobile + ', ' + paramDirection + ', ' + 
               paramState);
    
	// 데이터베이스 객체가 초기화된 경우
	if (database.db) {
        // 1. 모든 사용자 검색
		database.StatusModel.findByMobile(
            paramMobile,
            function(err, results) {
                // 에러 발생 시, 클라이언트로 에러 전송
                if (err) {
                    console.error('통화상태 업데이트 중 에러 발생 : ' + err.stack);

                    res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
                    res.write('<h2>통화상태 업데이트 중 에러 발생</h2>');
                    res.write('<p>' + err.stack + '</p>');
                    res.end();

                    return;
                }

			    if (results && results.length > 0) {
                    var data = {direction:paramDirection, state:paramState};
				    database.StatusModel.update(results[0], data, function(err, updated) {
                        if (err) {
                            console.log('에러 발생');
                            
                            res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
                            res.write('<h2>통화상태 업데이트 실패</h2>');
                            res.end();
                            return;
                        }
                        
                        res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
                        res.write('<h2>통화상태 업데이트 성공</h2>');
                        res.end();
                    });
 
                    
			    } else {
                    res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
                    res.write('<h2>통화상태 업데이트 실패</h2>');
                    res.end();
			    }
		  });
	} else {
		res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
		res.write('<h2>데이터베이스 연결 실패</h2>');
		res.end();
	}

}


var listStatus = function(req, res) {
	console.log('status 모듈 안에 있는 listStatus 호출됨.');

	var database = req.app.get('database');
	
	// 데이터베이스 객체가 초기화된 경우
	if (database.db) {
        
		// 1. 모든 단말 검색
		database.StatusModel.findAll(function(err, results) {
			if (err) {
				console.error('통화 상태 리스트 조회 중 에러 발생 : ' + err.stack);
                
                res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
				res.write('<h2>통화 상태 리스트 조회 중 에러 발생</h2>');
                res.write('<p>' + err.stack + '</p>');
				res.end();
                
                return;
			}
			  
			if (results) {
				console.dir(results);

				var context = {
					title: '통화 상태',
					status: results
				};
				
				req.app.render('liststatus', context, function(err, html) {
                    if (err) {
                        console.log('liststatus.ejs 뷰 렌더링 시 에러 발생');
                        console.dir(err);
                        return;
                    }
                    
					res.end(html);
				});
				
			} else {
				res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
				res.write('<h2>통화 상태 리스트 조회  실패</h2>');
				res.end();
			}
		});
	} else {
		res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
		res.write('<h2>데이터베이스 연결  실패</h2>');
		res.end();
	}
	
};


module.exports.addStatus = addStatus;
module.exports.updateStatus = updateStatus;
module.exports.listStatus = listStatus;
