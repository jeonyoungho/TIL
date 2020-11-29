/**
 * 통화 상태 스키마
 * 
 */

var Schema = {};

Schema.createSchema = function(mongoose) {
	
	// 스키마 정의
	var StatusSchema = mongoose.Schema({
		mobile: {type: String, index: {unique: true}, 'default':''}
	    , direction: {type: String, 'default':''}
	    , state: {type: String, 'default':''}
	    , created_at: {type: Date, index: {unique: false}, 'default': Date.now}
	});
	
	// 입력된 칼럼의 값이 있는지 확인
	StatusSchema.path('mobile').validate(function (mobile) {
		return mobile.length;
	}, 'mobile 칼럼의 값이 없습니다.');
	
	// 스키마에 static 메소드 추가
	StatusSchema.static('findByMobile', function(mobile, callback) {
		return this.find({mobile:mobile}, callback);
	});
	
	StatusSchema.static('findAll', function(callback) {
		return this.find({}, callback);
	});
	
	StatusSchema.static('load', function(options, callback) {
		options.select = options.select || 'mobile';
	    this.findOne(options.criteria)
	      .select(options.select)
	      .exec(callback);
	});
	
	
	// 모델을 위한 스키마 등록
	mongoose.model('Status', StatusSchema);
	
	console.log('StatusSchema 정의함.');

	return StatusSchema;
};

// module.exports에 Schema 객체 직접 할당
module.exports = Schema;

