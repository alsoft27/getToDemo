package com.getto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.getto.dto.GuestDTO;
import com.getto.dto.TogetherDTO;
import com.getto.service.GuestService;

@RestController
public class GuestController {

    /**
     * get my getTo save answer
     * 
     */
    @Autowired
    private GuestService service;

    @RequestMapping(value = "/addGuest", method = RequestMethod.POST)
    public ResponseEntity<GuestDTO> addGuest(@RequestBody GuestDTO answer) {
        service.addGuest(answer);
        return null;
    }

    @RequestMapping(value = "/saveGuestAnswer", method = RequestMethod.POST)
    public ResponseEntity<GuestDTO> saveGuestAnswer(@RequestBody GuestDTO answer) {
        service.saveAnswer(answer);
        return new ResponseEntity<GuestDTO>(answer, HttpStatus.OK);
    }

    @RequestMapping(value = "/guest", method = RequestMethod.GET, params = { "idGuest" })
    public ResponseEntity<TogetherDTO> findTogetherByIdGuest(@RequestParam Long idGuest) {
        TogetherDTO tog = service.findTogetherByIdGuest(idGuest);
        ResponseEntity<TogetherDTO> res = null;
        if (tog == null) {
            res = new ResponseEntity<TogetherDTO>(tog, HttpStatus.NOT_FOUND);
        } else {
            res = new ResponseEntity<TogetherDTO>(tog, HttpStatus.OK);
        }
        return res;
    }
}
