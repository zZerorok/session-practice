package project.session.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.session.member.Member;
import project.session.member.MemberRepository;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }
}
