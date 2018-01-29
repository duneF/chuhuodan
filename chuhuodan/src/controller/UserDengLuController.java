package controller;

import bean.UserDengLu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserDengLuService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午9:02 2018/1/10
 * @Modified By:
 */
@Controller
public class UserDengLuController {
    @Autowired
    private UserDengLuService userDengLuService;
    private UserDengLu userByuser;
    private HttpServletRequest request;

    @RequestMapping("login")
    public String findUserByUser(UserDengLu userDengLu,HttpServletRequest request){
        userByuser = userDengLuService.findUserByuser ( userDengLu );
        if (userByuser==null){
            request.setAttribute ( "msg","账号密码错误" );
            return "login.jsp";
        }
        HttpSession session = request.getSession ();
        session.setAttribute ( "USER",userByuser);
        //不关浏览器情况下，设置session有效时间为4小时
        session.setMaxInactiveInterval(240*60);
        return "redirect:/findAll.do";
    }
    
    @RequestMapping("loginOut")
    public String loginOut(HttpServletRequest request){
    	request.getSession().invalidate();
    	return "login.jsp";
    }

}
