package com.sabaton.wishmaster.controller;
import com.sabaton.wishmaster.model.Item;
import com.sabaton.wishmaster.repository.WishmasterRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/view")
    String viewList(@RequestParam(name="id") int wishlistId, Model model){

        ArrayList<Item>items = wishmasterRepository.getItemsFromId(wishlistId);

        model.addAttribute("items", items);

        return "viewlist";
    }

    @GetMapping("/edit")
    String editList(@RequestParam(name="id") int wishlistId,
                    Model model,
                    HttpSession session){

        // Ensure that user has entered correct password
        String password = wishmasterRepository.getWishlistPassword(wishlistId);
        if(session.getAttribute("id") != null && session.getAttribute("pwd") != null) {
            if(!(session.getAttribute("id").equals(wishlistId) && session.getAttribute("pwd").equals(password))) {
                // If user password is invalid:
                return "redirect:/view/" + wishlistId;
            }
        }
        else {
            // Session is null
            return "redirect:/view/" + wishlistId;
        }

        ArrayList<Item>items = wishmasterRepository.getItemsFromId(wishlistId);

        model.addAttribute("items", items);

        return "editlist";
    }

    @PostMapping("/edit")
    String verifyPassword(@RequestParam("pwd") String input_password,
                          @RequestParam("wishlistId") int id,
                          HttpSession session) {

        String db_password = wishmasterRepository.getWishlistPassword(id);

        if(db_password.equals(input_password)) {
            session.setAttribute("id", id);
            session.setAttribute("pwd", input_password);
        }

        return "redirect:/edit?id=" + id;
    }

    @PostMapping("/createItem")
    String createItem(@RequestParam("product-title") String title,
                      @RequestParam("product-link") String link) {
        return "";
    }
}