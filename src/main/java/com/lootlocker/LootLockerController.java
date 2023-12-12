package com.lootlocker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class LootLockerController {
     private List<Item> items = new ArrayList<>();

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false)String id) {
        int index = getItemIndex(id);
        model.addAttribute("item", index == Constants.NOT_FOUND ? new Item() : items.get(index));
        model.addAttribute("types", Constants.TYPES);
        return "form";
    }

    @GetMapping("/inventory")
    public String getInventory(Model model) {
        model.addAttribute("items", items);
        return "inventory";
    }

    @PostMapping("/submitItem")
    public String handleSubmit(Item item, RedirectAttributes redirectAttributes) {
        int index = getItemIndex(item.getId());
        String status = Constants.SUCCESS_STATUS;
        if(!isValidDate(item.getDate())) {
            status = Constants.FAILED_STATUS;
        } else if((index == Constants.NOT_FOUND)) {
            items.add(item);
        } else {
            items.set(index, item);
        }
        redirectAttributes.addFlashAttribute("status", status);
        return "redirect:/inventory";
    }

    public Integer getItemIndex(String id) {
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }

    public static boolean isValidDate(Date date) {
        return new Date().after(date);
    }
}
