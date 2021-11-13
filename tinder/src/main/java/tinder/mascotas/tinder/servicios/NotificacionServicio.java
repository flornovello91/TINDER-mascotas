//package tinder.mascotas.tinder.servicios;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//@Service 
//public class NotificacionServicio {
//    
//    @Autowired
//    private JavaMailSender mailSender;
//    
//    
//    //Al tener el atributo ASYNC lo que pasa es que el hilo de ejecucion no espera a que se termine de enviar el mail
//    //sino que lo ejecuta en un hilo paralelo , por eso el usuario tiene respuesta inmediata, no tiene que esperar
//    //a que se termine de enviar el mail
//    
//    @Async
//    public void enviarMail (String cuerpo,String titulo,String mail){
//        
//        SimpleMailMessage mensaje = new SimpleMailMessage();
//        mensaje.setTo(mail);
//        mensaje.setFrom("noreply@tinder-mascota.com");
//        mensaje.setSubject(titulo);
//        mensaje.setText(cuerpo);
//        
//        mailSender.send(mensaje);
//    }
//}
