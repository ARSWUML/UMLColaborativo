
username="";
function setName(){
    sessionStorage.name=username;
    window.location.href='proyectos.html';
};

function validar(){
    username=$("#username").val();
    var passw=hex_sha1($("#passwd").val());
    comprobar(passw);
    
    
}
function comprobar(passw){
    return $.get("/seguridad/"+username+"/"+passw, function(data){
            if(data){
                setName();
            }else{
                $("#mensaje").show();
            }
    }).fail(function (){
                $("#mensaje").show();
    } );
}
$(document).ready(
        function () {
            $("#mensaje").hide();

        }
);
