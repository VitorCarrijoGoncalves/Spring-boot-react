package br.com.api.produtos.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.api.produtos.modelo.AuthenticationModelo;

@Repository
public interface AuthenticationRepositorio extends CrudRepository<AuthenticationModelo, Long>  {

    //@Query(value = "SELECT * FROM authentication WHERE username = ?1", nativeQuery = true)
    AuthenticationModelo findByUsername(String username);
    
}
