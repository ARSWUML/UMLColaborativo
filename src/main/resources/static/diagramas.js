
var arrD={};
var diagramas=0;
var radioButtonInD='<input type="radio" name="diagrama" value="';
var radioButtonFinD='" checked> Seleccionar<br>';

function initD(){
    disconnect();
    connectD();
    getDiagramas();
};

function formAgregarDiagrama(){
    $("#newD").show();
};

function accederDiagrama(){
    disconnect();
    sessionStorage.nameDiagram=$('input[name=diagrama]:checked').val();
    console.log(sessionStorage.nameDiagram);
    window.location.href='lienzosDiagramas.html';
};

function agregarDiagrama(){
    $("#newD").hide();
    console.log($("#nomD").val());
    diagrama=new DiagramaClases($("#nomD").val(),$("#descD").val());
    sendDiagrama();
};

function agregarDiagramaVista(diag){
    diagramas++;
    arrD[diag.nombre]=diag;
    $("#listaD").append("<tr><td>"+diag.titulo+"</td><td>"+diag.descripcion+"</td><td>"+diag.fechaCreacion.toLocaleString()+
    "</td><td>"+diag.fechaUltimaModificacion.toLocaleString()+"</td><td>"+radioButtonInD+diag.titulo+radioButtonFinD+"</td></tr>");
};

function getDiagramas(){
    return $.get("/projects/users/"+sessionStorage.name+"/"+sessionStorage.nameProject,function(data){
        console.log(data);
        for(var element in data.diagramas){
            data.diagramas[element].fechaCreacion = new Date(data.diagramas[element].fechaCreacion);
            data.diagramas[element].fechaUltimaModificacion = new Date(data.diagramas[element].fechaUltimaModificacion);
            agregarDiagramaVista(data.diagramas[element]);
        };
    });
}


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
    sessionStorage.connected=false;
    if (stompClient!=null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
};

$(document).ready(
        function () {
            initD();
            $("#usrnmD").html("Usuario: "+sessionStorage.name);
            $("#proNme").html("Proyecto: "+sessionStorage.nameProject);
        }
);