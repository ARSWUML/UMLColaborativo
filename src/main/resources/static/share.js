/* global arrP */

proyecto="";
nombreUsuario="";

function agregarUsuario(){
    nombreUsuario=$("#nomU").val();
    var NOMproyecto=$('input[name=proyecto]:checked').val();
    proyecto=arrP[NOMproyecto];
    return $.ajax({
        url: "/projects/users/"+nombreUsuario,
        type: 'POST',
        data: JSON.stringify(proyecto),
        contentType: "application/json"
    });
};

function compartir(){
    $("#nU").show();
};