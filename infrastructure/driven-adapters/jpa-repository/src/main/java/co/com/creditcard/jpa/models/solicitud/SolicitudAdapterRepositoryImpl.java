package co.com.creditcard.jpa.models.solicitud;

import co.com.creditcard.jpa.helper.AdapterOperations;
import co.com.creditcard.model.solicitud.Solicitud;
import co.com.creditcard.model.solicitud.gateways.SolicitudRepository;
import lombok.extern.java.Log;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

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
            solicitudAdapter.setIngresosmensuales(solicitud.getIngresosMensuales());
            solicitudAdapter.setCupodeseado(solicitud.getCupoDeseado());
            solicitudAdapter.setFechasolicitud(solicitud.getFechaSolicitud());
            solicitudAdapter.setHistorialcrediticio(solicitud.getHistorialCrediticio());
            solicitudAdapter.setMotivorechazo(solicitud.getMotivoRechazo());
            repository.save(solicitudAdapter);
            log.info("Silicitud Creada Correctamente");
        }catch (Exception ex){
            log.info("Error al guardar el registro:" + ex);
        }
    }

    @Override
    public Solicitud findByUsuario(String usuario) {
        SolicitudAdapter solicitudAdapter = repository.findByUsuario(usuario);
        Solicitud solicitud = Solicitud.builder()
                .usuario(solicitudAdapter.getUsuario())
                .cupo(solicitudAdapter.getCupo())
                .cupoDeseado(solicitudAdapter.getCupodeseado())
                .estadoSolicitud(solicitudAdapter.getEstadosolicitud())
                .fechaSolicitud(solicitudAdapter.getFechasolicitud())
                .historialCrediticio(solicitudAdapter.getHistorialcrediticio())
                .ingresosMensuales(solicitudAdapter.getIngresosmensuales())
                .motivoRechazo(solicitudAdapter.getMotivorechazo())
                .build();

        return solicitud;
    }

    public List<Solicitud> findByEstadoSolicitud(Integer estado) {
        List<SolicitudAdapter> solicitudesAdapters = repository.findByEstadoSolicitud(estado);
        System.out.println("Resultados de la consulta:");
        for (SolicitudAdapter solicitudAdapter : solicitudesAdapters) {
            System.out.println("Número de Solicitud: " + solicitudAdapter.getUsuario());
            System.out.println("Estado: " + solicitudAdapter.getEstadosolicitud());
            System.out.println("cupo Solicitado: "+ solicitudAdapter.getCupodeseado());
            System.out.println("fecha: "+solicitudAdapter.getFechasolicitud());
            System.out.println("historial: "+solicitudAdapter.getHistorialcrediticio());
            // Imprime otros campos según sea necesario
        }
        return solicitudesAdapters.stream()
                .map(this::mapToSolicitud)
                .collect(Collectors.toList());
    }
    private Solicitud mapToSolicitud(SolicitudAdapter solicitudAdapter) {
        Solicitud solicitud = new Solicitud();
        solicitud.setUsuario(solicitudAdapter.getUsuario());
        solicitud.setCupoDeseado(solicitudAdapter.getCupodeseado());
        solicitud.setEstadoSolicitud(solicitudAdapter.getEstadosolicitud());
        solicitud.setFechaSolicitud(solicitudAdapter.getFechasolicitud());
        solicitud.setHistorialCrediticio(solicitudAdapter.getHistorialcrediticio());
        solicitud.setIngresosMensuales(solicitudAdapter.getIngresosmensuales());
        return solicitud;
    }
}
