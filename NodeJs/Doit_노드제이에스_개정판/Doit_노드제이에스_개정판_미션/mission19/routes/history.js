/*
 * 푸시 이력 관리 기능을 위한 라우팅 함수 정의
 *
 */

var config = require('../config/config.js');


var listHistory = function(req, res) {
	console.log('history 모듈 안에 있는 listHistory 호출됨.');

	var database = req.app.get('database');
	
	// 데이터베이스 객체가 초기화된 경우
	if (database.db) {
        
		// 1. 모든 단말 검색
		database.HistoryModel.findAll(function(err, results) {
			if (err) {
				console.error('푸시 이력 리스트 조회 중 에러 발생 : ' + err.stack);
                
                res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
				res.write('<h2>푸시 이력 리스트 조회 중 에러 발생</h2>');
                res.write('<p>' + err.stack + '</p>');
				res.end();
                
                return;
			}
			  
			if (results) {
				console.dir(results);

				var context = {
					title: '푸시 이력',
					history: results
				};
				
				req.app.render('listhistory', context, function(err, html) {
                    if (err) {
                        console.log('listhistory.ejs 뷰 렌더링 시 에러 발생');
                        console.dir(err);
                        return;
                    }
                    
					res.end(html);
				});
				
			} else {
				res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
				res.write('<h2>푸시 이력 리스트 조회  실패</h2>');
				res.end();
			}
		});
	} else {
		res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
		res.write('<h2>데이터베이스 연결  실패</h2>');
		res.end();
	}
	
};



module.exports.listHistory = listHistory;
