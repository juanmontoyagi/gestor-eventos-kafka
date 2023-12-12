package com.jumogira.gestoreventoskafka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jumogira.gestoreventoskafka.models.Topicos;

@RequestMapping("/ext/topicos")
@RestController
public class TopicosController {

    @GetMapping("")
    public Topicos[] enviarProductoExternoKafka(){
        return Topicos.values();
    }
}
