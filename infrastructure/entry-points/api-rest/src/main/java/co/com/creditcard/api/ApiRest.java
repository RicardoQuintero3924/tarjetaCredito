package co.com.creditcard.api;
import co.com.creditcard.api.http.UsuarioForm;
import co.com.creditcard.model.solicitud.Solicitud;
import co.com.creditcard.model.solicitud.gateways.SolicitudRepository;
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
import java.util.List;

@Controller
@RequestMapping(value = "/app", produces = "text/html")
@AllArgsConstructor
@ControllerAdvice
public class ApiRest {
  private final UserUseCase useCase;
  private final SolicitudUseCase solicitudUseCase;
  private final SolicitudRepository solicitudRepository;


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
    @GetMapping("/solicitud")
    public String mostrarFormularioSolicitud(){
        return "application";
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
            return "FollowUpCreditCard";
        }catch(Exception ex){
            model.addAttribute("error","Error al procesar la Solicitud");
            return "error";
        }
    }
    @GetMapping("/consultaSolicitud")
    public String mostrarFormularioConsulta(){
        return "FollowUpCreditCard";
    }
    @PostMapping("/consultaSolicitud")
    public String consultarEstadoSolicitud(@RequestParam String usuario, Model model){
        Solicitud solicitud = solicitudRepository.findByUsuario(usuario);
        System.out.println("esta es la solicitud: "+ solicitud);
        if(solicitud.getEstadoSolicitud() == null){
            solicitud.setEstadoSolicitud(0);

        }
        if(solicitud != null){
            model.addAttribute("solicitud", solicitud);
        }else{
            model.addAttribute("error","No se encontró ninguna solicitud con el número proporcionado.");
        }
        return "FollowUpCreditCard";
    }

    @GetMapping("/panel")
    public String mostrarPanelAdministrativo(Model model){
        Integer estado = 0;
        List<Solicitud> solicitudesPendientes = solicitudRepository.findByEstadoSolicitud(estado);
        model.addAttribute("solicitudesPendientes", solicitudesPendientes);
        return "administrationPanel";
    }
    @PostMapping("/aprobar-solicitud")
    public String aprobarSolicitud(@RequestParam Long solicitudId) {
        Solicitud solicitud = solicitudRepository.findById(solicitudId).orElse(null);
        if (solicitud != null) {
            solicitud.setEstadoSolicitud(1); // Cambiar el estado a aprobado
            solicitudRepository.save(solicitud);
        }
        return "redirect:/panel"; // Redirigir a la página de administración
    }

    @PostMapping("/rechazar-solicitud")
    public String rechazarSolicitud(@RequestParam Long solicitudId) {
        Solicitud solicitud = solicitudRepository.findById(solicitudId).orElse(null);
        if (solicitud != null) {
            solicitud.setEstadoSolicitud(2); // Cambiar el estado a rechazado
            solicitudRepository.save(solicitud);
        }
        return "redirect:/panel"; // Redirigir a la página de administración
    }




}
