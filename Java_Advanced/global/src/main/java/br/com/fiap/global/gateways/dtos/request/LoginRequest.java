package br.com.fiap.global.gateways.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

        private String email;
        private String senha;
        private String id;
}
