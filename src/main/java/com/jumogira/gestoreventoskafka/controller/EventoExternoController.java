package com.jumogira.gestoreventoskafka.controller;

import com.amazonaws.services.sqs.model.Message;
import com.jumogira.gestoreventoskafka.models.EventoExterno;
import com.jumogira.gestoreventoskafka.models.Topicos;
import com.jumogira.gestoreventoskafka.services.EventoExternoKafkaService;
import com.jumogira.gestoreventoskafka.services.EventoSQSService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/ext/productos")
@RestController
@AllArgsConstructor
public class EventoExternoController {

    private EventoExternoKafkaService eventoExternoKafkaService;
    private EventoSQSService eventoSQSService;

    @PostMapping("/")
    public EventoExterno enviarEventoExternoKafka(@RequestBody EventoExterno eventoExterno){
        eventoExternoKafkaService.send(String.valueOf(Topicos.EVENTO_EXTERNOS_2023_11), eventoExterno);
        return eventoExterno;
    }

    @PostMapping("/sqs")
    public String enviarEventoDesdeSQSHaciaKafka(){
        List<Message> awsSqsMessages = eventoSQSService.receiveMessagesFromQueue("eventos", 10, 10);
        return eventoExternoKafkaService.sendAWSSqsListMessagesToKafka(awsSqsMessages, String.valueOf(Topicos.EVENTO_SQS_2023_11));
    }
}
