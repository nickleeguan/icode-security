package com.icode.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.icode.validator.MyConstraint;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

public class User {
    public interface UserSimpleView {}
    public interface UserDetailView extends UserSimpleView{}

    @MyConstraint(message = "这是一个测试注解")
    private String username;

    @NotBlank(message = "密码不可为空")
    private String password;

    private int id;

    @Past(message = "日期必须为过去时间")
    private Date birthday;




    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
