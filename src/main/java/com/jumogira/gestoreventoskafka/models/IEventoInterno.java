package com.jumogira.gestoreventoskafka.models;

public interface IEventoInterno {

    String getId();
    String getTitulo();
    String getDescripcion();
    String getFecha();
    Integer getCapacidad();
    String eventoToString();
}
