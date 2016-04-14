package com.getto.factory;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import com.getto.domain.Guest;
import com.getto.domain.Together;
import com.getto.dto.GuestDTO;
import com.getto.dto.TogetherDTO;

@Component
public class TogetherFactory {

    private Mapper mapper;

    public TogetherFactory() {
        this.mapper = new DozerBeanMapper();
    }

    public Together convertToTogether(TogetherDTO togetherDTO) {
        Together together = this.mapper.map(togetherDTO, Together.class);
        if (together.getGuests() != null) {
            for (Guest guest : together.getGuests()) {
                guest.setTogether(together);
            }
        }
        return together;
    }

    public TogetherDTO convertToTogetherDTO(Together together) {
        TogetherDTO togetherDTO = this.mapper.map(together, TogetherDTO.class);
        return togetherDTO;
    }

    public GuestDTO convertToGuestDTO(Guest guest) {
        return this.mapper.map(guest, GuestDTO.class);
    }

    public Guest convertToGuest(GuestDTO guestDTO) {
        return this.mapper.map(guestDTO, Guest.class);
    }
}
