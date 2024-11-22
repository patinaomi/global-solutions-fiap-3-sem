package br.com.fiap.global.gateways.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidateUserResponse {

        String message;
        Integer id;
}
