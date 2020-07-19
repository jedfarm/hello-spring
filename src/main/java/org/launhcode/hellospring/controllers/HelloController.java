package org.launhcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller

@RequestMapping("hello")
public class HelloController {

    // Handles request at path /hello/aurora
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value="aurora")
    public String hello(@RequestParam String name, Model model){
        String greeting = "Hello, " + name + "!";
        model.addAttribute("greeting", greeting);
        return "hello";
    }

    // Now lives at /hello/goodbye because of line 8 (@RequestMapping("hello")).
    @GetMapping("goodbye")
    @ResponseBody
    public String goodbye(){
        return "Goodbye, Spring!";
    }

    // Handles request of the form/ hello?name=LaunchCode
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value="hello")
    @ResponseBody
    public String helloWithQueryParam(@RequestParam String name, String language) {
        String salutation = "";
        switch(language){
            case "french":
                salutation = "Bonjour ";
                break;
            case "spanish":
                salutation = "Hola ";
                break;
            case "italian":
                salutation = "Buongiorno ";
                break;
            case "portuguese":
                salutation = "Ola ";
                break;
            default:
                salutation = "Hello ";
        }
        return salutation + name + "!";
    }

    // Handles request of the form /hello/LaunchCode
    @GetMapping("{name}")
    @ResponseBody
    public String helloWithPathParam(@PathVariable String name){
        return "Hello, " + name + "!";
    }

    //lives at /hello/form
    @GetMapping("form")
    @ResponseBody
    public String helloForm(){
        return "<html>" +
                    "<body>" +
                        "<form action='hello' method='post'>" +  // submit a request to /hello
                            "<input type='text' name='name'>" +
                            "<select name='language'>" +
                            "<option value='english'>english</option>" +
                            "<option value='french'>french</option>" +
                            "<option value='spanish'>spanish</option>" +
                            "<option value='italian'>italian</option>" +
                            "<option value='portuguese'>portuguese</option>" +
                            "</select>" +
                            "<input type='submit' value='Greet me!'>" +
                        "</form>" +
                    "</body>" +
                "</html>";
    }


    @GetMapping("simple_form")
    public String simpleHelloForm(){
        return "myform";
    }

    @GetMapping("hello-names")
    public String helloNames(Model model){
        List<String> names = new ArrayList<>();
        names.add("LaunchCode");
        names.add("Java");
        names.add("JavaScript");
        model.addAttribute("names", names);
        return "hello-list";
    }

}
