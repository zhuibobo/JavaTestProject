package com.springmvcproject2.demo.controller;

import com.springmvcproject2.demo.formatter.AddressFormatter;
import com.springmvcproject2.demo.formatter.Customer;
import com.springmvcproject2.demo.model.Person;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/demo009Formatter")
public class Demo009FormatterController {

    //加上value将来限定ModelAttribute
    @InitBinder("fullAddressCustomer")
    public void fullAddressBinding (WebDataBinder binder) {
        AddressFormatter addressFormatter = new AddressFormatter();
        addressFormatter.setStyle(AddressFormatter.Style.FULL);
        binder.addCustomFormatter(addressFormatter);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, "sumbitDate",
                new CustomDateEditor(dateFormatter, true));
    }

    @InitBinder("regionAddressCustomer")
    public void regionAddressBinding (WebDataBinder binder) {
        AddressFormatter addressFormatter = new AddressFormatter();
        addressFormatter.setStyle(AddressFormatter.Style.REGION);
        binder.addCustomFormatter(addressFormatter);
    }

    @RequestMapping(value = "convertToCustomerVoParamFullAddressCustomer",method = RequestMethod.POST)
    @ResponseBody
    public Customer convertToCustomerVoParamFullAddressCustomer(@ModelAttribute("fullAddressCustomer") Customer customer) {
        return customer;
    }

    @RequestMapping(value = "convertToCustomerVoParamRegionAddressCustomer",method = RequestMethod.POST)
    @ResponseBody
    public Customer convertToCustomerVoParamRegionAddressCustomer(@ModelAttribute("regionAddressCustomer") Customer customer) {
        return customer;
    }
}
