package co.com.creditcard.usecase.solicitud;

import co.com.creditcard.model.solicitud.Solicitud;
import co.com.creditcard.model.solicitud.gateways.SolicitudRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class SolicitudUseCase {
    private final SolicitudRepository repository;

    public void registrarSolicitud(String usuario, Integer cupo, Integer ingresos, Integer cupod, String historial){
        LocalDateTime fechaS = LocalDateTime.now();
        Integer estado = 0;
        String motivo = "en proceso";
        Solicitud solicitud = Solicitud.builder()
                .usuario(usuario)
                .cupo(cupo)
                .ingresosMensuales(ingresos)
                .cupoDeseado(cupod)
                .fechaSolicitud(fechaS)
                .historialCrediticio(historial)
                .estadoSolicitud(estado)
                .motivoRechazo(motivo)
                .build();
        repository.save(solicitud);
    }

}
