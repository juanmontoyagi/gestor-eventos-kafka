package com.jumogira.gestoreventoskafka.models;

import com.amazonaws.services.sns.model.MessageAttributeValue;

import java.util.Map;

public record EventoAWSSqs(Map<String, MessageAttributeValue> attributeValueMap) implements IEventoInterno {

    @Override
    public String getId() {
        final String prefijoAWSSqs = "SQS-";
        return prefijoAWSSqs + this.attributeValueMap.get("id").getStringValue();
    }

    @Override
    public String getTitulo() {
        return this.attributeValueMap.get("titulo").getStringValue();
    }

    @Override
    public String getDescripcion() {
        return this.attributeValueMap.get("descripcion").getStringValue();
    }

    @Override
    public String getFecha() {
        return this.attributeValueMap.get("fecha").getStringValue();
    }

    @Override
    public Integer getCapacidad() {
        return Integer.parseInt(String.valueOf(this.attributeValueMap.get("capacidad")));
    }

    @Override
    public String eventoToString() {
        return "{" +
                "'ID':'" + this.getId() + '\'' +
                ", 'TITULO':" + this.getTitulo()  +
                ", 'DESCRIPCION':'" + this.getDescripcion() + '\'' +
                ", 'FECHA':'" + this.getFecha() + '\'' +
                ", 'CAPACIDAD':" + this.getCapacidad() +
                '}';
    }
}
