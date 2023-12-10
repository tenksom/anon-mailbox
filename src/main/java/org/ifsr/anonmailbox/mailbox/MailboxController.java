package org.ifsr.anonmailbox.mailbox;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MailboxController {

    public MailboxController() {
    }

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
        System.out.println(form.getMessage());
        return "success";
    }

}
