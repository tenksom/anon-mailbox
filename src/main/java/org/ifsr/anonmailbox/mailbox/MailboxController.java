package org.ifsr.anonmailbox.mailbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MailboxController {
    @Value("${spring.mail.password}")
    String password;
    @Value("${spring.mail.username}")
    String username;

    @GetMapping("/")
    public String index() {
        return "redirect:/mailbox";
    }

    @GetMapping("/mailbox")
    public String mailbox(MailboxForm form) {
        return "mailbox";
    }

    @PostMapping("/mailbox")
    public String mailboxPost(MailboxForm form) {
        MailLogic logic = new MailLogic(username, password);
        logic.sendSimpleMessage(form.getEmail(), form.getMessage());
        return "success";
    }

}
