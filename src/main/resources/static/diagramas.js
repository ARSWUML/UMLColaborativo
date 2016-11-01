/* global proyecto, nameProject, proyectos */
var arrD={};
var diagramas=0;
var botonAccederInD = '<button type="submit" class="mui-btn mui-btn--raised" value="';
var botonAccederFinD = '" onclick="accederDiagrama()"><i class="fa fa-rocket"></i>Acceder</button>';

function accederProyecto(){
    console.log("Accedio a diagramas!!!!");
    sessionStorage.nameProject=$('input[name=proyecto]:checked').val();
    window.location.href='diagramas.html';
    //initD();
};

function initD(){
    disconnect();
    connectD();
};

function formAgregarDiagrama(){
    $("#newD").show();
};

function agregarDiagramas(){
    $("#newD").hide();
    diagrama=new DiagramaClases($("#nomD").val(),$("#descD").val());
    sendDiagrama();
};

function agregarDiagramaVista(diag){
    diagramas++;
    arrD[diag.nombre]=diag;
    $("#listaD").append("<tr><td>"+diag.nombre+"</td><td>"+diag.descripcion+"</td><td>"+diag.fechaCreacion.toLocaleString()+
    "</td><td>"+diag.fechaUltimaModificacion.toLocaleString()+"</td><td>"+botonAccederInD+diag.nombre+botonAccederFinD+"</td></tr>");
};

sendDiagrama = function () {
    stompClient.send('/app/newdiagram.'+sessionStorage.nameProject, {}, JSON.stringify(diagrama));
};


function connectD() {
    if (sessionStorage.connected!=true) {
        sessionStorage.connected = true;
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/newdiagram.' + sessionStorage.nameProject, function(data) {
                console.log("llego");
                var objeto = JSON.parse(data.body);
                objeto.fechaCreacion = new Date(objeto.fechaCreacion);
                objeto.fechaUltimaModificacion = new Date(objeto.fechaUltimaModificacion);
                agregarDiagramaVista(objeto);
            });
        });
    }
};

function disconnect() {
    connected=false;
    if (stompClient != null) {
        sessionStorage.stompClient.disconnect();
    }
    console.log("Disconnected");
};

$(document).ready(
        function () {
            $("#proNme").html("Proyecto: "+sessionStorage.nameProject);

        }
);