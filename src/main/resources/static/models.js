var stompClient;

function Proyecto(nombre, descripcion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.fechaCreacion = new Date();
    this.fechaUltimaModificacion = new Date();
    this.diagramas = {};
};

Proyecto.prototype.agregarDiagrama = function (diagrama) {
    this.diagramas[diagrama.nombre] = diagrama;
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

$(document).ready(
        function () {
        }
);
