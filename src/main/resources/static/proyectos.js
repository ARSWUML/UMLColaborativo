
var radioButtonIn='<input type="radio" name="proyecto" value="';
var radioButtonFin='" checked> Seleccionar<br>';
var proyecto = null;
var proyectos = 0;
var arrP = [];
var stompClient = null;

function formAgregarProyecto() {
    $("#newP").show();
};

function inicio(){
    $("#usrnm").html("Usuario: " + sessionStorage.name);
    disconnect();
    connect();
    getProyectos();
};

function agregarProyecto() {
    console.log($("#nomP").val());
    console.log($("#descP").val());
    $("#newP").hide();
    proyecto = new Proyecto($("#nomP").val(), $("#descP").val());
    //postProyecto(proyecto);
    sendProject();
};

function agregarProyectoVista(proy) {
    proyectos++;
    arrP[proy.nombre]=proy;
    $("#lista").append("<tr><td>"+proy.nombre+"</td><td>"+proy.descripcion+"</td><td>"+proy.fechaCreacion.toLocaleString()+
    "</td><td>"+proy.fechaUltimaModificacion.toLocaleString()+"</td><td>"+radioButtonIn+proy.nombre+radioButtonFin+"</td>"+"</tr>");
};

sendProject = function() {
    stompClient.send('/app/newproject.' + sessionStorage.name, {}, JSON.stringify(proyecto));
};

function connect() {
    if (sessionStorage.connected!=true) {
        sessionStorage.connected = true;
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/newproject.' + sessionStorage.name, function(data) {
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
            sessionStorage.stompClient.disconnect();
        }
        console.log("Disconnected");
};

function getProyectos(){
    return $.get("/projects/users/"+sessionStorage.name,function(data){
        console.log(data);
        for(element in data){
            data[element].fechaCreacion = new Date(data[element].fechaCreacion);
            data[element].fechaUltimaModificacion = new Date(data[element].fechaUltimaModificacion);
            agregarProyectoVista(data[element]);
        };
    });
}

function postProyecto(proyecto){
    console.log(JSON.stringify(proyecto));
    console.log("plain? "+jQuery.isPlainObject(JSON.stringify(proyecto)));
    return $.post("/projects/users/"+sessionStorage.name,proyecto);
}
$(document).ready(
    function() {
        inicio();
    }
);
