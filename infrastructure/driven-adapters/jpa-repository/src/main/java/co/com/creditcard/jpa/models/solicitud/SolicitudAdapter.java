package co.com.creditcard.jpa.models.solicitud;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
@Table(name = "solicitud", schema = "app")
@ToString
@Entity
public class SolicitudAdapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String usuario;
    @Column
    private Integer cupo;
    @Column
    private Integer ingresosmensuales;
    @Column
    private Integer cupodeseado;
    @Column
    private LocalDateTime fechasolicitud;
    @Column
    private String historialcrediticio;
    @Column
    private Integer estadosolicitud;
    @Column
    private String motivorechazo;
}
