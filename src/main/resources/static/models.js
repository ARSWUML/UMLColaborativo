function Proyecto(nombre,descripcion){
    this.nombre=nombre;
    this.descripcion=descripcion;
    this.fechaCreacion=new Date();
    this.fechaUltimaModificacion= new Date();
    this.diagramas={};
};

Proyecto.prototype.agregarDiagrama=function (diagrama){
    this.diagramas[diagrama.nombre]=diagrama;
};

function DiagramaClases(nombre,descripcion){
    this.nombre=nombre;
    this.descripcion=descripcion;
    this.fechaCreacion=new Date();
    this.fechaUltimaModificacion= new Date();
    this.elementos={};
};

$(document).ready(
        function () {
        }
);
