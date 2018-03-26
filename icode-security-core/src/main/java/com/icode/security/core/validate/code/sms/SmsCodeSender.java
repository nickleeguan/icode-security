package com.icode.security.core.validate.code.sms;

public interface SmsCodeSender {

    void send(String mobile, String code);
}
