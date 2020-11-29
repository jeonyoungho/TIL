var url = require('url');

// 주소 문자열을 URL 객체로 만들기
var curURL = url.parse('https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=steve+jobs');

var curStr = url.format(curURL);

console.log('주소 문자열: ' + curStr);
console.log(curURL);

var querystring = require('querystring');
var param = querystring.parse(curURL.query);

console.log('요청 파라미터 중 query의 값: %s', param.query);
console.log('요청 파라미터 중 ie의 값: %s', param.ie);
console.log('원본 요청 파라미터: %s', querystring.stringify(param));