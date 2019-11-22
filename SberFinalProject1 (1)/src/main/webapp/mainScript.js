createAdminMap();
address  = 'http://192.168.43.95:';
path = '/back';
port = '8080';


function addCopters() {
    fnc = function() {
        $.get({ timeout : 8000,
            cache: false, url: address + port + path + '/copters', success: function (data) {
                console.log('Success!');
                updateCopters(data);
            },
            error: function(xhr, textStatus, error){
                console.log(xhr.statusText);
                console.log('textstatus from addCopters :' + textStatus);
                console.log(error);} });
    };
    $.when(fnc()).done(function () {clearTimeout(timeOutHandle);
    timeOutHandle = setTimeout(addCopters, 1000);});
    // clearTimeout();
    // // let val = await Promise.resolve(1);

}

// $(function () {
//     setInterval(addCopters, 1000);
// });
addCopters();
//
//
// let timerId = setTimeout(function tick() {
//     addCopters();
//     clearTimeout(timerId);
//     timerId = setTimeout(tick, 1000); // (*)
// }, 1000);

// var gtimer;
// $(document).ready(function () {
//     clearInterval(gtimer);
//     gtimer = window.setInterval(function () {
//         clearInterval(gtimer);
//         addCopters();
//     }, 1000);
// });
// window.setInterval(addCopters, 2000);