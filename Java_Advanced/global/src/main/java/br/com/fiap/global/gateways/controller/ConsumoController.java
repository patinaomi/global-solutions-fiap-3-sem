package br.com.fiap.global.gateways.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/consumos", produces = "application/json")
@Tag(name = "consumo", description = "Operações relacionadas aos consumos")
public class ConsumoController {
}
