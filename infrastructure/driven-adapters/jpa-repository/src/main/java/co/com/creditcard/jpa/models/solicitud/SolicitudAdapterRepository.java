package co.com.creditcard.jpa.models.solicitud;

import co.com.creditcard.jpa.models.solicitud.SolicitudAdapter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SolicitudAdapterRepository extends CrudRepository<SolicitudAdapter, Long>, QueryByExampleExecutor<SolicitudAdapter> {
    @Query("SELECT s FROM SolicitudAdapter s WHERE s.usuario = :usuario")
    SolicitudAdapter findByUsuario(@Param("usuario") String usuario);
    @Query("SELECT s FROM SolicitudAdapter s WHERE s.estadosolicitud = :estadosolicitud")
    List<SolicitudAdapter> findByEstadoSolicitud(@Param("estadosolicitud") Integer estadosolicitud);
}
