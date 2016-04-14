package com.getto.service;

import com.getto.dto.GuestDTO;
import com.getto.dto.TogetherDTO;

public interface GuestService {

    void addGuest(GuestDTO guest);

    void saveAnswer(GuestDTO guestDTO);

    TogetherDTO findTogetherByIdGuest(Long guest);

    GuestDTO findGuestById(Long id);

}
