var botonAcceder='<button type="submit" class="mui-btn mui-btn--raised" onclick=""><i class="fa fa-rocket"></i>Acceder</button>';
var botonCompartir='<button type="submit" class="mui-btn mui-btn--raised" onclick="compartir()"><i class="fa fa-share-alt"></i>Compartir</button>';
var proyecto = null;
var proyectos=0;
var divIn='<div>';
var divFin='</div>';
var divHiddenIn = '<div id="nU" hidden="hidden"';
var divHiddenFin = '</div>';
var nombreText='<input type="text" placeholder="Nombre usuario" id="nomU">';
var botonAceptar='<button>Aceptar</button>';
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
    "</td><td>"+proy.fechaUltimaModificacion.toLocaleString()+"</td><td>"+botonAcceder+"</td>"+divIn+"<td>"+
    botonCompartir+"</td>"+divHiddenIn+nombreText+botonAceptar+divHiddenFin+divFin+"</tr>");
};

sendProject = function () {
    stompClient.send('/app/newproject.'+name, {}, JSON.stringify(proyecto));
};
