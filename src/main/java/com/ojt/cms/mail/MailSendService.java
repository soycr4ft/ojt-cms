package com.ojt.cms.mail;

public interface MailSendService {
	void sendHtmlEmail(MailHtmlSendDTO mailHtmlSendDTO) throws Exception;
}
