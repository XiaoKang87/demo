package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.editor.IsbnEditor;
import com.example.demo.model.Demo;
import com.example.demo.model.Isbn;
import com.example.demo.model.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Wu.Kang on 2017/6/2.
 */
@Controller
public class DemoController {

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        return "welcome";
    }

    @RequestMapping(path={"/demo"})
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

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="接口说明(测试)",notes="在没有会话、没有签名的情况下，进入方法体")
    @ApiImplicitParam(name = "tag", paramType = "query")
    public String sayHello(String tag, @RequestBody User user) {
        return "hello " + user.getName() + " age " + user.getAge() + " tag " + tag;
    }

    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "query"),
            @ApiImplicitParam(name = "zone", paramType = "query")
    })
    public String getInfo(Integer id, String zone)
    {
        return "id is " + id + " zone is " + zone;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Isbn.class, new IsbnEditor());
    }

    @ResponseBody
    @RequestMapping(value = "/dynamo", method = RequestMethod.POST)
    public String dynamo(HttpServletRequest request) {
        String name = "";
        try {
            String body = request.getReader().readLine();
            JSONObject jsonObject = JSONObject.parseObject(body);
            name = jsonObject.getString("interfaceName");
        }catch (IOException ex) {

        }
        return name;
    }
}
