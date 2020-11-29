console.log('argv 속성의 파림터 수: ' + process.argv.length);
console.log(process.argv);

if(process.argv.length > 2){
    console.log('세번째 파라미터의 값: %s', process.argv[2]);
}

process.argv.forEach(function(item, index){
    console.log(index + ': ', item);
});