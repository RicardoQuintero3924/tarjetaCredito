package co.com.creditcard.jpa.models.usuarios;

import co.com.creditcard.jpa.helper.AdapterOperations;
import co.com.creditcard.model.usuarios.Usuarios;
import co.com.creditcard.model.usuarios.gateways.UsuariosRepository;
import lombok.extern.java.Log;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;



@Repository
@Log
public class UsuarioAdapterRepositoryImpl extends AdapterOperations<Usuarios, UsuariosAdapter, Long, UsuariosAdapterRepository>
implements UsuariosRepository {


    protected UsuarioAdapterRepositoryImpl(@Lazy UsuariosAdapterRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Usuarios.class));
        this.repository = repository;
    }

    @Override
    public void save(Usuarios usuarios) {
        try {
            UsuariosAdapter usuariosAdapter = new UsuariosAdapter();
            usuariosAdapter.setNombres(usuarios.getNombres());
            usuariosAdapter.setApellidos(usuarios.getApellidos());
            usuariosAdapter.setTipo_documento(usuarios.getTipoDocumento());
            usuariosAdapter.setDocumento(usuarios.getDocumento());
            usuariosAdapter.setRegistro_ingreso(usuarios.getRegistro());
            usuariosAdapter.setEstado(usuarios.isEstado());

            repository.save(usuariosAdapter);
            log.warning("Usuario guardo correctamente");
        }catch (Exception ex){
            log.info("Error al guardar el registro:" + ex);
        }

    }
}
