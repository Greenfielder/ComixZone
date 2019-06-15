package ru.comixzone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.comixzone.entities.Comix;
import ru.comixzone.repositories.ComixRepository;

import java.util.List;

/**
 * Created by Mickey on 15.06.2019.
 */

@Service
public class ComixService {
    private ComixService comixService;
    private ComixRepository comixRepository;

    @Autowired
    public void setComixRepository(ComixRepository comixRepository) {
        this.comixRepository = comixRepository;
    }

    public List<Comix> getAllComixList() {
        return (List<Comix>)comixRepository.findAll();
    }

    public void addComix(Comix comix) {
        comixRepository.save(comix);
    }

    public ComixService() {
    }

}
