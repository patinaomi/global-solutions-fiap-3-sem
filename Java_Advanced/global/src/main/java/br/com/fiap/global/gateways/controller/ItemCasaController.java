package br.com.fiap.global.gateways.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/itenscasa", produces = "application/json")
@Tag(name = "itemcasa", description = "Operações relacionadas aos itens da casa")
public class ItemCasaController {
}
