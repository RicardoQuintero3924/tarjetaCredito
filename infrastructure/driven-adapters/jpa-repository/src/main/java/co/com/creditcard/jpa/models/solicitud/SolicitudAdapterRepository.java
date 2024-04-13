package co.com.creditcard.jpa.models.solicitud;

import co.com.creditcard.jpa.models.solicitud.SolicitudAdapter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;


public interface SolicitudAdapterRepository extends CrudRepository<SolicitudAdapter, Long>, QueryByExampleExecutor<SolicitudAdapter> {
}
