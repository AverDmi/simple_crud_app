package com.dimthomas.telros.repository;

import com.dimthomas.telros.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
