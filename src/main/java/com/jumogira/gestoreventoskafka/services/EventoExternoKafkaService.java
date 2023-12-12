package com.jumogira.gestoreventoskafka.services;

import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.jumogira.gestoreventoskafka.models.EventoAWSSqs;
import com.jumogira.gestoreventoskafka.models.IEventoInterno;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Service
public class EventoExternoKafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topico, IEventoInterno eventoInterno){
        var future = kafkaTemplate.send(topico, eventoInterno.getId(), eventoInterno.eventoToString());

        future.whenComplete((resultadoEnvio, excepcion)->{
            if(excepcion != null){
                log.error(excepcion.getMessage());
                future.completeExceptionally(excepcion);
            } else {
                future.complete(resultadoEnvio);
                log.info("Evento Externo enviado al topico -> " + topico + " en Kafka " + eventoInterno.eventoToString());
            }
        });
    }

    private List<IEventoInterno> transformEventFromAWSSqsToEventoInterno(List<Message> messages) {
        List<IEventoInterno> eventoInternos = new LinkedList<>();
        for(Message message: messages){
            Map<String, MessageAttributeValue> atributosMensaje = message.getMessageAttributes();
            EventoAWSSqs eventoAWSSqs = new EventoAWSSqs(atributosMensaje);
            eventoInternos.add(eventoAWSSqs);
        }
        return eventoInternos;
    }

    public String sendAWSSqsListMessagesToKafka(List<Message> messages, String topico){
        List<IEventoInterno> eventoInternos = transformEventFromAWSSqsToEventoInterno(messages);
        for(IEventoInterno productoInterno: eventoInternos){
            send(topico, productoInterno);
        }
        return "Se han enviado " + eventoInternos.size() + " eventos desde AWSSqs hacia Kafka";
    }
}
