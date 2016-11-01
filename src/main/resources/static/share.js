/* global arrP */

proyecto = "";
nombreUsuario = "";

function agregarUsuario() {
    nombreUsuario = $("#nomU").val();
    if (nombreUsuario != null && nombreUsuario.length > 0) {
        var NOMproyecto = $('input[name=proyecto]:checked').val();
        proyecto = arrP[NOMproyecto];
        return $.ajax({
            url: "/projects/users/" + nombreUsuario,
            type: 'POST',
            data: JSON.stringify(proyecto),
            contentType: "application/json",
        }).fail(function (response) {
            $("#mesje").html(response.responseText);
            $("#errMes").show();
        }).then(success);
    } else {
        $("#mesje").html("Ingrese un nombre de usuario para compartir");
        $("#errMes").show();
    }
};

sendProjecto = function (proy) {
    stompClient.send('/app/newproject.' + nombreUsuario, {}, JSON.stringify(proy));
};

function compartir() {
    $("#nU").toggle();
};

function success() {
    sendProjecto(proyecto);
    $("#nU").hide();
    $("#mesjs").html("Se ha compartido el proyecto " + proyecto.nombre + " con el usuario " + nombreUsuario);
    $("#succMes").show();
};

function hideError() {
    $("#errMes").hide();
};

function hideSuccess() {
    $("#succMes").hide();
};