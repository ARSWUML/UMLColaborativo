
var arrD = [];
var diagramas = 0;
var radioButtonInD = '<input type="radio" name="diagrama" value="';
var radioButtonFinD = '" checked> Seleccionar<br>';
var proyecto = null;

function initD() {
    disconnect();
    connectD();
    $("#usrnmD").html("Usuario: " + sessionStorage.name);
    $("#proNme").html("Proyecto: " + sessionStorage.nameProject);
    getDiagramas();
};

function formAgregarDiagrama() {
    $("#newD").show();
};

function accederDiagrama() {
    disconnect();
    sessionStorage.nameDiagram = $('input[name=diagrama]:checked').val();
    console.log(sessionStorage.nameDiagram);
    window.location.href = 'lienzoDiagramas.html';
};

function agregarDiagrama() {
    $("#newD").hide();
    if($("#nomD").val() == null || $("#nomD").val().length == 0){
        $("#mesjeD").html("Ingrese un titulo al diagrama");
        $("#errMesD").show();
    }else if($("#descD").val() == null || $("#descD").val().length == 0){
        $("#mesjeD").html("Agregue una descripcion  al diagrama");
        $("#errMesD").show();
    }else{
    diagrama = new DiagramaClases($("#nomD").val(), $("#descD").val());
    getProyecto().then(actualizarProyecto);
    sendDiagrama();
    }
};

function hideErrorD() {
    $("#errMesD").hide();
};

function agregarDiagramaVista(diag) {
    diagramas++;
    arrD[diag.nombre] = diag;
    $("#listaD").append("<tr><td>" + diag.titulo + "</td><td>" + diag.descripcion + "</td><td>" + diag.fechaCreacion.toLocaleString() +
            "</td><td>" + diag.fechaUltimaModificacion.toLocaleString() + "</td><td>" + radioButtonInD + diag.titulo + radioButtonFinD + "</td></tr>");
};

function getDiagramas() {
    return $.get("/projects/users/" + sessionStorage.name + "/" + sessionStorage.nameProject, function (data) {
        console.log(data);
        for (var element in data.diagramas) {
            data.diagramas[element].fechaCreacion = new Date(data.diagramas[element].fechaCreacion);
            data.diagramas[element].fechaUltimaModificacion = new Date(data.diagramas[element].fechaUltimaModificacion);
            agregarDiagramaVista(data.diagramas[element]);
        }
        ;
    });
}


sendDiagrama = function () {
    stompClient.send('/app/newdiagram.' + sessionStorage.nameProject, {}, JSON.stringify(diagrama));
};

function getProyecto() {
    return $.get("/projects/users/" + sessionStorage.name + "/" + sessionStorage.nameProject).then(function (proyectoG) {
        proyecto = new Proyecto(proyectoG.nombre, proyectoG.descripcion);
        proyecto.fechaCreacion = new Date(proyectoG.fechaCreacion);
        proyecto.fechaUltimaModificacion = new Date(proyectoG.fechaUltimaModificacion);
    });
};


actualizarProyecto = function () {
    console.log(diagrama.titulo);
    proyecto.agregarDiagrama(diagrama);
    return $.ajax({
        url: "/projects/users/" + sessionStorage.name,
        type: 'PUT',
        data: JSON.stringify(proyecto),
        contentType: "application/json"
    });
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
};

function disconnect() {
    sessionStorage.connected = false;
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
};

function validar() {
    if (sessionStorage.name == null || sessionStorage.name.length == 0) {
        signOut();
    } else if (sessionStorage.nameProject == null || sessionStorage.nameProject.length == 0) {
        volver();
    }
}

function volver(){
    sessionStorage.nameDiagram="";
    window.location.href = 'proyectos.html';
}

function signOut(){
    sessionStorage.nameDiagram="";
    sessionStorage.nameProject="";
    window.location.href = 'index.html';
}

$(document).ready(
        function () {
            validar();
            initD();

        }
);
