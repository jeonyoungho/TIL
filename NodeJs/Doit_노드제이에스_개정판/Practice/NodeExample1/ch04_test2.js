console.log('start');

process.on('tick', function(){
    console.log('exit 이벤트 발생함.');
});

setTimeout(function(){
    console.log('2초 후에 시스템 종료 시도함.');

    process.exit();
}, 2000);

console.log('end');