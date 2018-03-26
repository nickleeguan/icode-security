package com.icode.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.icode.dto.User;
import com.icode.dto.UserQueryCondition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails){
//    public Object getCurrentUser(Authentication authentication){
//        return SecurityContextHolder.getContext().getAuthentication();
//        return authentication;
        return userDetails;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id){
        System.out.println(id);
    }

    /**
     * 此处是模拟返回了错误
     * @param user
     * @return
     */
    @PostMapping
    public User create(@Valid @RequestBody User user){

        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getId());
        System.out.println(user.getBirthday());
        user.setId(1);
        return user;
    }
//    BindingResult:用于错误收集，不直接返回错误
    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors){
        if (errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                String message =  fieldError.getField() + "-----" + error.getDefaultMessage();
                System.out.println(message);
            });
        }


        System.out.println("username is :" + user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getId());
        System.out.println(user.getBirthday());
        user.setId(1);
        return user;
    }

//    @RequestParam(name = "username", required = false, defaultValue = "tom")
//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    @PostMapping
//    @PutMapping
//    @DeleteMapping
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户查询服务")//swagger显示信息
    public List<User> query(UserQueryCondition condition,@PageableDefault(page = 2, size = 17, sort = "username,asc") Pageable pageable){

        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getSort());
        System.out.println(pageable.getPageNumber());
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

//    @RequestMapping(value = "/user/{id:\\d+}", method = RequestMethod.GET)
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@ApiParam(value = "用户ID") @PathVariable(name = "id") String idxxx){
//        throw new RuntimeException("user not exist");

//        throw new UserNotExistException(idxxx);
        User user = new User();
        System.out.println("调用getInfo");
        user.setUsername("tom");
        return user;
    }


}
