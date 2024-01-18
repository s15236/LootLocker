package com.lootlocker.service;

import com.lootlocker.Constants;
import com.lootlocker.Item;
import com.lootlocker.repository.LootLockerRepository;

import java.util.Date;
import java.util.List;

public class LootLockerService {

    LootLockerRepository repository = new LootLockerRepository();

    public Item getItem(int index) {
        return repository.getItem(index);
    }

    public void addItem(Item item) {
        repository.addItem(item);
    }

    public void updateItem(int index, Item item) {
        repository.updateItem(index,item);
    }

    public List<Item> getItems() {
        return repository.getItems();
    }

    public int getItemIndex(String id) {
        for (int i = 0; i < repository.getItems().size(); i++) {
            if(repository.getItems().get(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }

    public Item getItemById(String id) {
        int index = getItemIndex(id);
        return index == Constants.NOT_FOUND ? new Item() : getItem(index);
    }

    public String submitItem(Item item) {
        int index = getItemIndex(item.getId());
        String status = Constants.SUCCESS_STATUS;
        if(!isValidDate(item.getDate())) {
            status = Constants.FAILED_STATUS;
        } else if((index == Constants.NOT_FOUND)) {
            repository.getItems().add(item);
        } else {
            repository.getItems().set(index, item);
        }
        return status;
    }

    public static boolean isValidDate(Date date) {
        return new Date().after(date);
    }
}
