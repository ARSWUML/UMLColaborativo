var botonAcceder='<button type="submit" class="mui-btn mui-btn--raised" onclick=""><i class="fa fa-rocket"></i>Acceder</button>';
var botonCompartir='<button type="submit" class="mui-btn mui-btn--raised" onclick="compartir()"><i class="fa fa-share-alt"></i>Compartir</button>';
var proyecto = null;
var proyectos=0;
function formAgregarProyecto(){
    $("#newP").show();
};

function agregarProyecto(){
    console.log($("#nomP").val());
    console.log($("#descP").val());
    $("#newP").hide();
    proyecto=new Proyecto($("#nomP").val(),$("#descP").val())
    sendProject();
};

function agregarProyectoVista(proy){
    proyectos++;
    $("#lista").append("<tr><td>"+proy.nombre+"</td><td>"+proy.descripcion+"</td><td>"+proy.fechaCreacion.toLocaleString()+
    "</td><td>"+proy.fechaUltimaModificacion.toLocaleString()+"</td><td>"+botonAcceder+"</td><td>"+botonCompartir+"</td></tr>");
};

sendProject = function () {
    stompClient.send('/app/newproject.'+name, {}, JSON.stringify(proyecto));
};
