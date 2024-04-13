package co.com.creditcard.api.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioForm {
    private String nombres;
    private String apellidos;
    private Integer tipoDocumento;
    private String documento;
}
