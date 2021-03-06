package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyController {

    // DummyController가 메모리에 오를 때 자동으로 같이 메모리에 띄워준다. -> 의존성 주입 (DI)
    @Autowired
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String deleteUser(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
        }

        return "삭제 되었습니다. id: " + id;
    }

    // @Transactional 을 사용하면 함수 종료 시 자동 commit 된다.
    // 같은 path 라도 get,put 이 다르므로 상관없다.
    // email,password 만 수정 가능
    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {

        // 이 때, 영속화가 된다.
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        /*
        - save 함수는
        1. id를 전달하지 않으면 insert
        2. id에 대한 데이터가 있으면 update
        3. id에 대한 데이터가 없으면 insert

        userRepository.save(user);
         */

        // 함수가 끝나면서 자동으로 commit 되고, 변경을 감지해서 (더티 체킹) 1차 캐시에서 flush 까지 이루어진다.
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // ?page=x 를 통해 pagination 가능
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();

        return users;
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // 없는 유저를 찾으면 null이 리턴되어 오류가 생길 수 있다.
        // Optional을 사용하여 User 객체를 감싸서 가져오니 null 인지 아닌지 판단.
        // orElseGet에 들어갈 수 있는 객체는 Supplier (interface 이고 get 함수를 갖고 있다.)
        // illegalArgumentException을 사용하면 null을 리턴하지 않고 오류 메세지를 보낼 수 있다.

        /*
        람다식 사용 버전
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 유저는 없습니다. id: " + id);
        });
         */

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id: " + id);
            }
        });


        /*
        두 번째 방법.

        orElseGet(new Supplier<User>() {
            @Override
            public User get() {
                return new User();
            }
        });
        */

        // 리턴을 해줄 때, 웹브라우저가 이해 할 수 있는 데이터 구조로 리턴을 해줘야한다. -> json
        // 자바에서는 주로 GSON 라이브러리를 사용할탠데,
        // 스프링부트에서는 MessageConverter 가 자동으로 Jackson 을 호출하여 자바 객체를 json 으로 변환.

        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user) {

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }
}
