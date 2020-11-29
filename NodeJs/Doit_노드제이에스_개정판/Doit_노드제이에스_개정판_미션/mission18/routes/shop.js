/*
 * Shop 저장, 조회 및 공간 검색을 위한 라우팅 함수 정의
 *
 */

var addShop = function(req, res) {
	console.log('shop 모듈 안에 있는 addShop 호출됨.');
 
    var paramName = req.body.name || req.query.name;
    var paramAddress = req.body.address || req.query.address;
    var paramTel = req.body.tel || req.query.tel;
    var paramShopType = req.body.shopType || req.query.shopType;
    var paramLongitude = req.body.longitude || req.query.longitude;
    var paramLatitude = req.body.latitude || req.query.latitude;
	
    console.log('요청 파라미터 : ' + paramName + ', ' + paramAddress + ', ' +
               paramTel + ', ' + paramShopType + ', ' + paramLongitude + ', ' +
               paramLatitude);
	
    // 데이터베이스 객체 참조
	var database = req.app.get('database');
	
    // 데이터베이스 객체가 초기화된 경우
	if (database.db) {
		insertShop(database, paramName, paramAddress, paramTel, paramShopType, paramLongitude, paramLatitude, function(err, result) {
			if (err) {
                console.error('Shop 추가 중 에러 발생 : ' + err.stack);
                
                res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
				res.write('<h2>Shop 추가 중 에러 발생</h2>');
                res.write('<p>' + err.stack + '</p>');
				res.end();
                
                return;
            }
			
			if (result) {
				console.dir(result);
 
				res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
				res.write('<h2>커피숍 추가 성공</h2>');
				res.end();
			} else {
				res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
				res.write('<h2>커피숍 추가  실패</h2>');
				res.end();
			}
		});
	} else {
		res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
		res.write('<h2>데이터베이스 연결 실패</h2>');
		res.end();
	}
	
};

var listShop = function(req, res) {
	console.log('shop 모듈 안에 있는 listShop 호출됨.');
 
    // 데이터베이스 객체 참조
	var database = req.app.get('database');
	
    // 데이터베이스 객체가 초기화된 경우
	if (database.db) {
		// 1. 모든 커피숍 검색
		database.ShopModel.findAll(function(err, results) {
			if (err) {
                console.error('Shop 리스트 조회 중 에러 발생 : ' + err.stack);
                
                res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
				res.write('<h2>Shop 리스트 조회 중 에러 발생</h2>');
                res.write('<p>' + err.stack + '</p>');
				res.end();
                
                return;
            }
			  
			if (results) {
				console.dir(results);
 
				res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
				res.write('<h2>Shop 리스트</h2>');
				res.write('<div><ul>');
				
				for (var i = 0; i < results.length; i++) {
					var curName = results[i]._doc.name;
					var curAddress = results[i]._doc.address;
					var curTel = results[i]._doc.tel;
                    var curShopType = results[i]._doc.shop_type;
					var curLongitude = results[i]._doc.geometry.coordinates[0];
					var curLatitude = results[i]._doc.geometry.coordinates[1];
					
					res.write('    <li>#' + i + ' : ' + curName + ', ' + curAddress + ', ' + curTel + ', ' + curShopType + ', ' + curLongitude + ', ' + curLatitude + '</li>');
				}	
			
				res.write('</ul></div>');
				res.end();
			} else {
				res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
				res.write('<h2>Shop 리스트 조회  실패</h2>');
				res.end();
			}
		});
	} else {
		res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
		res.write('<h2>데이터베이스 연결 실패</h2>');
		res.end();
	}
	
};


var findCircle = function(req, res) {
	console.log('shop 모듈 안에 있는 findCircle 호출됨.');
  
    var paramShopType = req.body.shopType || req.query.shopType;
    var paramCenterLongitude = req.body.center_longitude || req.query.center_longitude;
    var paramCenterLatitude = req.body.center_latitude || req.query.center_latitude;
    var paramRadius = req.body.radius || req.query.radius;
	
    console.log('요청 파라미터 : ' + paramShopType + ', ' + paramCenterLongitude + ', ' + paramCenterLatitude + ', ' + 
               paramRadius);
    
    // 데이터베이스 객체 참조
	var database = req.app.get('database');
	
    // 데이터베이스 객체가 초기화된 경우
	if (database.db) {
		// 1. 가까운 커피숍 검색
		database.ShopModel.findCircle(paramShopType, paramCenterLongitude, paramCenterLatitude, paramRadius, function(err, results) {
			if (err) {
                console.error('Shop 검색 중 에러 발생 : ' + err.stack);
                
                res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
				res.write('<h2>Shop 검색 중 에러 발생</h2>');
                res.write('<p>' + err.stack + '</p>');
				res.end();
                
                return;
            }
			  
			if (results) {
				console.dir(results);
 
				if (results.length > 0) {
					res.render('circleshop.ejs', {result: results[0]._doc, paramLatitude: paramCenterLatitude, paramLongitude: paramCenterLongitude, paramShopType: paramShopType});
				} else {
					res.writeHead('200', {'Content-Type':'text/html;charset=utf8'});
					res.write('<h2>가까운 커피숍 데이터가 없습니다.</h2>');
					res.end();
				}
			} else {
				res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
				res.write('<h2>반경 내 Shop 조회  실패</h2>');
				res.end();
			}
		});
	} else {
		res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
		res.write('<h2>데이터베이스 연결 실패</h2>');
		res.end();
	}
	
};


// 커피숍을 추가하는 함수
var insertShop = function(database, name, address, tel, shopType, longitude, latitude, callback) {
	console.log('insertShop 호출됨.');
	
	// ShopModel 인스턴스 생성
	var shop = new database.ShopModel(
			{name:name, address:address, tel:tel, shop_type:shopType, 
			    geometry: {
				   type: 'Point',
				   coordinates: [longitude, latitude]
			    }
			}
		);

	// save()로 저장
	shop.save(function(err) {
		if (err) {
			callback(err, null);
			return;
		}
		
	    console.log("Shop 데이터 추가함.");
	    callback(null, shop);
	     
	});
}


module.exports.addShop = addShop;
module.exports.listShop = listShop;
module.exports.findCircle = findCircle;

