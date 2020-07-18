package com.EFApplication.EcoFashion.Services.Implementations;

import com.EFApplication.EcoFashion.Entities.Item;
import com.EFApplication.EcoFashion.Repositories.ItemRepository;
import com.EFApplication.EcoFashion.Services.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImplementation implements ItemService {
    ItemRepository repository;

    @Autowired
    public ItemServiceImplementation(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Item save(Item item) {
        return repository.save(item);
    }

    @Override
    public void delete(Item item) {
        repository.delete(item);
    }

    @Override
    public void deleteAll(){
        repository.deleteAll();
    }

    @Override
    public List<Item> findAll(){
        return repository.findAll();
    }

    @Override
    public Optional<Item> findByitemID(long itemID){
        return repository.findByitemID(itemID);
    }

    @Override
    public List<Item> findByitemType(String type) {
        return repository.findByitemType(type);
    }

    @Override
    public List<Item> findByitemSize(String size) {
        return repository.findByitemSize(size);
    }

    @Override
    public List<Item> findByitemColor (String color) {
        return repository.findByitemColor(color);
    }

    @Override
    public List<Item> findByitemBrand (String brand) {
        return repository.findByitemBrand(brand);
    }

    @Override
    public List<Item> findByitemCondition (String condition) {
        return repository.findByitemCondition(condition);
    }

    @Override
    public List<Item> findByitemGender (String gender) { return repository.findByitemGender(gender); }

    @Override
    public List<Item> findByitemPriceLessThanEqual(long price) {
        return repository.findByitemPriceLessThanEqual(price);
    }

    @Override
    public void deleteByitemBuyerID(long buyerID) {
        repository.deleteByitemBuyerID(buyerID);
    }

    @Override
    public void updateBuyerID(long itemID, long buyerID) {
        repository.changeBuyerID(itemID, buyerID);
    }

    @Override
    public void removeBuyerID(long buyerID, long oldBuyerID) { repository.removeBuyerID(buyerID, oldBuyerID);}
}
