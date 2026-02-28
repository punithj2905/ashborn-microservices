package com.ashborn.ecommerce.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRespository extends JpaRepository<Order,Integer > {

}
