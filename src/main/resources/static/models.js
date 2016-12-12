var stompClient;

function Proyecto(nombre, descripcion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.fechaCreacion = new Date();
    this.fechaUltimaModificacion = new Date();
    this.diagramas = {};
}
;

Proyecto.prototype.agregarDiagrama = function (diagrama) {
    this.diagramas[diagrama.titulo] = diagrama;
};

function DiagramaClases(nombre, descripcion) {
    this.type = "clases";
    this.titulo = nombre;
    this.descripcion = descripcion;
    this.fechaCreacion = new Date();
    this.fechaUltimaModificacion = new Date();
    this.relaciones = [];
    this.elementos = {};
}
;

DiagramaClases.prototype.agregarElemento = function (elemento) {
    this.elementos[elemento.nombre] = elemento;
};

DiagramaClases.prototype.agregarRelacion = function (relacion) {
    this.relaciones.push(relacion);
};

function Clase(nombre, x, y) {
    this.type = "clase";
    this.nombre = nombre;
    this.x = x;
    this.y = y;
}
;

function ClaseAbstracta(nombre, x, y) {
    this.type = "claseAbstracta";
    this.nombre = nombre;
    this.x = x;
    this.y = y;
}
;

function Interface(nombre, x, y) {
    this.type = "interface";
    this.nombre = nombre;
    this.x = x;
    this.y = y;
}
;

function Asociacion(elementoA, elementoB, nombre, multA, multB, atributoA, atributoB) {
    this.type = "Asociacion";
    this.elementoA = elementoA;
    this.elementoB = elementoB;
    this.nombreRelacion = nombre;
    this.multiplicidad = {};
    this.multiplicidad[this.elementoA.nombre] = multA;
    this.multiplicidad[this.elementoB.nombre] = multB;
    this.isbidireccional = true;
    this.atributoA = atributoA;
    this.atributoB = atributoB;
}
;

Asociacion.prototype.dibujar = function (canvasName) {
    //Linea
    $('#' + canvasName).drawLine({
        strokeStyle: '#000',
        strokeWidth: 2,
        arrowRadius: 15,
        arrowAngle: 90,
        x1: this.elementoA.x + 50, y1: this.elementoA.y,
        x2: this.elementoB.x - 50, y2: this.elementoB.y
    });
    //nombre
    $('#' + canvasName).drawText({
        fillStyle: '#fff',
        strokeStyle: '#000',
        x: (this.elementoA.x+this.elementoB.x)/2, y: ((this.elementoA.y+this.elementoB.y)/2)-20,
        fontSize: 15,
        fontFamily: 'Verdana, sans-serif',
        text: this.nombreRelacion
    });
    //Nombre de B en A
    $('#' + canvasName).drawText({
        fillStyle: '#fff',
        strokeStyle: '#000',
        x: this.elementoA.x+50+(10*(this.atributoA.length/2)), y: this.elementoA.y-10,
        fontSize: 11,
        fontFamily: 'Verdana, sans-serif',
        text: this.atributoB
    });
    //Nombre de A en B
    $('#' + canvasName).drawText({
        fillStyle: '#fff',
        strokeStyle: '#000',
        x: this.elementoB.x-50-(10*(this.atributoA.length/2)), y: this.elementoB.y-10,
        fontSize: 11,
        fontFamily: 'Verdana, sans-serif',
        text: this.atributoA
    });
    //multiplicidad A
    $('#' + canvasName).drawText({
        fillStyle: '#fff',
        strokeStyle: '#000',
        x: this.elementoA.x+60, y: this.elementoA.y+10,
        fontSize: 11,
        fontFamily: 'Verdana, sans-serif',
        text: this.multiplicidad[this.elementoA.nombre][0]+".."+this.multiplicidad[this.elementoA.nombre][1]
    });
    //multiplicidad B
    $('#' + canvasName).drawText({
        fillStyle: '#fff',
        strokeStyle: '#000',
        x: this.elementoB.x-60, y: this.elementoB.y+10,
        fontSize: 11,
        fontFamily: 'Verdana, sans-serif',
        text: this.multiplicidad[this.elementoB.nombre][0]+".."+this.multiplicidad[this.elementoB.nombre][1]
    });
};

Asociacion.prototype.campos = function () {
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
    $('#send').click(function () {
        var elA = solicitarElemento($("#elA").val());
        console.log(elA);
        if (elA != 'NO') {
            var elB = solicitarElemento($("#elB").val());
            console.log(elB);
            if ($("#elAmul1").val() > 0 && $("#elAmul2").val() > 0 && $("#elBmul1").val() > 0 && $("#elBmul2").val() > 0) {
                var multA = [$("#elAmul1").val(), $("#elAmul2").val()];
                var multB = [$("#elBmul1").val(), $("#elBmul2").val()];
                var nomA = $("#atribA").val();
                var nomB = $("#atribB").val();
                relacion = new Asociacion(elA, elB, $('#nombreR').val(), multA, multB, nomA, nomB);
                console.log(relacion);
                sendRelacion(relacion);
                $('#formulario').hide();
            }
        }
    });
    $('#formulario').show();
};

function Dependencia(elementoA, elementoB, nombre) {
    this.type = "Dependencia";
    this.elementoA = elementoA;
    this.elementoB = elementoB;
    this.nombreRelacion = nombre;
    this.multiplicidad = {};
    this.multiplicidad[this.elementoA.nombre] = 1;
    this.multiplicidad[this.elementoB.nombre] = 1;
    this.isbidireccional = false;
}
;

Dependencia.prototype.dibujar = function (canvasName) {
    $('#' + canvasName).drawLine({
        strokeStyle: '#000',
        strokeWidth: 2,
        rounded: true,
        endArrow: true,
        strokeDash: [4],
        strokeDashOffset: 0,
        arrowRadius: 15,
        arrowAngle: 90,
        x1: this.elementoA.x + 50, y1: this.elementoA.y,
        x2: this.elementoB.x - 50, y2: this.elementoB.y
    });
    $('#' + canvasName).drawText({
        fillStyle: '#fff',
        strokeStyle: '#000',
        x: (this.elementoA.x+this.elementoB.x)/2, y: ((this.elementoA.y+this.elementoB.y)/2)-20,
        fontSize: 12,
        fontFamily: 'Verdana, sans-serif',
        text: this.nombreRelacion
    });
};

Dependencia.prototype.campos = function () {
    var relacion;
    $('#formulario').empty();
    $('#formulario').append('\
        <div class="mui-textfield mui-textfield--float-label">\
        Nombre de la relación: <input type="text" id="nombreR">\
        Elemento 1: <input type="text" id="elA">\
        Elemento 2: <input type="text" id="elB">\
        </div>\
        <button id="send" class="mui-btn mui-btn--raised">Crear</button>');
    $('#send').click(function () {
        var elA = solicitarElemento($("#elA").val());
        console.log(elA);
        if (elA != 'NO') {
            var elB = solicitarElemento($("#elB").val());
            console.log(elB);
            relacion = new Dependencia(elA, elB, $('#nombreR').val());
            console.log(relacion);
            sendRelacion(relacion);
            $('#formulario').hide();
        }
    });
    $('#formulario').show();
};

$(document).ready(
        function () {
        }
);
