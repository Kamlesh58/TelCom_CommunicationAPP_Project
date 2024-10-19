package com.telcom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telcom.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
