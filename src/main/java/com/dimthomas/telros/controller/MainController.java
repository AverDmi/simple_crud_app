package com.dimthomas.telros.controller;

import com.dimthomas.telros.model.Customer;
import com.dimthomas.telros.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    private final CustomerService customerService;

    @Autowired
    public MainController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/customers")
    public String findAll(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/customer-create")
    public String createCustomerForm(Customer customer, Model model) {
        model.addAttribute("customer", customer);
        return "customer-create";
    }

    @PostMapping("/customer-create")
    public String createCustomer(@RequestParam("file") MultipartFile file, Customer customer) throws IOException {
        customerService.saveCustomer(customer, file);
        return "redirect:/customers";
    }

    @GetMapping("customer-delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteById(id);
        return "redirect:/customers";
    }

    @GetMapping("/customer-update/{id}")
    public String updateCustomerForm(@PathVariable("id") Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer-update";
    }

    @PostMapping("/customer-update")
    public String updateCustomer(@RequestParam("file") MultipartFile file, Customer customer) throws IOException {
        customerService.saveCustomer(customer, file);
        return "redirect:/customers";
    }

    @GetMapping("/customer-info/{id}")
    public String infoCustomer(@PathVariable("id") Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        model.addAttribute("images", customer.getImages());
        return "customer-info";
    }
}
