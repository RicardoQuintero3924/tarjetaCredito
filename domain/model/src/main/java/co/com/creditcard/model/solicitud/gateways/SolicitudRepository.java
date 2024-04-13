package co.com.creditcard.model.solicitud.gateways;

import co.com.creditcard.model.solicitud.Solicitud;

public interface SolicitudRepository {
    void save(Solicitud solicitud);
}
