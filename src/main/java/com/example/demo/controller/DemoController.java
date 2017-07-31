package com.example.demo.controller;

import com.example.demo.editor.IsbnEditor;
import com.example.demo.model.Demo;
import com.example.demo.model.Isbn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by Wu.Kang on 2017/6/2.
 */
@Controller
public class DemoController {

    @RequestMapping(path={"/demo", "/"})
    public String demo(String a, String b, Model model){
        model.addAttribute("demo", new Demo());
        return "redirect:demoAdd";
    }

    @RequestMapping("/demoAdd")
    public String demoAdd(@Valid Demo demo, BindingResult result, Model model){
        //有错误信息.
        model.addAttribute("demo",demo);
        if(result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            for(ObjectError  error:list){
                System.out.println(error.getCode()+"---"+error.getArguments()+"---"+error.getDefaultMessage());
            }

            return "/demo";
        }
        return "/demo";
    }

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/books/{isbn}", method = RequestMethod.GET)
    public String getBook(@PathVariable Isbn isbn, Map<String, Object> model)
    {
        LOGGER.info("You searched for book with ISBN :: " + isbn.getIsbn());
        model.put("isbn", isbn);
        return "index";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Isbn.class, new IsbnEditor());
    }
}
