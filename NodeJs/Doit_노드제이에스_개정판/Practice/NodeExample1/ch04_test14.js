var fs = require('fs');

fs.mkdir('./docs', 0666, function(err) {
    if(err) throw err;
    console.log('새로운 docs폴더를 만들었습니다.');

    fs.rmdir('./docs', function(err) {
        if(err) throw err;
        console.log('docs폴더를 삭제했습니다.');
    });
});