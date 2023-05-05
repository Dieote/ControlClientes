package mx.com.gm.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data; //genera nuestro codigo set-get-hash-toString

@Data
@Entity
@Table(name = "persona_spring1")//por si hay problemas con el nombre de la tabla de mysql
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //indicamos la llave id
    private Long id_persona;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    @Email
    private String email;

    private String telefono;
    
    @NotNull  //es para todos los tipos - STRING es solo para cadenas
    private Double saldo;

}
