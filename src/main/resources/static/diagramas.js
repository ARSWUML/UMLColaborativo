
var arrD = {};
var diagramas = 0;
var botonAccederInD = '<button type="submit" class="mui-btn mui-btn--raised" value="';
var botonAccederFinD = '" onclick="accederDiagrama()"><i class="fa fa-rocket"></i>Acceder</button>';


function initD() {
    disconnect();
    connectD();
    $("#usrnmD").html("Usuario: " + sessionStorage.name);
    $("#proNme").html("Proyecto: " + sessionStorage.nameProject);
}
;

function formAgregarDiagrama() {
    $("#newD").show();
}
;

function agregarDiagrama() {
    $("#newD").hide();
    console.log($("#nomD").val());
    diagrama = new DiagramaClases($("#nomD").val(), $("#descD").val());
    sendDiagrama();
}
;

function agregarDiagramaVista(diag) {
    diagramas++;
    arrD[diag.nombre] = diag;
    $("#listaD").append("<tr><td>" + diag.titulo + "</td><td>" + diag.descripcion + "</td><td>" + diag.fechaCreacion.toLocaleString() +
            "</td><td>" + diag.fechaUltimaModificacion.toLocaleString() + "</td><td>" + botonAccederInD + diag.nombre + botonAccederFinD + "</td></tr>");
}
;


sendDiagrama = function () {
    stompClient.send('/app/newdiagram.' + sessionStorage.nameProject, {}, JSON.stringify(diagrama));
};


function connectD() {
    if (sessionStorage.connected != true) {
        sessionStorage.connected = true;
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/newdiagram.' + sessionStorage.nameProject, function (data) {
                console.log("llego");
                var objeto = JSON.parse(data.body);
                objeto.fechaCreacion = new Date(objeto.fechaCreacion);
                objeto.fechaUltimaModificacion = new Date(objeto.fechaUltimaModificacion);
                agregarDiagramaVista(objeto);
            });
        });
    }
}
;

function disconnect() {
    sessionStorage.connected = false;
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}
;
function validar() {
    if (sessionStorage.name == null || sessionStorage.name.length == 0) {
        window.location.href = 'index.html';
    }else if(sessionStorage.nameProject == null || sessionStorage.nameProject.length == 0){
        window.location.href = 'proyectos.html';
    }
}

$(document).ready(
        function () {
            validar();
            initD();

        }
);