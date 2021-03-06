
var radioButtonIn = '<input type="radio" name="proyecto" value="';
var radioButtonFin = '" checked> Seleccionar<br>';
var proyecto = null;
var proyectos = 0;
var arrP = [];

function formAgregarProyecto() {
    $("#newP").show();
};

function accederProyecto() {
    disconnect();
    sessionStorage.nameProject = $('input[name=proyecto]:checked').val();
    window.location.href = 'diagramas.html';
};

function inicio() {
    $("#usrnm").html("Usuario: " + sessionStorage.name);
    disconnect();
    connect();
    getProyectos();
    sessionStorage.nameProject = "";
};

function agregarProyecto() {
    console.log($("#nomP").val());
    console.log($("#descP").val());
    $("#newP").hide();
    proyecto = new Proyecto($("#nomP").val(), $("#descP").val());
    postProyecto(proyecto);

};

function agregarProyectoVista(proy) {
    proyectos++;
    arrP[proy.nombre] = proy;
    $("#lista").append("<tr><td>" + proy.nombre + "</td><td>" + proy.descripcion + "</td><td>" + proy.fechaCreacion.toLocaleString() +
            "</td><td>" + proy.fechaUltimaModificacion.toLocaleString() + "</td><td>" + radioButtonIn + proy.nombre + radioButtonFin + "</td>" + "</tr>");
};

sendProject = function () {
    stompClient.send('/app/newproject.' + sessionStorage.name, {}, JSON.stringify(proyecto));
};

function connect() {
    if (sessionStorage.connected != true) {
        sessionStorage.connected = true;
        var socket = new SockJS('/stompendpoint');

        stompClient = Stomp.over(socket);
        console.info(stompClient);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/newproject.' + sessionStorage.name, function (data) {
                console.log("llego");
                var objeto = JSON.parse(data.body);
                objeto.fechaCreacion = new Date(objeto.fechaCreacion);
                objeto.fechaUltimaModificacion = new Date(objeto.fechaUltimaModificacion);
                agregarProyectoVista(objeto);
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

function getProyectos() {
    return $.get("/projects/users/" + sessionStorage.name, function (data) {
        for (var element in data) {
            data[element].fechaCreacion = new Date(data[element].fechaCreacion);
            data[element].fechaUltimaModificacion = new Date(data[element].fechaUltimaModificacion);
            agregarProyectoVista(data[element]);
        };
    }).fail(function (response) {
        $("#mesje").html(response.responseText);
        $("#errMes").show();
    });
};

function postProyecto(proyecto) {
    console.log(JSON.stringify(proyecto));
    console.log("/projects/users/" + sessionStorage.name);
    return $.ajax({
        url: "/projects/users/" + sessionStorage.name,
        type: 'POST',
        data: JSON.stringify(proyecto),
        contentType: "application/json"
    }).fail(function (response) {
        $("#mesje").html(response.responseText);
        $("#errMes").show();
    }).then(nuevoProyectoPost);
};

function nuevoProyectoPost(){
    $("#nU").hide();
    $("#mesjs").html("Se ha creado el proyecto " + proyecto.nombre);
    $("#succMes").show();
    sendProject();
}

function validar() {
    if (sessionStorage.name == null || sessionStorage.name.length == 0) {
        volver();
    }
};

function volver(){
    sessionStorage.nameProject="";
    window.location.href = 'index.html';
}

$(document).ready(
        function () {
            validar();
            inicio();
        }
);
