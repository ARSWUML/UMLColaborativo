function Proyecto(nombre,descripcion){
    this.nombre=nombre;
    this.descripcion=descripcion;
    this.diagramas=[];
};

Proyecto.prototype.agregarDiagrama=function (diagrama){
    this.diagramas.push(diagrama);
};

function Diagrama(nombre,descripcion){
    this.nombre=nombre;
    this.descripcion=descripcion;
    this.elementos=[];
};

$(document).ready(
        function () {
        }
);
