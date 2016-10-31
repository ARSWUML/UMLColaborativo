

function agregarUsuario(proyecto,nombreUsuario){
    return $.ajax({
        url: "/projects/users/"+nombreUsuario,
        type: 'PUT',
        data: JSON.stringify(proyecto),
        contentType: "application/json"
    });
};

function compartir(){
    $("#nU").show();
    //var name=$("nomU").val();
    //var socket = new SockJS('/stompendpoint');
    //stompClient = Stomp.over(socket);
    //stompClient.connect({}, function (frame) {
        
        //console.log('Connected: ' + frame);
        
        //stompClient.subscribe('/topic/newproject', function (data) {
            //var co=JSON.parse(data.body);
            //console.log(co);
            //agregarUsuario(co,name);
        //});
        
        
    //});
};