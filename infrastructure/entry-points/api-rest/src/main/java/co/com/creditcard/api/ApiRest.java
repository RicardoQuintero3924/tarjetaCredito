package co.com.creditcard.api;
import co.com.creditcard.api.http.UsuarioForm;
import co.com.creditcard.usecase.solicitud.SolicitudUseCase;
import co.com.creditcard.usecase.user.UserUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequestMapping(value = "/app", produces = "text/html")
@AllArgsConstructor
@ControllerAdvice
public class ApiRest {
  private final UserUseCase useCase;
  private final SolicitudUseCase solicitudUseCase;


    @GetMapping(path = "/registro")
    public String mostrarFormularioRegistro(){
        return "bienvenida";
    }


    @PostMapping("/registro")
    public String procesarFormularioRegistro(@RequestParam String nombres,
                                             @RequestParam String apellidos,
                                             @RequestParam String tipo_documento,
                                             @RequestParam String documento,
                                             @RequestParam String correo,
                                             Model model) {
        try {
            String correo1 = correo;
            Integer tipo = Integer.valueOf(tipo_documento);
            useCase.registrarUsuario(nombres, apellidos, tipo, documento);
            model.addAttribute("mensaje", "Usuario registrado correctamente");
            return "applicationCreditCard"; // Devuelve el nombre de la plantilla Thymeleaf
        } catch (Exception ex) {
            model.addAttribute("error", "Error al procesar la solicitud");
            return "error";
        }
    }

    @PostMapping("/solicitud")
    public String procesarFormularioSolicitud(@RequestParam String usuario,
                                              @RequestParam String cupo,
                                              @RequestParam String ingresos,
                                              @RequestParam String cupoDeseado,
                                              @RequestParam String historialCrediticio,
                                              Model model){
        try{
            Integer cupo1 = Integer.valueOf(cupo);
            Integer ingresos1 = Integer.valueOf(ingresos);
            Integer cupoD = Integer.valueOf(cupoDeseado);

            solicitudUseCase.registrarSolicitud(usuario,cupo1, ingresos1, cupoD, historialCrediticio);
            model.addAttribute("mensaje", "Usuario registrado correctamente");
            return "application";
        }catch(Exception ex){
            model.addAttribute("error","Error al procesar la Solicitud");
            return "error";
        }
    }






}
