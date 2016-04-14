package com.getto;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getto.domain.User;
import com.getto.dto.GuestDTO;
import com.getto.dto.TogetherDTO;
import com.getto.dto.UserDTO;
import com.getto.factory.TogetherFactory;
import com.getto.service.TogetherService;
import com.getto.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GetTodemoApplication.class)
@WebAppConfiguration
public class TogetherTest {

    @Autowired
    UserService userService;

    @Autowired
    TogetherService togetherService;

    @Autowired
    TogetherFactory factory;

    @Test
    public void contextLoads() {
    }

    public TogetherDTO createTogetherDTO() {
        TogetherDTO tog = new TogetherDTO();
        // tog.setId((long) 1);
        tog.setName("quedada");
        tog.setDescription("prueba");
        tog.setPlace("el sitio");
        UserDTO creator = new UserDTO();
        creator.setId((long) 1);
        creator.setName("rick");
        creator.setEmail("rick@mail.com");
        tog.setCreator(creator);
        List<GuestDTO> guests = new ArrayList<GuestDTO>();
        GuestDTO e = new GuestDTO();
        // e.setId((long) 1);
        e.setName("Daryl");
        e.setType("wsap");
        GuestDTO b = new GuestDTO();
        // b.setId((long) 2);
        b.setName("Gleen");
        b.setType("wsap");

        guests.add(e);
        guests.add(b);
        tog.setGuests(guests);

        return tog;
    }

    @Test
    public void testTogethertoJson() throws JsonGenerationException, JsonMappingException, IOException {
        String jsonAir = "test-files/TogetherGA.json";
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(jsonAir), createTogetherDTO());
    }

    // @Test
    public void createTogether() {
        TogetherDTO togetherDTO = createTogetherDTO();
        User creator = new User();
        // creator.setId((long) 1);
        creator.setName("rick");
        creator.setEmail("rick@mail.com");
        userService.saveUser(creator);
        togetherService.saveTogether(togetherDTO);
        TogetherDTO tog = togetherService.getTogetherByName("quedada");
        System.out.println(tog);
        assertNotNull(tog);
    }
}
