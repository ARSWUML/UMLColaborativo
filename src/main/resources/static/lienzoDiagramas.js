/*Variables*/
stompClient = null;
isConnect = false;
elementos = [];
/**/

/**
 * Stomp
 */
function disconnect() {
    sessionStorage.connected = false;
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}
;

function connect() {
    if (sessionStorage.connected != true) {
        sessionStorage.connected = true;
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);

    }
}
;
agregarElementoDiagrama = function () {

};
/**
 * Actualiza el listado de los elementos
 */
actualizarElementos = function () {
    obtenerElementos().then(actualizarVistaElementos);
};
/**
 * Consultar los platos disponibles
 * @returns {unresolved}
 */
obtenerElementos = function () {
    elementos = [];
    return $.get("/elements", function (retorno) {
        for (var i in retorno) {
            elementos[i] = retorno[i];
        }
    });
};
/**
 * Actualiza la vista de  los elementos que se pueden arrastrar para realizar el diagrama.
 * panel conceptos
 */
actualizarVistaElementos = function () {
    for (var i in elementos) {
        $("#conceptos").append("<li id='" + elementos[i].nombre + "' class='draggable'>" + elementos[i].nombre + "</li>");
        $("li").draggable({helper: 'clone'});
    }
};
inicio = function () {
    $("#infousuario").html("<center>Usuario:" + sessionStorage.name + "</center><center>Nombre del proyecto:" + sessionStorage.nameProject + "</center>");
    $("#tituloDiagrama").html(sessionStorage.nameDiagram);
};
$(document).ready(
        function () {
            inicio();
            actualizarElementos();
            $("#lienzo").droppable({
                accept: "li",
                drop: function (event, ui) {
                    var context = $(this)[0].getContext("2d");
                    context.font = "16px helvetica";
                    context.fillText($(ui.draggable).clone().text(), ui.position.left - event.target.offsetLeft, ui.position.top - event.target.offsetTop);
                }
            });


        }
);



