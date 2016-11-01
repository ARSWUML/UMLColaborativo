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

function Clase(nombre) {
    this.type = "clase";
    this.nombre = nombre;
};

function ClaseAbstracta(nombre) {
    this.type = "claseAbstracta";
    this.nombre = nombre;
};

function Interface(nombre) {
    this.type = "interface";
    this.nombre = nombre;
};

$(document).ready(
        function () {
        }
);
