package other;

import javafx.scene.control.Alert;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailSender {

    private static final String PROPERTIES_FILE = "/email.properties";

    public static void sendEmail(String to, String subject, String body) {
        Properties emailProperties = loadProperties();

        Session session = Session.getInstance(emailProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        emailProperties.getProperty("email.username"),
                        emailProperties.getProperty("email.password")
                );
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(emailProperties.getProperty("email.username")));//reciver address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));//sender address
            message.setSubject(subject);
            message.setText(body);//email body

            Transport.send(message);

            new Alert(Alert.AlertType.INFORMATION, "Please check your email").show();

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email.");
        }
    }


    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = EmailSender.class.getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + PROPERTIES_FILE);
                return properties;
            }

            properties.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
