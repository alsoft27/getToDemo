package com.getto.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.getto.domain.Guest;
import com.getto.domain.Together;
import com.getto.dto.TogetherDTO;
import com.getto.factory.TogetherFactory;
import com.getto.infrastructure.EmailSender;
import com.getto.repository.TogetherRepository;

@Service
public class TogetherServiceImpl implements TogetherService {

    @Autowired
    private TogetherRepository togetherRepository;

    @Autowired
    private TogetherFactory factory;

    @Autowired
    private EmailSender emailSender;

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
        for (Guest guest : tog.getGuests()) {
            try {
                emailSender.send(guest.getEmail(), "Ge to",
                        "http://demo-alsoft27.boxfuse.io:8081/togethers/" + tog.getId().toString() + "/" + guest.getId().toString());
            } catch (MailException | MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return factory.convertToTogetherDTO(tog);
    }

    @Override
    public void deleteTogether(Long id) {
        togetherRepository.delete(id);
    }

}
