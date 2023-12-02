package Desertniy.Lab5.controllers;

import Desertniy.Lab5.model.DataBaseObjects;
import Desertniy.Lab5.model.PropertyBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class MainPropertyControllers {

    @Autowired
    DataBaseObjects data;

    public Model Models(Model model){
        ArrayList<DataBaseObjects.RentalProperty> objects = new ArrayList<>();
        for (Map.Entry<String, DataBaseObjects.RentalProperty> obj : data.getObjects()){
            objects.add(obj.getValue());
            System.out.println(obj.getValue());
        }
        model.addAttribute("properties", objects);
        model.addAttribute("property", new PropertyBody());
        model.addAttribute("street_act", "");
        return model;
    }
    @GetMapping("/property")
    public String propertyGet(Model model){
        Model models = Models(model);
        return "properties";
    }

    @PostMapping("/property")
    public String propertyPost(@Valid @ModelAttribute("property") PropertyBody body, Model model){
        if (data.getObj(body.getStreet()) == null)
            data.addProperty(body.getStreet(), body.getMonthlyRent(), body.getPrice());
        else
            model.addAttribute("error", true);
        return "redirect:/property";
    }

    @PostMapping("/delete/{street_act:.+}")
    public String propertyDelete(@RequestParam("street_act") String street_act){
        System.out.println(street_act);
        if (data.getObj(street_act) != null){
            data.remove(street_act);
        }
        return "redirect:/property";
    }

    @PostMapping("/put")
    public String propertyPut(@Valid @ModelAttribute("property") PropertyBody body){
        DataBaseObjects.RentalProperty data_t = data.getObj(body.getStreet());
        if (data_t != null){
            data_t.put(body.getPrice(), body.getMonthlyRent());
        }
        return "redirect:/property";
    }

    @GetMapping("/property/{street_act:.+}")
    public String propertyGetStreet(@RequestParam("street_act") String street_act, Model model){
        DataBaseObjects.RentalProperty rp = data.getObj(street_act);
        PropertyBody body = new PropertyBody(rp.getStreet(), rp.getPrice(), rp.getMonthlyRent());
        System.out.println(body);
        model.addAttribute("property_get", body);
        Model models = Models(model);
        return "properties";
    }

}
