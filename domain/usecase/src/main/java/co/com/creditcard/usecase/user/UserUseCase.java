package co.com.creditcard.usecase.user;

import co.com.creditcard.model.usuarios.Usuarios;
import co.com.creditcard.model.usuarios.gateways.UsuariosRepository;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class UserUseCase {
    private final UsuariosRepository repository;
    public void registrarUsuario(String nombre, String apellidos,Integer tipo_documento, String documento){
        int registroIngreso = 1;
        boolean estado = true;
        Usuarios usuarios = Usuarios.builder()
                .nombres(nombre)
                .apellidos(apellidos)
                .tipoDocumento(tipo_documento)
                .documento(documento)
                .registro(registroIngreso)
                .estado(estado)
                .build();
        System.out.println("usuarios antes de guardar: "+usuarios);
        repository.save(usuarios);
    }
}
