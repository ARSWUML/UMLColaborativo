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
        x1: elementoA.x, y1: elementoA.y,
        x2: elementoB.x, y2: elementoB.y
    });
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
        x1: elementoA.x, y1: elementoA.y,
        x2: elementoB.x, y2: elementoB.y
    });
}

$(document).ready(
        function () {
        }
);
