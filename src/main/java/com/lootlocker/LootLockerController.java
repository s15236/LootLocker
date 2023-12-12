package com.lootlocker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LootLockerController {
    @GetMapping("/")
    public String getForm(Model model) {
        model.addAttribute("types", Constants.TYPES);
        return "form";
    }


}
