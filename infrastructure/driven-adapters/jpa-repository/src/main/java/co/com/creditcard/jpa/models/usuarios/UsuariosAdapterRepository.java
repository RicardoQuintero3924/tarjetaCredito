package co.com.creditcard.jpa.models.usuarios;

import co.com.creditcard.jpa.models.usuarios.UsuariosAdapter;
import co.com.creditcard.model.usuarios.gateways.UsuariosRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;


public interface UsuariosAdapterRepository extends CrudRepository<UsuariosAdapter,Long>, QueryByExampleExecutor<UsuariosAdapter> {
}
