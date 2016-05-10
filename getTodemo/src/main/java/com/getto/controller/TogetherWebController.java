package com.getto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.getto.dto.GuestDTO;
import com.getto.dto.TogetherDTO;
import com.getto.service.GuestService;
import com.getto.service.TogetherService;

@Controller
public class TogetherWebController {

    @Autowired
    private TogetherService togetherService;

    @Autowired
    private GuestService guestService;

    @RequestMapping(value = "/togethers", method = RequestMethod.GET)
    public String list(Model model) {
        List<TogetherDTO> res = togetherService.listAlltogethers();
        model.addAttribute("togethers", res);
        return "togethers";
    }

    @RequestMapping(value = "togethers/{id}")
    public String showTogether(@PathVariable Long id, Model model) {
        model.addAttribute("together", togetherService.findTogetherById(id));
        return "togethershow";
    }

    @RequestMapping(value = "togethers/{id}/{idGuest}")
    public String showTogetherGuest(@PathVariable Long id, @PathVariable Long idGuest, Model model) {
        TogetherDTO tog = togetherService.findTogetherById(id);
        tog.setAnswerer(idGuest);
        tog.setAnswer("Y");
        model.addAttribute("together", tog);
        return "togetheranswer";
    }

    @RequestMapping(value = "together", method = RequestMethod.POST)
    public String saveTogether(TogetherDTO togetherDTO) {
        if (togetherDTO.getGuests() != null) {
            List<GuestDTO> guests = new ArrayList<GuestDTO>();
            for (GuestDTO guestNames : togetherDTO.getGuests()) {
                String[] names = guestNames.getName().split(",");
                // String[] emails = guestNames.getEmail().split(",");
                // int sw = 0;
                for (String name : names) {
                    GuestDTO guest = new GuestDTO();
                    guest.setName(name);
                    // guest.setEmail(emails[sw]);
                    guests.add(guest);
                    // sw++;
                }
            }
            togetherDTO.setGuests(guests);
        }

        togetherService.saveTogether(togetherDTO);
        return "redirect:/togethers";
    }

    @RequestMapping(value = "togethers/new")
    public String newTogether(Model model) {
        TogetherDTO tog = new TogetherDTO();
        List<GuestDTO> guests = new ArrayList<GuestDTO>();
        GuestDTO guest = new GuestDTO();
        guests.add(guest);
        tog.setGuests(guests);
        model.addAttribute("together", tog);
        return "togetherform";
    }

    @RequestMapping(value = "saveGuestAnswerTog", method = RequestMethod.POST)
    public String saveGuestAnswer(TogetherDTO togetherDTO) {
        GuestDTO guest = guestService.findGuestById(togetherDTO.getAnswerer());
        guest.setAnswer(togetherDTO.getAnswer().equals("Y") ? true : togetherDTO.getAnswer().equals("N") ? false : null);
        guestService.saveAnswer(guest);
        return "redirect:/togethers/" + togetherDTO.getId();
    }

}
