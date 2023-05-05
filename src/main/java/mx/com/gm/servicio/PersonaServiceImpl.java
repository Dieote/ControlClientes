package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.dao.iPersonaDao;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //aqui inyectamos la implementacion
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private iPersonaDao personaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    @Transactional

    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)

    public Persona encontrarPersona(Persona persona) {
        return personaDao.findById(persona.getId_persona()).orElse(null); //si no encuentra muestra null
    }

}
