package com.ojt.cms.mail;

import java.io.IOException;
import java.util.Base64;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

//이메일 전송에 대한 인터페이스의 구현체로 javamailsender를 주입받아서 메일을 전송하는 구조
@Service
@RequiredArgsConstructor
public class MailSendServiceImpl implements MailSendService {
	private final JavaMailSender mailSender;
	private final TemplateEngine templateEngine;
	
	@Override
	public void sendHtmlEmail(MailHtmlSendDTO dto) throws Exception {
		// TODO Auto-generated method stub
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        
        Context context = new Context();
        context.setVariable("subject", dto.getSubject());
        context.setVariable("message", dto.getContent());
        context.setVariable("target", dto.getTarget());

        //보낸 타임리프 템플릿 렌더링하기
        String htmlContent = templateEngine.process("mail/joinemail", context);

        
        // MailSendServiceImpl.java 내부
        //String base64Image = getBase64EncodedImage("static/images/logo.png");
        //context.setVariable("logoImage", base64Image);
        //helper.addInline("logo", new ClassPathResource("static/images/logo.png"));

        helper.setTo(dto.getEmailAddr());
        helper.setSubject(dto.getSubject());
        helper.setText(htmlContent, true);
        mailSender.send(message);
        System.out.println("master에게 회원가입 승인 요청 메일 발송 성공: " + dto.getEmailAddr());
    
	}
	
    // 이미지를 Base64로 인코딩하는 메서드
//    private String getBase64EncodedImage(String imagePath) throws IOException {
//        Resource resource = new ClassPathResource(imagePath);
//        byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
//        return Base64.getEncoder().encodeToString(bytes);
//    }

}
