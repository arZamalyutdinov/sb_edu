address = 'http://192.168.43.95:';
port = '8080';
path = '/front';
heliportId = null;
orderID = null;

function getCookie(name) {
    var match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'));
    if (match) return match[2];
    return match ? decodeURIComponent(match[1]) : undefined;
}

function setCookie(name, value, options = {}) {

    options = {
        path: '/',
        // при необходимости добавьте другие значения по умолчанию
        ...options
    };

    if (options.expires.toUTCString) {
        options.expires = options.expires.toUTCString();
    }

    let updatedCookie = encodeURIComponent(name) + "=" + encodeURIComponent(value);

    for (let optionKey in options) {
        updatedCookie += "; " + optionKey;
        let optionValue = options[optionKey];
        if (optionValue !== true) {
            updatedCookie += "=" + optionValue;
        }
    }

    document.cookie = updatedCookie;
}

function f() {
    ord = getCookie('ordId');
    if (ord !== undefined) {
        orderID = ord;
        $('#myForm').removeClass('visible').addClass('invisible');
        setCookie('ordId', orderID, {expires : Date.now() + 86400e3});
        $('#order').removeClass('invisible').addClass('visible');
        updatePosition();
    }
}

function showCopter(id) {
    orderID = $('#exampleInputEmail1').val();
    $('#myForm').removeClass('visible').addClass('invisible');
    // alert(orderID);
    setCookie('ordId', orderID, {expires : Date.now() + 86400e3});
    updatePosition();
}

function afterClick() {
    $("#myForm").removeClass('visible').addClass('invisible');
    $.get({url: address+port+path+'/heliports', success : function (data) {
            console.log('My object: ', data[0].id);
       data.forEach(function (elem) {
       $('#myList').append('<a' + 'id=' + elem.id + ' href=\"#\" class=\"list-group-item list-group-item-action\"' +
           'onclick=\"rememberHelicopterId(this.innerHTML);\">'
           + elem.id +  '</a>');

       });
    }, error: function(xhr, textStatus, error){
            console.log(xhr.statusText);
            console.log('text :' + textStatus);
            console.log(error);}});
    $('#rower').removeClass('invisible').addClass('visible');
}

function rememberHelicopterId(id) {
    let heliportId = id;
    console.log(heliportId);
    $.post({
        url : address+port+path+'/orders',
        data : id,
        success : function(data) {
            console.log(data.id);
            // console.log('My object: ', data[0].id);
            orderID = data.id;
            setCookie('ordId', orderID, {expires : Date.now() + 86400e3});
        }
    , dataType:"json", contentType: "application/json; charset=utf-8"});
    $('#order').removeClass('invisible').addClass('visible');
    $('#rower').removeClass('visible').addClass('invisible');
}

function updatePosition() {
    if (orderID == null) {
        return;
    }
    $.get({url : address + port + path + '/orders',success : function (data) {
        console.log(data.status);
        // data.location = {lat : data.telemetry.location.lat, lon : data.telemetry.location.lon};
        $('#lst1').html('Order ID: ' + orderID);
        $('#lst2').html('Order Status: ' + data.status);
        updateDronePosition(data.location.lat, data.location.lon);
    }, data : {id : orderID} })
}