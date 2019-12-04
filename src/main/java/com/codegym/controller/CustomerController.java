package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public ModelAndView createCustomer(){
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customers",new Customer());
        return modelAndView;
    }
    @RequestMapping(value = "/createNewCustomer" ,method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Customer> customers() {
        return customerService.findAll();
    }

    @GetMapping("")
    public ModelAndView showCustomer(){
        ModelAndView modelAndView = new ModelAndView("customer/list");
        modelAndView.addObject("customers",customers());
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Customer deleteCustomer(@PathVariable Long id){
        return customerService.remove(id);
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editCustomer(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method=RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Customer editCustomer(@PathVariable Long id,@RequestBody Customer customer) {
        customer.setId(id);
        return customerService.save(customer);
    }
}
