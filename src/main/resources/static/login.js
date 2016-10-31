var name="";
var connected=false;
var stompClient = null;

function setName(){
    name=$("#uname").val();
    $("#usr").hide();
    $("#proyectos").show();
    $("#usrnm").html("Usuario: "+name);
    disconnect();
    connect();
};

function connect() {
    connected=true;
    var socket = new SockJS('/stompendpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/newproject.'+name, function (data) {
            var objeto = JSON.parse(data.body);
            objeto.fechaCreacion= new Date(objeto.fechaCreacion);
            objeto.fechaUltimaModificacion= new Date(objeto.fechaUltimaModificacion);
            agregarProyectoVista(objeto);
        });
    });
};

function disconnect() {
    connected=false;
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
};

$(document).ready(
        function () {


        }
);
