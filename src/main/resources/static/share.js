/* global arrP */

proyecto="";
nombreUsuario="";

function agregarUsuario(){
    nombreUsuario=$("#nomU").val();
    var NOMproyecto=$('input[name=proyecto]:checked').val();
    proyecto=arrP[NOMproyecto];
    console.log("Compartio el proyecto: "+"/projects/users/"+nombreUsuario);
    return $.ajax({
        url: "/projects/users/"+nombreUsuario,
        type: 'POST',
        data: JSON.stringify(proyecto),
        contentType: "application/json",
        
    }).fail(function(response){alert(response.responseText);});
};

function compartir(){
    $("#nU").show();
};