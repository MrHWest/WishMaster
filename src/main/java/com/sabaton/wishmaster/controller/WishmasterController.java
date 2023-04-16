package com.sabaton.wishmaster.controller;
import com.sabaton.wishmaster.model.Item;
import com.sabaton.wishmaster.repository.WishmasterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/view/{id}")
    String viewList(@PathVariable("id") int wishlistId, Model model){

        ArrayList<Item>items = wishmasterRepository.getItemsFromId(wishlistId);

        model.addAttribute("items", items);

        return "viewlist";
    }
}