package ua.goit.ProductStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.goit.ProductStore.model.Manufacturer;
import ua.goit.ProductStore.service.ManufacturerService;

import java.util.Set;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping(value = "/**")
    public String getBook(Model model) {
        Set<Manufacturer> manufacturers1 = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers1);
        return "manufacturers";
    }

}
