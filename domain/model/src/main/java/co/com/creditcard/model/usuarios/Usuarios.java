package co.com.creditcard.model.usuarios;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Usuarios {
    private Integer id;
    private String nombres;
    private String apellidos;
    private Integer tipoDocumento;
    private String documento;
    private Integer registro;
    private boolean estado;
}
