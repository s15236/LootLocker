package com.lootlocker.controller;

import com.lootlocker.Constants;
import com.lootlocker.Item;
import com.lootlocker.service.LootLockerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class LootLockerController {
    LootLockerService service;

    public LootLockerController(LootLockerService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false)String id) {
        model.addAttribute("item", service.getItemById(id));
        return "form";
    }

    @GetMapping("/inventory")
    public String getInventory(Model model) {
        model.addAttribute("items", service.getItems());
        return "inventory";
    }

    @PostMapping("/submitItem")
    public String handleSubmit(@Valid Item item, BindingResult result, RedirectAttributes redirectAttributes) {
        if(item.getDate() == null) return "form";
        if(result.hasErrors()) return "form";

        String status = service.submitItem(item);
        redirectAttributes.addFlashAttribute("status", status);
        return "redirect:/inventory";
    }
}
