package com.icode.security.app;

import com.icode.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.icode.security.core.properties.SecurityConstants;
import com.icode.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableResourceServer
public class IcodeResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler icodeAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler icodeAuthenticationFailureHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer icodeSocialSecurityConfig;


    @Autowired
    private SecurityProperties securityProperties;



    @Override
    public void configure(HttpSecurity http) throws Exception {
        //表单登录.指定身份认证的方式
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(icodeAuthenticationSuccessHandler)
                .failureHandler(icodeAuthenticationFailureHandler)
                .and()
//                .apply(validateCodeSecurityConfig)
//                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(icodeSocialSecurityConfig)
                .and()
//                .rememberMe()
//                .tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
//                .userDetailsService(userDetailsService)
                //basic验证方式
//        http.httpBasic()
//                .and()
                .authorizeRequests()//对请求授权
                .antMatchers(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*").permitAll()
                .anyRequest()//对象为所有请求
                .authenticated()//都需要认证授权
                .and()
                .csrf().disable();//关闭跨站防护
    }
}
