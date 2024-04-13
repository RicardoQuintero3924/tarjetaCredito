package co.com.creditcard.model.solicitud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Solicitud {
    private Integer id;
    private String usuario;
    private Integer cupo;
    private Integer ingresosMensuales;
    private Integer cupoDeseado;
    private LocalDateTime fechaSolicitud;
    private String historialCrediticio;
    private Integer estadoSolicitud;
    private String motivoRechazo;
}
