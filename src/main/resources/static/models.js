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
    this.multiplicidad={this.elementoA.nombre:multA,this.elementoB.nombre:multB};
    this.isbidireccional=true;
    this.atributoA=atributoA;
    this.atributoB=atributoB;
};

function Dependencia(elementoA,elementoB,nombre){
    this.elementoA=elementoA;
    this.elementoB=elementoB;
    this.nombreRelacion=nombre;
    this.multiplicidad={this.elementoA.nombre:1,this.elementoB.nombre:1};
    this.isbidireccional=false;
};

$(document).ready(
        function () {
        }
);
