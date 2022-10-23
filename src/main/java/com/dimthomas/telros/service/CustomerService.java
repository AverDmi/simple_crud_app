package com.dimthomas.telros.service;

import com.dimthomas.telros.model.Customer;
import com.dimthomas.telros.model.Image;
import com.dimthomas.telros.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer, MultipartFile file) throws IOException {
        Image image;
        if(file.getSize() != 0) {
            image = toImageEntity(file);
            customer.addImageToCustomer(image);
        }
        return customerRepository.save(customer);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
