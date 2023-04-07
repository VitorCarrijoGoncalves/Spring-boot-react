package br.com.api.produtos.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.produtos.config.JwtAuthenticationRequest;
import br.com.api.produtos.modelo.AuthenticationModelo;
import br.com.api.produtos.servico.TokenServico;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationControle {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenServico tokenServico;
    
    @PostMapping("/authenticate")
    public String login(@RequestBody JwtAuthenticationRequest jwt) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
        new UsernamePasswordAuthenticationToken(jwt.username(), jwt.password());

        Authentication authentication = this.authenticationManager
        .authenticate(usernamePasswordAuthenticationToken);

        var auth = (AuthenticationModelo) authentication.getPrincipal();

        return tokenServico.gerarToken(auth);
    }

}
