proyecto=null;
nombreUsuario="";

function agregarUsuario(){
    return $.ajax({
        url: "/projects/users/"+nombreUsuario,
        type: 'PUT',
        data: JSON.stringify(proyecto),
        contentType: "application/json"
    });
};

function compartir(){
    $("#nU").show();
    nombreUsuario=$("nomU").val();
    proyecto=$('input[name=proyecto]:checked').val();
};