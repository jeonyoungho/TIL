/**
 * 라우팅 모듈을 로딩하여 설정
 * 
 * 라우팅 모듈 파일에 대한 정보는 config.js의 routes 배열에 등록함
 */

var route_loader = {};

var config = require('../config/config');


route_loader.init = function(app, router, upload) {
	console.log('routes.init 호출됨.');
	return initRoutes(app, router, upload);
}

// route_info에 정의된 라우팅 정보 처리
function initRoutes(app, router, upload) {

	var infoLen = config.routes.length;
	console.log('설정에 정의된 라우팅 모듈의 수 : %d', infoLen);
 
	for (var i = 0; i < infoLen; i++) {
		var curItem = config.routes[i];
			
		// 모듈 파일에서 모듈 불러옴
		var curModule = require(curItem.file);
		console.log('%s 파일에서 모듈정보를 읽어옴.', curItem.file);
		
		//  라우팅 처리
		if (curItem.type == 'get') {
            router.route(curItem.path).get(curModule[curItem.method]);
		} else if (curItem.type == 'post') {
            if (curItem.upload) {
                router.route(curItem.path).post(upload.array(curItem.upload, 1), curModule[curItem.method]);
            } else {
                router.route(curItem.path).post(curModule[curItem.method]);
            }
		} else {
			if (curItem.upload) {
                router.route(curItem.path).post(upload.array(curItem.upload, 1), curModule[curItem.method]);
            } else {
                router.route(curItem.path).post(curModule[curItem.method]);
            }
		}
		
		
		console.log('라우팅 모듈 [%s]이(가) 설정됨.', curItem.method);
	}

    // 라우터 객체 등록
    app.use('/', router);
}

module.exports = route_loader;

