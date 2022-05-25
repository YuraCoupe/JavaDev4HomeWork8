package ua.goit.ProductStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.ProductStore.model.Manufacturer;
import ua.goit.ProductStore.model.Product;
import ua.goit.ProductStore.model.ManufacturerEditor;
import ua.goit.ProductStore.service.ManufacturerService;
import ua.goit.ProductStore.service.ProductService;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping(path = "/products")
public class ProductController {
    private final ProductService productService;
    private final ManufacturerService manufacturerService;

    @Autowired
    public ProductController(ProductService productService, ManufacturerService manufacturerService) {
        this.productService = productService;
        this.manufacturerService = manufacturerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getProducts(Model model) {
        Set<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public String showNewForm(Model model) {
        Set<Manufacturer> manufacturers = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("manufacturer", new Manufacturer());
        model.addAttribute("product", new Product());
        return "product";
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public String showEditForm(@PathVariable UUID id, Model model) {
        Set<Manufacturer> manufacturers = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("manufacturer", new Manufacturer());
        model.addAttribute("product", productService.findById(id));
        return "product";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public String showEditFormWithParam(@RequestParam UUID id, Model model) {
        Set<Manufacturer> manufacturers = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("manufacturer", new Manufacturer());
        model.addAttribute("product", productService.findById(id));
        return "product";    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView submit(@Valid @ModelAttribute("product") Product product,
                         BindingResult result, ModelAndView model) {
        if (result.hasErrors()) {
            Set<Manufacturer> manufacturers = manufacturerService.findAll();
            model.addObject("manufacturers", manufacturers);
            model.addObject("product", product);
            model.setViewName("product");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        productService.save(product);
        Set<Product> products = productService.findAll();
        model.addObject("products", products);
        model.setViewName("products");
        return model;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable UUID id, ModelMap model) {
        productService.delete(id);
        Set<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Manufacturer.class, "manufacturer", new ManufacturerEditor(manufacturerService));
    }
}
