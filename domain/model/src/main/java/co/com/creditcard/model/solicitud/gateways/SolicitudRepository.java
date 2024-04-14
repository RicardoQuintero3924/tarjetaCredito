package co.com.creditcard.model.solicitud.gateways;

import co.com.creditcard.model.solicitud.Solicitud;

import java.util.List;

public interface SolicitudRepository {
    void save(Solicitud solicitud);

    Solicitud findByUsuario(String usuario);

    List<Solicitud> findByEstadoSolicitud(Integer estado);
}
