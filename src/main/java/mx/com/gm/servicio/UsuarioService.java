package mx.com.gm.servicio;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.iUsuarioDao;
import mx.com.gm.domain.Rol;
import mx.com.gm.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService") //nombre reservado de springsecurity
@Slf4j
public class UsuarioService implements UserDetailsService{
    
    @Autowired
    private iUsuarioDao usuarioDao;
    
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// loaduser va a obtener un objeto usuario filtrado por username
        Usuario usuario = usuarioDao.findByUsername(username);
        
        if (usuario == null){
            throw new UsernameNotFoundException (username);
        }
        
       List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        
        for (Rol rol: usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        
    return new User(usuario.getUsername(), usuario.getPassword(), roles);  //esta es una clase de spring
    
}
} 
//18.40 