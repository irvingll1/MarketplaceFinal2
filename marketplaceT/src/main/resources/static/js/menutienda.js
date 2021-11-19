/*<![CDATA[*/
function newLocation(newLat, newLng)
{
    map.setCenter({
        lat: newLat,
        lng: newLng
    });
}
// Load initialize function
//google.maps.event.addDomListener(window, 'load', iniciarMap);
$(document).on('change', '#distrito', function () {
    var tiendas = document.getElementById("tienda");
    var length = tiendas.options.length;
    for (i = length - 1; i > 0; i--) {
        tiendas.options[i] = null;
    }
    switch ($("#distrito option:selected").val()) {
        case '1':
            newLocation(parseFloat(ubicacion[0].latitud), parseFloat(ubicacion[0].longitud));
            for (var i = 0; i < tiendass.length; i++) {
                console.log(tiendass[i].distrito.descripcion);
                //console.log(ubicacion[i].descripcion);
                if (tiendass[i].distrito.descripcion == ubicacion[0].descripcion) {
                    var option = document.createElement("option");
                    option.text = tiendass[i].nombre;
                    option.value = tiendass[i].id;
                    tiendas.add(option);
                }
            }
            break;
        case '2':
            newLocation(parseFloat(ubicacion[1].latitud), parseFloat(ubicacion[1].longitud));
            for (var i = 0; i < tiendass.length; i++) {
                console.log(tiendass[i].distrito.descripcion);
                //console.log(ubicacion[i].descripcion);
                if (tiendass[i].distrito.descripcion == ubicacion[1].descripcion) {
                    var option = document.createElement("option");
                    option.text = tiendass[i].nombre;
                    option.value = tiendass[i].id;
                    tiendas.add(option);
                }
            }
            break;
        case '3':
            newLocation(parseFloat(ubicacion[2].latitud), parseFloat(ubicacion[2].longitud));
            for (var i = 0; i < tiendass.length; i++) {
                console.log(tiendass[i].distrito.descripcion);
                //console.log(ubicacion[i].descripcion);
                if (tiendass[i].distrito.descripcion == ubicacion[2].descripcion) {
                    var option = document.createElement("option");
                    option.text = tiendass[i].nombre;
                    option.value = tiendass[i].id;
                    tiendas.add(option);
                }
            }
            break;
        case '4':
            newLocation(parseFloat(ubicacion[3].latitud), parseFloat(ubicacion[3].longitud));
            for (var i = 0; i < tiendass.length; i++) {
                console.log(tiendass[i].distrito.descripcion);
                //console.log(ubicacion[i].descripcion);
                if (tiendass[i].distrito.descripcion == ubicacion[3].descripcion) {
                    var option = document.createElement("option");
                    option.text = tiendass[i].nombre;
                    option.value = tiendass[i].id;
                    tiendas.add(option);
                }
            }
            break;
        case '5':
            newLocation(parseFloat(ubicacion[4].latitud), parseFloat(ubicacion[4].longitud));
            for (var i = 0; i < tiendass.length; i++) {
                console.log(tiendass[i].distrito.descripcion);
                //console.log(ubicacion[i].descripcion);
                if (tiendass[i].distrito.descripcion == ubicacion[4].descripcion) {
                    var option = document.createElement("option");
                    option.text = tiendass[i].nombre;
                    option.value = tiendass[i].id;
                    tiendas.add(option);
                }
            }
            break;
        case '6':
            newLocation(parseFloat(ubicacion[5].latitud), parseFloat(ubicacion[5].longitud));
            for (var i = 0; i < tiendass.length; i++) {
                console.log(tiendass[i].distrito.descripcion);
                //console.log(ubicacion[i].descripcion);
                if (tiendass[i].distrito.descripcion == ubicacion[5].descripcion) {
                    var option = document.createElement("option");
                    option.text = tiendass[i].nombre;
                    option.value = tiendass[i].id;
                    tiendas.add(option);
                }
            }
            break;
    }
});
/*]]>*/
