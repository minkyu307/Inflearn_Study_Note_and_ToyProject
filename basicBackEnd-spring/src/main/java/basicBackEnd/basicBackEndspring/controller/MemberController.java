package basicBackEnd.basicBackEndspring.controller;

import basicBackEnd.basicBackEndspring.domain.Member;
import basicBackEnd.basicBackEndspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller//컨트롤러 어노테이션이 붙어있으면 실행할때 스프링 컨테이너에 컨트롤러 객체를 생성한다.
public class MemberController {
    private final MemberService memberService;

    /*컨트롤러객체가 스프링 컨테이너에 생성될때 생성자가 오토와이어드 어노테이션을 가지고있으면
    스프링 컨테이너에 있는 인스턴스와 연결시켜준다
    이때 스프링 컨테이너에 미리 인스턴스가 등록되어있어야하는데 이것을 스프링 빈으로
    미리 등록시킬수 있다
    이러한 방식을 컴포턴트 스캔이라 한다. */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
