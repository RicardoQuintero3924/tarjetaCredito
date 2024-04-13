package co.com.creditcard.jpa.models.usuarios;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Table(name = "usuario", schema = "app")
@ToString
@Entity
public class UsuariosAdapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombres;
    @Column
    private String apellidos;
    @Column
    private Integer tipo_documento;
    @Column
    private String documento;
    @Column
    private Integer registro_ingreso;
    @Column
    private boolean estado;
}
