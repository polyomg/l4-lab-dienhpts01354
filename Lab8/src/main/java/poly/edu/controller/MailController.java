package poly.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.mail.MessagingException;  // ✅ Thêm import này
import poly.edu.service.MailService;

@Controller
public class MailController {

    @Autowired
    MailService mailService;

    // ✅ Gửi mail (xếp vào hàng đợi)
    @ResponseBody
    @RequestMapping("/mail/send")
    public String send() {
        try {
            // Đặt email người nhận, tiêu đề, và nội dung tại đây
            mailService.push("dienhuynh1209@gmail.com", "Chào từ Sơn", "Đây là email test từ Lab8 – Schedule Tasks.");
            return "✅ Mail của bạn đã được xếp vào hàng đợi!";
        } catch (Exception e) {
            return "❌ Gửi mail thất bại: " + e.getMessage();
        }
    }
}
