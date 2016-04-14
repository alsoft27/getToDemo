package com.getto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.getto.dto.TogetherDTO;
import com.getto.service.TogetherService;

@RestController
@RequestMapping("/togethersRest")
public class TogetherController {

    @Autowired
    private TogetherService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TogetherDTO> create(@RequestBody TogetherDTO togetherDTO) {
        service.saveTogether(togetherDTO);
        return new ResponseEntity<TogetherDTO>(togetherDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = { "name" })
    public ResponseEntity<TogetherDTO> findByName(@RequestParam String name) {
        TogetherDTO together = service.getTogetherByName(name);
        ResponseEntity<TogetherDTO> res = null;
        if (together == null) {
            res = new ResponseEntity<TogetherDTO>(together, HttpStatus.NOT_FOUND);
        } else {
            res = new ResponseEntity<TogetherDTO>(together, HttpStatus.OK);
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.GET, params = { "id" })
    public ResponseEntity<TogetherDTO> findById(@RequestParam Long id) {
        TogetherDTO tog = service.findTogetherById(id);
        ResponseEntity<TogetherDTO> res = null;
        if (tog == null) {
            res = new ResponseEntity<TogetherDTO>(tog, HttpStatus.NOT_FOUND);
        } else {
            res = new ResponseEntity<TogetherDTO>(tog, HttpStatus.OK);
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TogetherDTO>> list() {
        List<TogetherDTO> res = service.listAlltogethers();
        return new ResponseEntity<List<TogetherDTO>>(res, HttpStatus.OK);
    }

}
