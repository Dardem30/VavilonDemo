package com.vavilon.demo.service;

import com.vavilon.demo.bo.user.AppUser;
import com.vavilon.demo.bo.PasswordResetToken;
import com.vavilon.demo.bo.bean.MailInfo;
import com.vavilon.demo.bo.bean.Recipient;
import com.vavilon.demo.bo.enums.RecipientType;
import com.vavilon.demo.util.ContextHolder;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

@Service
@AllArgsConstructor
public class EmailService {
    protected final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    private final MessageSource messageSource;
    private final ContextHolder contextHolder;

    private void prepareAndSendMail(final MailInfo mailInfo, final String[] attachmentPaths, final Consumer<VelocityContext> fillVarsFunc) throws Exception {
        final String templatePath = mailInfo.getTemplate();
        try {
            final Template template = Velocity.getTemplate(templatePath);
            final VelocityContext context = new VelocityContext();
            fillVarsFunc.accept(context);
            try (final StringWriter writer = new StringWriter()) {
                template.merge(context, writer);
                if (attachmentPaths != null) {
                    final List<MimeBodyPart> attachments = new ArrayList<>(attachmentPaths.length);
                    for (final String attachmentPath : attachmentPaths) {
                        final MimeBodyPart attachment = new MimeBodyPart();
                        final FileDataSource source = new FileDataSource(attachmentPath);
                        attachment.setDataHandler(new DataHandler(source));
                        attachment.setFileName(source.getFile().getName());
                        attachments.add(attachment);
                    }
                    sendMail(mailInfo, writer, attachments);
                } else {
                    sendMail(mailInfo, writer, null);
                }
            }
        } catch (final Exception e) {
            logger.error("Couldn't send mail [Template: " + mailInfo.getTemplate() + "]", e);
            throw e;
        }
    }
    private void sendMail(final MailInfo mailInfo, final StringWriter writer, final List<MimeBodyPart> attachments) throws Exception {
        final String from = mailInfo.getAddressFrom();
        final String nameFrom = mailInfo.getNameFrom();
        final MimeMessage message = mailSender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(message, true);
        final InternetAddress addressFrom = new InternetAddress(from, nameFrom);
        final List<InternetAddress> to = new ArrayList<>();
        final List<InternetAddress> cc = new ArrayList<>();
        final List<InternetAddress> bcc = new ArrayList<>();
        for (final Recipient receiver : mailInfo.getRecipients()) {
            if (StringUtils.isEmpty(receiver.getEmail())) {
                continue;
            }
            final InternetAddress address = convertRecipientToInternetAddress(receiver);
            if (RecipientType.RECIPIENT.equals(receiver.getType())) {
                to.add(address);
            } else if (RecipientType.CC.equals(receiver.getType())) {
                cc.add(address);
            } else if (RecipientType.BCC.equals(receiver.getType())) {
                bcc.add(address);
            }
        }

        helper.setTo(to.toArray(new InternetAddress[to.size()]));
        helper.setCc(cc.toArray(new InternetAddress[cc.size()]));
        helper.setBcc(bcc.toArray(new InternetAddress[bcc.size()]));

        final BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(writer.toString(), "text/html");
        final Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        if (attachments != null) {
            for (final MimeBodyPart attachment: attachments) {
                multipart.addBodyPart(attachment);
            }
        }
        message.setSubject(mailInfo.getSubject());
        message.setContent(multipart);
        message.setFrom(addressFrom);

        logger.info("The e-mail message[" + mailInfo.getTemplate() + "] has been sent");
        mailSender.send(message);
    }
    private InternetAddress convertRecipientToInternetAddress(final Recipient recipient) throws Exception {
        final InternetAddress address = new InternetAddress(recipient.getEmail());
        if (recipient.getName() != null) {
            address.setPersonal(recipient.getName());
        }
        return address;
    }
    public void sendActivationCode(final AppUser appUser, final Locale locale) throws Exception {
        final MailInfo mailInfo = new MailInfo();
        mailInfo.setTemplate(MailInfo.ACTIVATION_CODE_MAIL_TEMPLATE);
        mailInfo.setSubject(messageSource.getMessage("mail.activation_code_subject", null, locale));
        mailInfo.setAddressFrom(contextHolder.getMailFrom());

        final Recipient recipient = appUser.toRecipient();
        recipient.setType(RecipientType.RECIPIENT);
        mailInfo.setRecipients(Collections.singletonList(recipient));
        prepareAndSendMail(mailInfo, null, context -> {
            context.put("code", appUser.getActivationCode());
            context.put("url", contextHolder.getActivateUserUrl());
            context.put("userId", appUser.getUserId());
        });
    }

    public void sendResetPasswordMail(final PasswordResetToken passwordResetTokenEntry, final Locale locale) throws Exception {
        final MailInfo mailInfo = new MailInfo();
        mailInfo.setTemplate(MailInfo.RESTORE_PASSWORD_MAIL_TEMPLATE);
        mailInfo.setSubject(messageSource.getMessage("mail.reset_password_subject", null, locale));
        mailInfo.setAddressFrom(contextHolder.getMailFrom());

        final Recipient recipient = passwordResetTokenEntry.getUser().toRecipient();
        recipient.setType(RecipientType.RECIPIENT);
        mailInfo.setRecipients(Collections.singletonList(recipient));
        prepareAndSendMail(mailInfo, null, context -> {
            context.put("url", passwordResetTokenEntry.getUrl());
            context.put("token", passwordResetTokenEntry.getToken());
            context.put("userId", passwordResetTokenEntry.getUser().getUserId());
        });
    }
}
