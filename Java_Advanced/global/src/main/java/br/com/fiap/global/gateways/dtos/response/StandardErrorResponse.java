package br.com.fiap.global.gateways.dtos.response;

public record StandardErrorResponse(Integer status, String msg, Long timeStamp) {
}
