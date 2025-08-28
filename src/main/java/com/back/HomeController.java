package com.back;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class HomeController {
    private PersonService personService = new PersonService(); //공용객체

    @GetMapping("/home")
    @ResponseBody
    public String home() {
        return "사람 수 : %d".formatted(personService.count());
    }
}
