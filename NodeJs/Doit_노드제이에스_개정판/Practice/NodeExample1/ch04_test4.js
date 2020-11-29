var Calc = require('./calc3.js');

var calc = new Calc();
calc.emit('stop');

console.log(Calc.title +  '에 stop 이벤트 전달함.');