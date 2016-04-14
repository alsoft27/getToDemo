package com.getto.service;

import java.util.List;

import com.getto.dto.TogetherDTO;

public interface TogetherService {

    List<TogetherDTO> listAlltogethers();

    TogetherDTO findTogetherById(Long id);

    TogetherDTO getTogetherByName(String name);

    TogetherDTO saveTogether(TogetherDTO together);

    void deleteTogether(Long id);

}
