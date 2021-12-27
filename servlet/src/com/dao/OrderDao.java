package com.dao;

import com.beans.Order;

import java.util.List;

public interface OrderDao {
    int addOrder(String msg, Integer id);

    List<Order> searchAllOrder(Integer id);

    int updateOrder(Integer id);
}
