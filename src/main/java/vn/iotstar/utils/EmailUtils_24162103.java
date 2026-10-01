package vn.iotstar.utils;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

/**
 * Gui OTP qua Gmail. KHONG hard-code mat khau trong code.
 * Cau hinh bang bien moi truong (hoac -D khi chay Tomcat):
 *   MAIL_USER = dia chi gmail
 *   MAIL_PASS = App Password 16 ky tu cua Google
 * Neu chua cau hinh, OTP se duoc in ra Console de ban van test duoc.
 */
public class EmailUtils_24162103 {

    private static String cfg(String key) {
        String v = System.getProperty(key);
        if (v == null || v.isBlank()) v = System.getenv(key);
        return (v == null || v.isBlank()) ? null : v.trim();
    }

    public static void sendOtp(String toEmail, String otpCode) {
        // Luon in ra console de test khi khong co mail
        System.out.println(">>> OTP gui toi " + toEmail + " = " + otpCode);

        final String fromEmail = cfg("MAIL_USER");
        final String password = cfg("MAIL_PASS");
        if (fromEmail == null || password == null) {
            System.out.println(">>> Chua cau hinh MAIL_USER / MAIL_PASS -> bo qua gui mail, dung OTP o tren.");
            return;
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
        	MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Mã OTP Xác Thực Đăng Ký", "UTF-8");
            message.setText("Mã OTP của bạn là: " + otpCode, "UTF-8");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
