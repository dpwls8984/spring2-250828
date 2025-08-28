package com.back;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class PersonController {
    private PersonService personService = new PersonService();

    @GetMapping("/people")
    @ResponseBody
    public String people() {
        return "사람 수 : %d".formatted(personService.count());
    }
}
