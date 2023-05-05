package mx.com.gm.dao;

import mx.com.gm.domain.Persona;
import org.springframework.data.repository.CrudRepository;


public interface iPersonaDao extends CrudRepository<Persona,Long>{ //crudrepository tiene los metodos  
    
}
