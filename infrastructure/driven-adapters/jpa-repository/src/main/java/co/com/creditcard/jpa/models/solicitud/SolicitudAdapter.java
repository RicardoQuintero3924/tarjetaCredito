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
    private String Usuario;
    @Column
    private Integer cupo;
    @Column
    private Integer ingresosMensuales;
    @Column
    private Integer cupoDeseado;
    @Column
    private LocalDateTime fechaSolicitud;
    @Column
    private String historialCrediticio;
    @Column
    private Integer estadoSolicitud;
    @Column
    private String motivoRechazo;
}
