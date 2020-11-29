/*
 * 메모 테이블 
 */

var memo = {};

var sql = {
    insertMemo:'insert into memo set ?'
}

// 메모 추가 함수
var insertMemo = function(pool, data, callback) {
	console.log('insertMemo 호출됨.');
    console.dir(data);
	
	pool.execute(pool, sql.insertMemo, data, callback);
};

memo.insertMemo = insertMemo;


module.exports = memo;
