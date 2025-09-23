package com.ojt.cms.mail;

import lombok.Getter;

@Getter
public class MailHtmlSendDTO {

    private String emailAddr;                   // 수신자 이메일

    private String subject;                     // 이메일 제목

    private String content;                     // 이메일 내용

    private String target;                      // 이메일 대상 타겟을 지정합니다.

    public MailHtmlSendDTO(String emailAddr, String subject, String content, String target) {
        this.emailAddr = emailAddr;
        this.subject = subject;
        this.content = content;
        this.target = target;
    }
}
