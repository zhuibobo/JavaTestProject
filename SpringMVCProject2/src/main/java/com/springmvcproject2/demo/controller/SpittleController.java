package com.springmvcproject2.demo.controller;

import com.springmvcproject2.demo.model.Spittle;
import com.springmvcproject2.demo.service.ISpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

    @Autowired
    private ISpittleRepository spittleRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model) {
        List<Spittle> spittles = spittleRepository.findSpittles(Long.MAX_VALUE, 20);
        model.addAttribute(spittles);
        return "spittles";
    }
}
