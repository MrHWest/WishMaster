package com.sabaton.wishmaster.controller;

import com.sabaton.wishmaster.model.Item;
import com.sabaton.wishmaster.repository.WishmasterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WishmasterController {

    private WishmasterRepository wishmasterRepository;
    public WishmasterController(WishmasterRepository wishmasterRepository) {
        this.wishmasterRepository = wishmasterRepository;}

    @GetMapping("/index")
    public String index(Model model){
        return "forside";
    }

    @GetMapping("/view/{id}")
    public String viewList(@PathVariable("id") String id, Model model) {
        // TODO: Using getAll() for now. Set this to select all items belonging to list of given id
        model.addAttribute("items", wishmasterRepository.getAll());
        return "viewlist";
    }

    /*@GetMapping("//create"){
        public String showCreate(){
            return //create-side ;
        }*/

     @PostMapping("/create")
     public String createItem(@RequestParam("item-title") String newTitle,
                                 @RequestParam("product-link") String newLink){

         // TODO: re-evaluate the need for item ID in model. Set ID=0 until then
         Item newItem = new Item(0, newLink, newTitle);


         wishmasterRepository.addItem(newItem);

         //tilbage til itemlisten
         return "redirect:/";
     }

}
