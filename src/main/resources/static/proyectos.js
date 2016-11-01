var botonAcceder='<button type="submit" class="mui-btn mui-btn--raised" onclick=""><i class="fa fa-rocket"></i>Acceder</button>';
var radioButtonIn='<input type="radio" name="proyecto" value="';
var radioButtonFin='" checked> Seleccionar<br>';
var formIn='<form id="radio">';
var formFin= '</form>';
var proyecto = null;
var proyectos=0;
var arrP={};
var connected=null;
var stompClient = null;
function formAgregarProyecto(){
    $("#newP").show();
};

function agregarProyecto(){
    console.log($("#nomP").val());
    console.log($("#descP").val());
    $("#newP").hide();
    proyecto=new Proyecto($("#nomP").val(),$("#descP").val());
    sendProject();
};

function agregarProyectoVista(proy){
    proyectos++;
    arrP[proy.nombre]=proy;
    $("#lista").append("<tr><td>"+proy.nombre+"</td><td>"+proy.descripcion+"</td><td>"+proy.fechaCreacion.toLocaleString()+
    "</td><td>"+proy.fechaUltimaModificacion.toLocaleString()+"</td><td>"+botonAcceder+"</td><td>"+radioButtonIn+proy.nombre+radioButtonFin+"</td>"+"</tr>");
    console.log("</td><td>"+radioButtonIn+proy.nombre+radioButtonFin+"</td>");
};

sendProject = function () {
    stompClient.send('/app/newproject.'+name, {}, JSON.stringify(proyecto));
};

function connect() {
    connected=true;
    var socket = new SockJS('/stompendpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/newproject.'+sessionStorage.name, function (data) {
            console.log("llego");
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
        sessionStorage.stompClient.disconnect();
    }
    console.log("Disconnected");
};
$(document).ready(
        function () {
            $("#usrnm").html("Usuario: "+sessionStorage.name);
            connect();

        }
);
