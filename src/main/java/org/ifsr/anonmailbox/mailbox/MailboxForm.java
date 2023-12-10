package org.ifsr.anonmailbox.mailbox;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MailboxForm {
        private String email;
        private String message;
}
