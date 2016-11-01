/* global arrP */

proyecto="";
nombreUsuario="";

function agregarUsuario(){
    nombreUsuario=$("#nomU").val();
    var NOMproyecto=$('input[name=proyecto]:checked').val();
    proyecto=arrP[NOMproyecto];
    console.log("Compartio el proyecto");
    return $.ajax({
        url: "/projects/users/"+nombreUsuario,
        type: 'POST',
        data: JSON.stringify(proyecto),
        contentType: "application/json",
        error: function(XMLHttpRequest, textStatus, errorThrown) {
           alert("some error: "+errorThrown);
        }
    });
};

function compartir(){
    $("#nU").show();
};