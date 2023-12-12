package com.lootlocker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LootLockerController {
    private List<Item> items = new ArrayList<>();

    @GetMapping("/")
    public String getForm(Model model) {
        model.addAttribute("types", Constants.TYPES);
        model.addAttribute("item", new Item());
        return "form";
    }

    @GetMapping("/inventory")
    public String getInventory() {
        return "inventory";
    }

    @PostMapping("/submitItem")
    public String handleSubmit(Item item) {
        items.add(item);
        return "redirect:/inventory";
    }
}
