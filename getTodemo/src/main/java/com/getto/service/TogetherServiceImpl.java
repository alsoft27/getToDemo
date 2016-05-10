package com.getto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getto.domain.Together;
import com.getto.dto.TogetherDTO;
import com.getto.factory.TogetherFactory;
import com.getto.repository.TogetherRepository;

@Service
public class TogetherServiceImpl implements TogetherService {

    @Autowired
    private TogetherRepository togetherRepository;

    @Autowired
    private TogetherFactory factory;

    // @Autowired
    // private EmailSender emailSender;

    @Override
    public List<TogetherDTO> listAlltogethers() {
        Iterable<Together> togs = togetherRepository.findAll();
        List<TogetherDTO> togsDTO = new ArrayList<TogetherDTO>();
        for (Together tog : togs) {
            togsDTO.add(factory.convertToTogetherDTO(tog));
        }
        return togsDTO;
    }

    @Override
    public TogetherDTO findTogetherById(Long id) {
        Together tog = togetherRepository.findOne(id);
        return factory.convertToTogetherDTO(tog);
    }

    @Override
    public TogetherDTO getTogetherByName(String name) {
        Together tog = togetherRepository.findByName(name);
        return factory.convertToTogetherDTO(tog);
    }

    @Override
    public TogetherDTO saveTogether(TogetherDTO togetherDTO) {
        Together tog = factory.convertToTogether(togetherDTO);
        tog = togetherRepository.save(tog);

        // send email
        // try {
        // emailSender.send("alsoft27@gmail.com", "geto", "te invito a
        // participar http://localhost:8080/togethers/1/1");
        // } catch (MessagingException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        return factory.convertToTogetherDTO(tog);
    }

    @Override
    public void deleteTogether(Long id) {
        togetherRepository.delete(id);
    }

}
