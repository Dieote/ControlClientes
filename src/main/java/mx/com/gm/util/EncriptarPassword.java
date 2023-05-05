package mx.com.gm.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarPassword {
    public static void main(String[] args) {
        
        String password = "123";
        System.out.println("Password = " + password);
                System.out.println("Password encriptado = " + encriptarPassword(password));

        //este flujo lo podemos colocar en la aplicacion para que al crear un usuario se encripte la contraseöa.
    }
    
    public static String encriptarPassword(String password){  //aqui utiliyamos bcript de spring para codificar la password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
