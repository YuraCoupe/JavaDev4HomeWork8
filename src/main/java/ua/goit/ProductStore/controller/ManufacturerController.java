package ua.goit.ProductStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.ProductStore.model.ErrorMessage;
import ua.goit.ProductStore.model.Manufacturer;
import ua.goit.ProductStore.service.ManufacturerService;
import ua.goit.ProductStore.service.ProductService;
import ua.goit.ProductStore.validator.ManufacturerValidator;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping(path = "/manufacturers")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;
    private final ProductService productService;
    private final ManufacturerValidator validatorService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService, ProductService productService, ManufacturerValidator validatorService) {
        this.manufacturerService = manufacturerService;
        this.productService = productService;
        this.validatorService = validatorService;
    }

    @Autowired
    @Qualifier("manufacturerValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @ModelAttribute("manufacturer")
    public Manufacturer createManufacturerModel() {
        return new Manufacturer();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getManufacturers(Model model) {
        Set<Manufacturer> manufacturers = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "manufacturers";
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public ModelAndView showNewForm() {
        return new ModelAndView("manufacturer", "manufacturer", new Manufacturer());
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView showEditForm(@PathVariable UUID id) {
        return new ModelAndView("manufacturer", "manufacturer", manufacturerService.findById(id));
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public ModelAndView showEditFormWithParam(@RequestParam UUID id) {
        return new ModelAndView("manufacturer", "manufacturer", manufacturerService.findById(id));
    }

    @RequestMapping(value = "/edit/{id}/delete/{productId}", method = RequestMethod.GET)
    public ModelAndView deleteProduct(@PathVariable UUID id, @PathVariable UUID productId) {
        productService.delete(productId);
        return new ModelAndView("manufacturer", "manufacturer", manufacturerService.findById(id));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute("manufacturer") @Validated Manufacturer manufacturer,
                         BindingResult result) {
        ModelAndView model = new ModelAndView();
        if (result.hasErrors()) {
            if (Objects.nonNull(manufacturer.getId())) {
                manufacturer = manufacturerService.findById(manufacturer.getId());
            }
            model.addObject("manufacturer", manufacturer);
            model.setViewName("manufacturer");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        manufacturerService.save(manufacturer);
        Set<Manufacturer> manufacturers = manufacturerService.findAll();
        model.addObject("manufacturers", manufacturers);
        model.setViewName("manufacturers");
        return model;
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable UUID id, ModelMap model){
        ErrorMessage errorMessage = validatorService.validateManufacturerToDelete(id);
        if (!errorMessage.getErrors().isEmpty()) {
            model.addAttribute("errorMessage", errorMessage);
        } else {
            manufacturerService.delete(id);
        }
        Set<Manufacturer> manufacturers = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "manufacturers";
    }

}
