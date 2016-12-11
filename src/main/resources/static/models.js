var stompClient;

function Proyecto(nombre, descripcion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.fechaCreacion = new Date();
    this.fechaUltimaModificacion = new Date();
    this.diagramas = {};
};

Proyecto.prototype.agregarDiagrama = function (diagrama) {
    this.diagramas[diagrama.titulo] = diagrama;
};

function DiagramaClases(nombre, descripcion) {
    this.type = "clases";
    this.titulo = nombre;
    this.descripcion = descripcion;
    this.fechaCreacion = new Date();
    this.fechaUltimaModificacion = new Date();
    this.elementos = {};
};

DiagramaClases.prototype.agregarElemento = function (elemento) {
    this.elementos[elemento.nombre] = elemento;
};

function Clase(nombre,x,y) {
    this.type = "clase";
    this.nombre = nombre;
    this.x=x;
    this.y=y;
};

function ClaseAbstracta(nombre,x,y) {
    this.type = "claseAbstracta";
    this.nombre = nombre;
    this.x=x;
    this.y=y;
};

function Interface(nombre,x,y) {
    this.type = "interface";
    this.nombre = nombre;
    this.x=x;
    this.y=y;
};

function Asociacion(elementoA,elementoB,nombre,multA,multB,atributoA,atributoB){
    this.elementoA=elementoA;
    this.elementoB=elementoB;
    this.nombreRelacion=nombre;
    this.multiplicidad={};
    this.multiplicidad[this.elementoA.nombre]=multA;
    this.multiplicidad[this.elementoB.nombre]=multB;
    this.isbidireccional=true;
    this.atributoA=atributoA;
    this.atributoB=atributoB;
};

Asociacion.prototype.dibujar = function(canvasName){
    $('#'+canvasName).drawLine({
        strokeStyle: '#000',
        strokeWidth: 2,
        arrowRadius: 15,
        arrowAngle: 90,
        x1: this.elementoA.x+50, y1: this.elementoA.y,
        x2: this.elementoB.x-50, y2: this.elementoB.y
    });
}

Asociacion.prototype.campos= function(){
    var relacion;
    $('#formulario').empty();
    $('#formulario').append('\
        <div class="mui-textfield mui-textfield--float-label">\
        Nombre de la relacion: <input type="text" id="nombreR">\
        Elemento 1: <input type="text" id="elA">\
        Elemento 2: <input type="text" id="elB">\
        Multiplicidad de 1 a 2 de <input type="number" id="elAmul1"> a <input type="number" id="elAmul2">\
        Multiplicidad de 2 a 1 de <input type="number" id="elBmul1"> a <input type="number" id="elBmul2">\
        Nombre de 2 en 1: <input type="text" id="atribA">\
        Nombre de 1 en 2: <input type="text" id="atribB">\
        </div>\
        <button id="send" class="mui-btn mui-btn--raised">Crear</button>');
    $('#send').click(function(){
        var elA=solicitarElemento($("#elA").val());
        console.log(elA);
        if(elA!='NO'){
            var elB=solicitarElemento($("#elB").val());
            console.log(elB);
            if($("#elAmul1").val()>0 && $("#elAmul2").val()>0 && $("#elBmul1").val()>0 && $("#elBmul2").val()>0){
                var multA=[$("#elAmul1").val(),$("#elAmul2").val()];
                var multB=[$("#elBmul1").val(),$("#elBmul2").val()];
                var nomA=$("#atribA").val()
                var nomB=$("#atribB").val()
                relacion= new Asociacion(elA,elB,$('#nombreR'),multA,multB,nomA,nomB);
                console.log(relacion);
                relacion.dibujar('lienzo');
                $('#formulario').hide();
            }
        }
    });
    $('#formulario').show();
}

function Dependencia(elementoA,elementoB,nombre){
    this.elementoA=elementoA;
    this.elementoB=elementoB;
    this.nombreRelacion=nombre;
    this.multiplicidad={};
    this.multiplicidad[this.elementoA.nombre]=1;
    this.multiplicidad[this.elementoB.nombre]=1;
    this.isbidireccional=false;
};

Dependencia.prototype.dibujar = function(canvasName){
    $('#'+canvasName).drawLine({
        strokeStyle: '#000',
        strokeWidth: 2,
        rounded: true,
        endArrow: true,
        strokeDash: [4],
        strokeDashOffset: 0,
        arrowRadius: 15,
        arrowAngle: 90,
        x1: this.elementoA.x+50, y1: this.elementoA.y,
        x2: this.elementoB.x-50, y2: this.elementoB.y
    });
}

Dependencia.prototype.campos= function(){
    var relacion;
    $('#formulario').empty();
    $('#formulario').append('\
        <div class="mui-textfield mui-textfield--float-label">\
        Nombre de la relacion: <input type="text" id="nombreR">\
        Elemento 1: <input type="text" id="elA">\
        Elemento 2: <input type="text" id="elB">\
        </div>\
        <button id="send" class="mui-btn mui-btn--raised">Crear</button>');
    $('#send').click(function(){
        var elA=solicitarElemento($("#elA").val());
        console.log(elA);
        if(elA!='NO'){
            var elB=solicitarElemento($("#elB").val());
            console.log(elB);
                relacion= new Dependencia(elA,elB,$('#nombreR'));
                console.log(relacion);
                relacion.dibujar('lienzo');
                $('#formulario').hide();
        }
    });
    $('#formulario').show();
}

$(document).ready(
        function () {
        }
);
