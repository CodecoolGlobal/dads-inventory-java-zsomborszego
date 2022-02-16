package com.codecool.dadsinventory.service;

import com.codecool.dadsinventory.model.AppUser;
import com.codecool.dadsinventory.model.Category;
import com.codecool.dadsinventory.model.Item;
import com.codecool.dadsinventory.model.Role;
import com.codecool.dadsinventory.repository.CategoryRepository;
import com.codecool.dadsinventory.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class InitService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final UserService userService;

    @Autowired
    public InitService(ItemRepository itemRepository, CategoryRepository categoryRepository, UserService userService) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    public void seedDatabase() {
        Category smallCat = Category.builder().name("small").build();
        Category mediumCat = Category.builder().name("medium").build();
        Category largeCat = Category.builder().name("medium").build();

        smallCat = categoryRepository.saveAndFlush(smallCat);
        mediumCat = categoryRepository.saveAndFlush(mediumCat);
        largeCat = categoryRepository.saveAndFlush(largeCat);

        Item ship = Item.builder().name("Ship").price(5).category(largeCat).comment("").inStock(true).build();
        Item hammer = Item.builder().name("Hammer").price(3).category(mediumCat).comment("").inStock(true).build();
        Item pin = Item.builder().name("Pin").price(2).category(smallCat).comment("").inStock(true).build();
        ship = itemRepository.saveAndFlush(ship);
        hammer = itemRepository.saveAndFlush(hammer);
        pin = itemRepository.saveAndFlush(pin);

        smallCat.setItems(List.of(pin));
        mediumCat.setItems(List.of(hammer));
        largeCat.setItems(List.of(ship));
        categoryRepository.saveAllAndFlush(Arrays.asList(smallCat, mediumCat, largeCat));
        
        // users

        userService.saveRole(new Role(null, "ROLE_USER"));
        userService.saveRole(new Role(null, "ROLE_MANAGER"));
        userService.saveRole(new Role(null, "ROLE_ADMIN"));
        userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

        userService.saveUser(new AppUser(null, "Dad Peter", "peter", "1234", new ArrayList<>()));
        userService.saveUser(new AppUser(null, "Mom Dolores", "dolores", "1234", new ArrayList<>()));

        userService.addRoleToUser("peter", "ROLE_USER");
        userService.addRoleToUser("dolores", "ROLE_USER");
        
    }

}
