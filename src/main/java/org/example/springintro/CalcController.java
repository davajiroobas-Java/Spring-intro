package org.example.springintro;

import org.springframework.web.bind.annotation.*;



@RestController
public class CalcController {

    // Get /add?a=10&b=5 => 15

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    // GET /search?identity=387645
    @GetMapping("/search")
    public String search(@RequestParam(name = "identity", defaultValue = "def") String id) {
        return "Söker efter id: " + id;
    }



    @GetMapping("/add")
    public double add(@RequestParam double a,@RequestParam double b) {
        return a + b;
    }

    @GetMapping("/subtract")
    public double subtract(@RequestParam double a,@RequestParam double b) {
        return a - b;
    }

    @PostMapping("/person")
    public String createPerson(@RequestBody Person person) {
        return "Skapar " + person.getName() + " som är " + person.getAge() + " år";
    }

    @GetMapping("/person/{id}")
    public String getPersonById(@PathVariable("id") String id) {
        return  "Person med id: " + id;
    }

    // designa ett CRUD Create Read Update Delete
    // Designa API vilka endpoints - vilka metoder
    // skapa alla endpoints men med enkel funktionalitet ( varken skriver eller läser)


}
