var botonAcceder='<button type="submit" class="mui-btn mui-btn--raised" onclick=""><i class="fa fa-rocket"></i>Acceder</button>';
var radioButton='<input type="radio" name="proyecto" value="proy.nombre" checked> Seleccionar<br>';
var formIn='<form id="radio">';
var formFin= '</form>';
var proyecto = null;
var proyectos=0;
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
    $("#lista").append("<tr><td>"+proy.nombre+"</td><td>"+proy.descripcion+"</td><td>"+proy.fechaCreacion.toLocaleString()+
    "</td><td>"+proy.fechaUltimaModificacion.toLocaleString()+"</td><td>"+botonAcceder+"</td><td>"+radioButton+"</td>"+"</tr>");
};

sendProject = function () {
    stompClient.send('/app/newproject.'+name, {}, JSON.stringify(proyecto));
};
