package cz.czechitas.java2webapps.ukol6.Controller;
import cz.czechitas.java2webapps.ukol6.Entity.Vizitka;
import cz.czechitas.java2webapps.ukol6.Repository.VizitkaRepository;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class VizitkaController {
    private final VizitkaRepository vizitkaRepository;

    public VizitkaController(VizitkaRepository vizitkaRepository) {
        this.vizitkaRepository = vizitkaRepository;
    }

    @InitBinder
    public void nullStringBinding(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public Object seznam() {
        return new ModelAndView("seznam")
                .addObject("vizitky", vizitkaRepository.findAll());
    }

    @GetMapping("/{id}")
    public Object detail(@PathVariable int id) {
        if (vizitkaRepository.existsById(id)) {
            return new ModelAndView("vizitka")
                    .addObject("vizitka", vizitkaRepository.findById(id).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nova")
    public Object novy() {
        return new ModelAndView("formular")
                .addObject("vizitka", new Vizitka());
    }

    @PostMapping("/nova")
    public Object nova(@ModelAttribute("vizitka") @Valid Vizitka vizitka, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formular";
        }
        vizitkaRepository.save(vizitka);
        return "redirect:/";
    }

}
