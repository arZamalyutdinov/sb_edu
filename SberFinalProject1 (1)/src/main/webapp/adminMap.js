
address = 'http://192.168.43.95:';
port = '8080';
path='/back';
var home = {
    lat : 55.741146,
    lon : 37.531220
};

var coptersMap = new Map(); // keeps references to drones placeMark objects
var selectedCopter; // keeps a reference to the selected drone's placeMark. I need it to ensure having only one selected copter
var myMap; // the very map itself



// should call it once when loading the page
function createAdminMap(){
    ymaps.ready(function () {
            myMap = new ymaps.Map('map', {
            center: [home.lat, home.lon],
            zoom: 14
        }, {
            searchControlProvider: 'yandex#search'
        });

        // Создаём макет содержимого.
        var MyIconContentLayout = ymaps.templateLayoutFactory.createClass(
            '<div style="color: #FFFFFF; font-weight: bold;">$[properties.iconContent]</div>'
        );
    });
}




// creates a placeMark for a drone , adds it the map and saves the ref to it into a dictionary
function addCopterObject(copter){
  //  console.log("trying to add copter: " + copter.id);
    var location = copter.telemetry.location;
    // alert(location);
    ymaps.ready(function () {
        var newPlaceMark = new ymaps.Placemark([location.lat, location.lon], {
            hintContent: copter.id, // save the id here to be able to get it once placeMark is selected
            balloonContent: copter.id
        }, {
            // Опции.
            // Необходимо указать данный тип макета.
            iconLayout: 'default#image',
            // Своё изображение иконки метки.
            iconImageHref: 'images/drone.png',
            // Размеры метки.
            iconImageSize: [60, 60],
            // Смещение левого верхнего угла иконки относительно
            // её "ножки" (точки привязки).
            iconImageOffset: [-30, -30],
            hideIconOnBalloonOpen: false,
            openBalloonOnClick: false
        });
        var newCopter = {
            placeMark :  newPlaceMark,
            updated : true
        };
        coptersMap.set(copter.id, newCopter);
        newPlaceMark.events.add('click', clickOnPlaceMark);
        myMap.geoObjects.add(newPlaceMark);
    });
}

function updateCopterInfo(curId) {
    console.log('BeforeUpdate');
    let test;
    $.get({cache: false, url : address+port+path+'/copter',data : {id : curId}, success :  function (data) {
            test = data;
            $('#lat').html(test.telemetry.location.lat);
            $('#battery').html(test.telemetry.battery);
            $('#vel').html(test.telemetry.speed);
            $('#lon').html(test.telemetry.location.lon);
            $('#coptId').html(test.id);
            console.log('asked for telemetry');

        }, error: function(xhr, textStatus, error){
            console.log(xhr.statusText);
            console.log('textstatus :' + textStatus);
            console.log(error);
        }, timeout: 8000}).done(function () {document.cookie.split(";").forEach(function(c) { document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/"); });
    timeOutHandle = setTimeout(addCopters, 1000);
        });



    // $(function () {
    //     setInterval(addCopters, 1000);
    // });
}

let timeOutHandle;

function clickOnPlaceMark(e) {
    clearTimeout(timeOutHandle);
    var placeMark = e.get("target");
    console.log(e);
    if(selectedCopter!=null && placeMark===selectedCopter) return;
    placeMark.options.set("iconImageHref", 'images/selectedDrone.png');
    //call function to show telemetry
    if(selectedCopter!=null){
        selectedCopter.options.set("iconImageHref", 'images/drone.png');
    }
    selectedCopter = placeMark;
     // get selected drone id saved in a placeMark
    // alert(selectedCopterId);
    console.log(selectedCopter.id);
    // showTelemetry(selectedCopterId);
    var curId = placeMark.properties._data.hintContent;
    updateCopterInfo(curId);
    console.log('afterUpdate');
}

// update copter location on the map or add it to the map
function update(copter){
  var location = copter.telemetry.location;
  if(coptersMap.has(copter.id)) {
     // console.log("has copter: " + copter.id);
      var copterObj = coptersMap.get(copter.id);
      copterObj.placeMark.geometry.setCoordinates([location.lat, location.lon]);
      copterObj.updated = true;
  }
  else{
      addCopterObject(copter);
  }
}


function deleteIfNotUpdated() {
   // console.log("delete or not");

    coptersMap.forEach(function (value, key, map) {
      //  console.log('Testing ' + key + "having updated = " + value.updated);
        if(value.updated===false){
          //  console.log("deleting copter: " + key);
            map.delete(key);                                // delete object if there was info about it in the last update
            myMap.geoObjects.remove(value.placeMark);
        }
    });



}


function updateCopters(copters){
    coptersMap.forEach(function (value, key, map) {
      //  console.log("reseting flag for " + key)
        value.updated = false;                      // reset update flags before processing new information
    });
    copters.forEach(update);
    deleteIfNotUpdated();
}






