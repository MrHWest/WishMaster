package com.sabaton.wishmaster.controller;

import com.sabaton.wishmaster.repository.WishmasterRepository;
import org.springframework.stereotype.Controller;

@Controller
public class WishmasterController {

    private WishmasterRepository wishmasterRepository;
    public WishmasterController(WishmasterRepository wishmasterRepository) {
        this.wishmasterRepository = wishmasterRepository;}

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("item", wishmasterRepository.getAll());
        return index;
    }

    /*@GetMapping("//create"){
        public String showCreate(){
            return //create-side ;
        }*/

    @PostMapping("/create")
    public String createItem(@RequestParam("item-title") String newTitle,
                                @RequestParam("product-link") String newLink){

        Item newItem = new Item();
        newItem.setLink(newLink);
        newItem.setTitle(newTitle);


        WishmasterRepository.addItem(newItem);

        //tilbage til itemlisten
        return "redirect:/";
    }

}
