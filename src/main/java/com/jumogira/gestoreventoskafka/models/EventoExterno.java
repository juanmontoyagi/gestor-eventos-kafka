package com.jumogira.gestoreventoskafka.models;

public record EventoExterno(String id, String titulo, String descripcion, String fecha, Integer capacidad) implements IEventoInterno {

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getTitulo() {
        return this.titulo;
    }

    @Override
    public String getDescripcion() {
        return this.descripcion;
    }

    @Override
    public String getFecha() {
        return this.fecha;
    }

    @Override
    public Integer getCapacidad() {
        return this.capacidad;
    }

    @Override
    public String eventoToString() {
        return "{" +
                "'ID':'" + this.id() + '\'' +
                ", 'TITULO':" + this.titulo()  +
                ", 'DESCRIPCION':'" + this.descripcion() + '\'' +
                ", 'FECHA':'" + this.fecha() + '\'' +
                ", 'CAPACIDAD':" + this.capacidad() +
                '}';
    }
}
