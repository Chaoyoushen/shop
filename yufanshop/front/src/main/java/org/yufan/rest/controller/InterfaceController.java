package org.yufan.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yufan.bean.User;
import org.yufan.common.HttpClientUtil;
import org.yufan.common.Result;

@Controller
public class InterfaceController {
    private final String ADMIN_URL ="http://admin.chaoyous.cn/rest";

    private final String SSO_URL="http://sso.chaoyous.cn/sso";




}
