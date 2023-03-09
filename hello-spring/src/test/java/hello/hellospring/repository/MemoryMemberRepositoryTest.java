package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();


    @AfterEach //한 번의 test가 끝난 뒤 repository를 깨끗하게 지워주는 역할
    public void afterEach(){
        repository.clearStore();
    }

    @Test //저장 잘 되는지 확인하는 test
    public void save(){
        //given
        Member member = new Member();
        member.setName("spring");

        repository.save(member); //spring이라는 member 저장

        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member); // 결과가 member랑 똑같은지 확인

    }

    @Test //find확인 test
    public void findByname(){

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);


        Member result = repository.findByName("spring1").get(); //run 성공
        //Member result = repository.findByName("spring2").get(); // 에러발생

        assertThat(result).isEqualTo(member1);

    }

    @Test

    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);


        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }


}
