package com.sabaton.wishmaster.controller;
import com.sabaton.wishmaster.model.Item;
import com.sabaton.wishmaster.repository.WishmasterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class WishmasterController{

    private WishmasterRepository wishmasterRepository;

    public WishmasterController(WishmasterRepository wishmasterRepository){
        this.wishmasterRepository = wishmasterRepository;
    }

    @GetMapping("/")
    String getForside(Model model){

        ArrayList<Item>items = wishmasterRepository.getAllItems();

        model.addAttribute("items", items);

        return "viewlist";
    }


}