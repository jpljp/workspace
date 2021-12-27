package com.controller;

import com.beans.Order;
import com.beans.User;
import com.dao.OrderDao;
import com.dao.impl.OrderDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flag=request.getParameter("flag");

        if("faOrder".equals(flag)){
             faOrder(request,response);
        }else if("fafafa".equals(flag)){
            fafafa(request,response);
        }else if("qOrder".equals(flag)){
            qOrder(request,response);
        }else if("qqq".equals(flag)){
            qqq(request,response);
        }
    }

    private void qqq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id=Integer.parseInt(request.getParameter("id"));
        OrderDao dao=new OrderDaoImpl();
        int result=dao.updateOrder(id);

        String msg= result!=0?"抢单成功":"抢单失败";
        request.setAttribute("msg", msg);
        qOrder(request,response);
        request.getRequestDispatcher("qOrder.jsp").forward(request, response);
    }

    private void qOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDao dao=new OrderDaoImpl();
        User user= (User) request.getSession().getAttribute("user");
        Integer id=user.getId();
        List<Order> orderList=dao.searchAllOrder(id);

        request.setAttribute("orderList",orderList);
        request.getRequestDispatcher("qOrder.jsp").forward(request, response);

    }

    private void fafafa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg1=request.getParameter("msg");
        Integer id=Integer.parseInt(request.getParameter("id"));

        OrderDao dao=new OrderDaoImpl();
        int result=dao.addOrder(msg1,id);
        String msg = (result != 0) ? "发单成功" : "发单失败";
        request.setAttribute("msg",msg);
        request.getRequestDispatcher("main.jsp").forward(request,response);

    }


    private void faOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user= (User) request.getSession().getAttribute("user");
        int id=user.getId();
        request.getRequestDispatcher("fa.jsp").forward(request, response);
    }
}

