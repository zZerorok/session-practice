package project.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import project.session.member.Member;
import project.session.member.MemberRepository;
import project.session.session.SessionManager;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    //    @GetMapping("/")
    public String home() {
        return "home";
    }

    //    @GetMapping("/")
    public String homeLogin(
            @CookieValue(name = "memberId", required = false) Long memberId,
            Model model
    ) {
        if (memberId == null) {
            return "home";
        }

        Member loginMember = memberRepository.findById(memberId);
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "login-home";
    }

//    @GetMapping("/")
    public String homeLoginV2(
            HttpServletRequest request,
            Model model
    ) {
        Member member = (Member) sessionManager.getSession(request);
        if (member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "login-home";
    }

    @GetMapping("/")
    public String homeLoginV3(
            HttpServletRequest request,
            Model model
    ) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }

        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "login-home";
    }
}
