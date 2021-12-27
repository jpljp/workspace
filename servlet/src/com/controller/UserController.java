package com.controller;

import com.beans.PageInfo;
import com.beans.User;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.util.PageUtil;
import org.w3c.dom.Element;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * @author lin
 */
public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag = request.getParameter("flag");

        if ("login".equals(flag)) {
            login(request, response);
        } else if ("delById".equals(flag)) {
            delById(request, response);
        } else if ("updateById".equals(flag)) {
            updateById(request, response);
        } else if ("update".equals(flag)) {
            update(request, response);
        } else if ("delUserByIds".equals(flag)) {
            delUserByIds(request, response);
        }else if("allUser".equals(flag)){
            allUser(request,response);
        }else if("suoId".equals(flag)){
            suoId(request,response);
        }else if("back".equals(flag)){
            back(request,response);
        }else if("add".equals(flag)){
            add(request,response);
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");

        UserDao dao=new UserDaoImpl();
        int result=dao.addUser(userName,password);
        if (result!=0){
            request.setAttribute("msg", "注册成功，请登录！");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else{
            request.setAttribute("msg", "注册失败！");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

    private void back(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }


    private void suoId(HttpServletRequest request, HttpServletResponse response) {
        Integer id=Integer.parseInt(request.getParameter("id"));
        int status=Integer.parseInt(request.getParameter("status"));
        if (status<3){
            status=3;
        }else{
            status=0;
        }
        UserDao dao=new UserDaoImpl();
        dao.setStatus(id,status );
        try {
            this.allUser(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void allUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao=new UserDaoImpl();

        Integer pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
        int pageSize=10;
        int rowCount=dao.getCount();
        PageInfo page=PageUtil.getPageInfo(pageSize, pageIndex, rowCount);
        ArrayList<User> userList=dao.searchAllUser(page);
        request.setAttribute("userList",userList);
        request.setAttribute("page",page);
        request.getRequestDispatcher("showDate.jsp").forward(request, response);

    }

    private void delUserByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //拿到id
        String ids=request.getParameter("ids");
        ids= ids.substring(0, ids.length()-1);
        String [] strs=ids.split(",");

        UserDao dao=new UserDaoImpl();
        int result=dao.delByIds(strs);
        if (result>0){
            allUser(request,response);
            request.setAttribute("msg", "删除成功");
            request.getRequestDispatcher("showDate.jsp").forward(request, response);
        }else{
            allUser(request,response);
            request.setAttribute("msg", "删除失败");
            request.getRequestDispatcher("showDate.jsp").forward(request, response);
        }

    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        int id=Integer.parseInt(request.getParameter("id"));

        User user=new User();
        user.setId(id);
        user.setUserName(userName);
        user.setPassword(password);


        UserDao dao=new UserDaoImpl();
        int result=dao.updateUser(user);
        if (result > 0){
            allUser(request,response);

            request.setAttribute("msg", "修改成功");
            allUser(request, response);
            request.getRequestDispatcher("showDate.jsp").forward(request, response);
        }else{

            request.setAttribute("msg", "修改失败");
            allUser(request,response);

            request.getRequestDispatcher("showDate.jsp").forward(request, response);
        }

    }

    private void updateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserDao dao = new UserDaoImpl();
        User user=dao.searchUserById(id);


        if (user!=null){
            request.setAttribute("user", user);
            request.getRequestDispatcher("update.jsp").forward(request, response);
        }
    }

    private void delById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        UserDao dao=new UserDaoImpl();
        int result=dao.delById(id);
        if (result>0){
            request.setAttribute("msg", "删除成功");
            allUser(request,response);
            request.getRequestDispatcher("showDate.jsp").forward(request, response);
        }else{
            request.setAttribute("msg", "删除失败");
            request.getRequestDispatcher("showDate.jsp").forward(request, response);
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String status = request.getParameter("status");

            UserDao dao = new UserDaoImpl();
            User user = dao.login(userName, password);
            if (status != null) {
                Cookie name = new Cookie("userName", userName);
                Cookie pwd = new Cookie("password", password);
                name.setMaxAge(3600 * 24 * 7);
                pwd.setMaxAge(3600 * 24 * 7);
                response.addCookie(name);
                response.addCookie(pwd);
            } else {
                Cookie name = new Cookie("userName", userName);
                Cookie pwd = new Cookie("password", password);
                name.setMaxAge(0);
                pwd.setMaxAge(0);
                response.addCookie(name);
                response.addCookie(pwd);
            }
            if (user.getStatus() >= 3) {
                request.setAttribute("msg", "账户已锁定,请联系管理员");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {

                if (user.getPassword().equals(password)) {

                    dao.setStatus(user.getId(), 0);
                    request.getSession().setAttribute("user",user);
                    request.getRequestDispatcher("main.jsp").forward(request, response);
                } else {

                    dao.setStatus(user.getId(), user.getStatus() + 1);
                    User user1 = dao.login(userName, password);
                    int result = 2 - user1.getStatus();

                    request.setAttribute("msg", "还有" + result + "次机会");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }


    }
}


