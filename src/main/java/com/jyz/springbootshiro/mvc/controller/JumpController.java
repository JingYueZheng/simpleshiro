package com.jyz.springbootshiro.mvc.controller;

import com.jyz.springbootshiro.comment.Logging;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JumpController {

      @RequestMapping("/jump/toHome")
      public String  toHome(){
          return  "/home.html";
      }

        @RequestMapping("/")
        public String redirectIndex() {
            return "/home.html";
        }


}
