package thinh.manager.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    private final String url = "http://localhost:8080/verify-email/";

    public void sendEmail(String to , String subject, String description){
       try {
           SimpleMailMessage message = new SimpleMailMessage();
           message.setTo(to);
           message.setSubject(subject);
           message.setText(description);
           mailSender.send(message);
       } catch (Exception exception){
           log.error("Send mail fail");
           log.error(exception.getMessage());
           exception.printStackTrace();
       }
    }

    public void sendEmailVerifyEmailRegister(String to,String name,String idUser){
        String subject = "Xác thực tài khoản đăng ký";
        StringBuilder urlVerify = new StringBuilder();
        urlVerify.append(url+idUser);
        StringBuilder description = new StringBuilder();
        description.append("Chào "+name+" ,");
        description.append("Đây là email xác nhận tài khoản đăng ký Quản lý trung tâm , vui lòng nhấn vào đường link sau để xác nhận tài khoản ");
        description.append(urlVerify);

        sendEmail(to,subject,description.toString());
    }
}
