/**
 * 푸시 전송 기록 스키마
 * 
 */

var Schema = {};

Schema.createSchema = function(mongoose) {
	
	// 스키마 정의
	var HistorySchema = mongoose.Schema({
		mobile: {type: String, 'default':''}
	    , contents: {type: String, 'default':''}
	    , created_at: {type: Date, index: {unique: false}, 'default': Date.now}
	    , updated_at: {type: Date, index: {unique: false}, 'default': Date.now} 
	});
	
	// 입력된 칼럼의 값이 있는지 확인
	HistorySchema.path('mobile').validate(function (mobile) {
		return mobile.length;
	}, 'mobile 칼럼의 값이 없습니다.');
	
	// 스키마에 static 메소드 추가
	HistorySchema.static('findByMobile', function(mobile, callback) {
		return this.find({mobile:mobile}, callback);
	});
	
	HistorySchema.static('findAll', function(callback) {
		return this.find({}, callback);
	});
	 
	// 모델을 위한 스키마 등록
	mongoose.model('History', HistorySchema);
	
	console.log('HistorySchema 정의함.');

	return HistorySchema;
};

// module.exports에 Schema 객체 직접 할당
module.exports = Schema;

