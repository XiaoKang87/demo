package com.example.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Wu.Kang on 2017/6/22.
 */
@ApiModel(value = "User", description = "用户信息")
public class User {

    @ApiModelProperty(value = "ID")
    private int id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "年龄")
    private int age;
    @ApiModelProperty(value = "性别", dataType = "java.lang.Integer", allowableValues = "1,2")
    private byte sex;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
