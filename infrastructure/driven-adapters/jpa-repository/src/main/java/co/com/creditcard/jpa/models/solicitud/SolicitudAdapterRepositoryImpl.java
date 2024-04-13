package co.com.creditcard.jpa.models.solicitud;

import co.com.creditcard.jpa.helper.AdapterOperations;
import co.com.creditcard.model.solicitud.Solicitud;
import co.com.creditcard.model.solicitud.gateways.SolicitudRepository;
import lombok.extern.java.Log;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
@Log
public class SolicitudAdapterRepositoryImpl extends AdapterOperations<Solicitud, SolicitudAdapter, Long, SolicitudAdapterRepository>
implements SolicitudRepository {


    protected SolicitudAdapterRepositoryImpl(@Lazy SolicitudAdapterRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Solicitud.class));
        this.repository = repository;
    }

    @Override
    public void save(Solicitud solicitud) {
        try{
            SolicitudAdapter solicitudAdapter = new SolicitudAdapter();
            solicitudAdapter.setUsuario(solicitud.getUsuario());
            solicitudAdapter.setCupo(solicitud.getCupo());
            solicitudAdapter.setIngresosMensuales(solicitud.getIngresosMensuales());
            solicitudAdapter.setCupoDeseado(solicitud.getCupoDeseado());
            solicitudAdapter.setFechaSolicitud(solicitud.getFechaSolicitud());
            solicitudAdapter.setHistorialCrediticio(solicitud.getHistorialCrediticio());
            solicitudAdapter.setMotivoRechazo(solicitud.getMotivoRechazo());
            repository.save(solicitudAdapter);
            log.info("Silicitud Creada Correctamente");
        }catch (Exception ex){
            log.info("Error al guardar el registro:" + ex);
        }
    }
}
