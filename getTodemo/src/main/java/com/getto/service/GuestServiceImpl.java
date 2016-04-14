package com.getto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getto.domain.Guest;
import com.getto.domain.Together;
import com.getto.dto.GuestDTO;
import com.getto.dto.TogetherDTO;
import com.getto.factory.TogetherFactory;
import com.getto.repository.GuestRepository;
import com.getto.repository.TogetherRepository;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private TogetherRepository togetherRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    TogetherFactory togetherFactory;

    @Override
    public void addGuest(GuestDTO guestDTO) {
        Guest guest = togetherFactory.convertToGuest(guestDTO);
        guestRepository.save(guest);
    }

    @Override
    public void saveAnswer(GuestDTO guestDTO) {
        Guest guest = togetherFactory.convertToGuest(guestDTO);
        guestRepository.save(guest);
    }

    @Override
    public GuestDTO findGuestById(Long id) {
        return togetherFactory.convertToGuestDTO(guestRepository.findOne(id));
    }

    @Override
    public TogetherDTO findTogetherByIdGuest(Long guest) {
        Together tog = togetherRepository.findOne(guest);
        TogetherDTO togDTO = togetherFactory.convertToTogetherDTO(tog);
        return togDTO;
    }

}
