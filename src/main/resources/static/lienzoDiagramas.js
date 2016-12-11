/*Variables*/
stompClient = null;
isConnect = false;
elementos = [];
elementosUsados = [];
proyecto = null;
diagrama = null;

/**
 * Stomp
 */
function disconnect() {
    sessionStorage.connected = false;
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}
;

function connect() {
    if (sessionStorage.connected != true) {
        sessionStorage.connected = true;
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            stompClient.subscribe('/topic/newelement.' + sessionStorage.nameProject + "." + sessionStorage.nameDiagram, function (data) {
                var objeto = JSON.parse(data.body);
                var tmp=objeto.nombre.split(" ");
                var texto=tmp[0];
                for (var i=1;i<tmp.length-1;i++){
                    texto+=" "+tmp[i];
                }
                diagrama.agregarElemento(objeto);
                if (typeof elementosUsados[texto] == "undefined") {
                    elementosUsados[texto] = [];

                }
                elementosUsados[texto].push(objeto);
                agregarElementoDiagrama(objeto.x, objeto.y, objeto.nombre);
            });
        });

    }
}
;

sendElemento = function (objeto) {
    stompClient.send('/app/newelement.' + sessionStorage.nameProject + "." + sessionStorage.nameDiagram, {}, JSON.stringify(objeto));
};

guardarDiagrama = function () {
    proyecto.diagramas[sessionStorage.nameDiagram] = diagrama;
    return $.ajax({
        url: "/projects/users/" + sessionStorage.name,
        type: 'PUT',
        data: JSON.stringify(proyecto),
        contentType: "application/json"
    }).then(showMessage());
};

function hideMessage() {
    $("#mensaje").hide();
}

function showMessage() {
    $("#mensaje").show();
}

agregarElementoDiagrama = function (coX, coY, texto) {
    $('#lienzo').drawRect({
        fillStyle: '#afb4b7',
        x: coX, y: coY,
        width: 100,
        height: 50
    });
    $('#lienzo').drawText({
        fillStyle: '#fff',
        strokeStyle: '#000',
        x: coX, y: coY,
        fontSize: 12,
        fontFamily: 'Verdana, sans-serif',
        text: texto
    });

};

function getProyecto() {
    return $.get("/projects/users/" + sessionStorage.name + "/" + sessionStorage.nameProject).then(function (proyectoG) {
        proyecto = proyectoG;
        proyecto.fechaCreacion = new Date(proyectoG.fechaCreacion);
        proyecto.fechaUltimaModificacion = new Date(proyectoG.fechaUltimaModificacion);
        diagrama = new DiagramaClases(proyecto.diagramas[sessionStorage.nameDiagram].titulo, proyecto.diagramas[sessionStorage.nameDiagram].descripcion);
        for (var u in proyecto.diagramas[sessionStorage.nameDiagram].elementos) {
            diagrama.agregarElemento(new Clase(u, proyecto.diagramas[sessionStorage.nameDiagram].elementos[u].x, proyecto.diagramas[sessionStorage.nameDiagram].elementos[u].y));
        }
    });
}
;

/**
 * Actualiza el listado de los elementos
 */
actualizarElementos = function () {
    obtenerElementos().then(actualizarVistaElementos);
};

/**
 * Consultar los platos disponibles
 * @returns {unresolved}
 */

obtenerElementos = function () {
    elementos = [];
    return $.get("/elements", function (retorno) {
        for (var i in retorno) {
            elementos[i] = retorno[i];
        }
    });
};

/**
 * Actualiza la vista de  los elementos que se pueden arrastrar para realizar el diagrama.
 * panel conceptos
 */
actualizarVistaElementos = function () {
    for (var i in elementos) {
        $("#conceptos").append("<li id='" + elementos[i].nombre + "' class='draggable'>" + elementos[i].nombre + "</li>");
        $("li").draggable({helper: 'clone'});
    }
};

inicio = function () {
    $("#infousuario").html("<h2><strong>Usuario:</strong>" + sessionStorage.name + "<br><strong>Proyecto:</strong>" + sessionStorage.nameProject + "</h2>");
    $("#tituloDiagrama").html(sessionStorage.nameDiagram);
    var w = window.innerWidth * .6;
    var h = window.innerHeight * .8;
    $("#canvasdiv").html('<canvas id="lienzo" width="' + w + 'px" height="' + h + 'px"></canvas>');
};

function canvasDroppable() {
    $("#lienzo").droppable({
        accept: "li",
        drop: function (event, ui) {
            var texto = $(ui.draggable).clone().text();
            if (typeof elementosUsados[texto] == "undefined") {
                elementosUsados[texto] = [];
            }
            var x = event.clientX - event.target.offsetLeft;
            var y = event.clientY - event.target.offsetTop;
            var cl = new Clase(texto+" " + elementosUsados[texto].length, x, y);
            sendElemento(cl);

        }
    });
}

function validar() {
    if (sessionStorage.name == null || sessionStorage.name.length == 0) {
        signOut();
    } else if (sessionStorage.nameDiagram == null || sessionStorage.nameDiagram.length == 0 || sessionStorage.nameDiagram==='undefined') {
        volver();
    }
}

function signOut(){
    sessionStorage.nameDiagram="";
    sessionStorage.nameProject="";
    window.location.href = 'index.html';
}

function volver(){
    sessionStorage.nameDiagram="";
    window.location.href = 'diagramas.html';
}

function funcion(cadena){console.log(cadena)}

function obtenerRelaciones(){
    relaciones=[]
    nombres=[]
    return $.get("/relations",function(data){
        for (var i in data) {
            var nombre=i.split(' ')[1];
            console.log(data[i]);
            console.log(nombres);
            nombres.push(nombre);
            $('#relaciones').append('<button id="'+nombre+'" class="mui-btn mui-btn--raised" >'+i+'</button>')
            $('#'+nombre).click(function(){
                console.log(this.id);
                var className=this.id;
                console.log(window[className].prototype);
                window[className].prototype.campos.call();
                console.log(window[className].prototype.constructor.length);
                console.log(window[className].prototype.constructor.prototype);
            });
            relaciones[i]=data[i];
        }
    });
}
function solicitarElemento(name){
    for(var i in elementosUsados){
        for(var j=0;j<elementosUsados[i].length;j++){
            if(elementosUsados[i][j].nombre==name)
                return elementosUsados[i][j];
        }
    }
    return "NO";
}
$(document).ready(
        function () {
            validar();
            connect();
            hideMessage();
            inicio();
            actualizarElementos();
            getProyecto();
            canvasDroppable();
            obtenerRelaciones();
        }
);
