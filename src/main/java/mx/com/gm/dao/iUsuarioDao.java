package mx.com.gm.dao;

import mx.com.gm.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iUsuarioDao extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username); 
//findByUsername es una palabra reservada de seguridad de spring
    //lo usa en automatico
}
