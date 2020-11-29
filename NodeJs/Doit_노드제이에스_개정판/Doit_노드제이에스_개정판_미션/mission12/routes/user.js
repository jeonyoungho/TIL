/*
 * 사용자 정보 처리 모듈
 * 데이터베이스 관련 객체들을 req.app.get('database')로 참조
 *
 * @date 2016-11-10
 * @author Mike
 */

var modifyUser = function(req, res) {
	console.log('user 모듈 안에 있는 modifyUser 호출됨.');

	var paramEmail = req.body.email || req.query.email;
    var paramName = req.body.name || req.query.name;
	
    console.log('요청 파라미터 : ' + paramEmail + ', ' + paramName);
    
    // 데이터베이스 객체 참조
	var database = req.app.get('database');
	
    // 데이터베이스 객체가 초기화된 경우, updateUser 함수 호출하여 사용자 추가
	if (database.db) {
		updateUser(database, paramEmail, paramName, function(err, updatedUser) {
            // 동일한 email이 없는 경우 에러 발생 - 클라이언트로 에러 전송
			if (err) {
                console.error('프로필 수정 중 에러 발생 : ' + err.stack);
                
                res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
				res.write('<h2>프로필 수정 중 에러 발생</h2>');
                res.write('<p>' + err.stack + '</p>');
				res.end();
                
                return;
            }
			
            // 결과 객체 있으면 성공 응답 전송
			if (updatedUser) {
				console.dir(updatedUser);
 
				res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
				
				// 뷰 템플레이트를 이용하여 렌더링한 후 전송
				var context = {title:'프로필 수정 성공'};
				req.app.render('modifyUser', context, function(err, html) {
					if (err) {throw err;}
					
					console.log("rendered : " + html);
					
					res.end(html);
				});
				
			} else {  // 결과 객체가 없으면 실패 응답 전송
				res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
				res.write('<h2>프로필 수정 실패</h2>');
				res.end();
			}
		});
	} else {  // 데이터베이스 객체가 초기화되지 않은 경우 실패 응답 전송
		res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
		res.write('<h2>데이터베이스 연결 실패</h2>');
		res.end();
	}
	
};
 

// 프로필 수정 함수
var updateUser = function(database, email, name, callback) {
	console.log('updateUser 호출됨.');
	
	// UserModel을 이용해 업데이트
    var conditions = {
        "email":email
    };
    
    var data = {
        $set:{
            "name":name
        }
    }
    
    var options = {
        new:true
    }
	database.UserModel.findOneAndUpdate(conditions, data, options, function(err, result) {
        if (err) {
			callback(err, null);
			return;
		}
		
	    console.log("데이터베이스에서 프로필 수정함.");
	    callback(null, result);
    });
}


module.exports.modifyUser = modifyUser;

