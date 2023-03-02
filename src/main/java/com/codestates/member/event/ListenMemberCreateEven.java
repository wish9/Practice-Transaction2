package com.codestates.member.event;

import com.codestates.helper.EmailSender;
import com.codestates.member.entity.Member;
import com.codestates.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@EnableAsync
@Slf4j
@Component
public class ListenMemberCreateEven {
    private final MemberService memberService;
    private final EmailSender emailSender;


    @Async
    @EventListener
    public void listenEvent(Member member) {
        try {
            emailSender.sendEmail("any email message");
        } catch (Exception e) {
            log.error("MailSendException happened: ", e);
            memberService.deleteMember(member.getMemberId());
        }
    }

    public ListenMemberCreateEven(MemberService memberService, EmailSender emailSender) {
        this.memberService = memberService;
        this.emailSender = emailSender;
    }
}
