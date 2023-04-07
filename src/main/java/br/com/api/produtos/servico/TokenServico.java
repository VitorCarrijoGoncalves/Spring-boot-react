package br.com.api.produtos.servico;

import java.util.Date;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.api.produtos.modelo.AuthenticationModelo;

@Service
public class TokenServico {

    public String gerarToken(AuthenticationModelo auth) {
        return JWT.create()
        .withIssuer("Produtos")
        .withSubject(auth.getUsername())
        .withClaim("id", auth.getId())
        .withExpiresAt(new Date(System.currentTimeMillis() + 300000)
        ).sign(Algorithm.HMAC256("secreta"));
    }

}