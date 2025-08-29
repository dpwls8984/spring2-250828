package com.back;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    //@Transactional
    //트랜잭션 붙이고 컨트롤러에서 디버깅해보면 프록시 객체로 호출되는 것을 볼 수 있다. ($$SpringCGLIB$$)
    //트랜잭션 안붙이면 리얼 객체로 호출된다.
    public int count() {
        return personRepository.count();
    }
}
