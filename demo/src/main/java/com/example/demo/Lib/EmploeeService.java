package com.example.demo.Lib;

import com.example.demo.Models.Emploee;
import com.example.demo.Types.createEmploeeTemplate;
import com.example.demo.Types.loginTemplate;
import com.example.demo.Types.quizeTemplate;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repo.EmploeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EmploeeService {
    public EmploeeRepo emploeeRepo;
    @Autowired
    public EmploeeService(EmploeeRepo emploeeRepo) {
        this.emploeeRepo = emploeeRepo;
    }

    public  Emploee findEmploee(Long id){
        Optional<Emploee> emploee = emploeeRepo.findById(id);
        if (emploee.isEmpty())
            throw new NotFoundException();

        return emploee.get();
    }

    public Emploee findEmploeeByLoginData(loginTemplate data){
        List<Emploee> list = emploeeRepo.findAll();
        for (Emploee emploee: list) {
            if(emploee.getLogin().equals(data.login) && emploee.getPassword().equals(data.password))
                return emploee;
        }
        throw new NotFoundException();
        //return new Emploee();

    }
    public List<Emploee> AllEmploee() {
        return emploeeRepo.findAll();
    }

    public Emploee addEmploeeToDB(createEmploeeTemplate data) {
        return emploeeRepo.save(new Emploee(data.login, data.password, data.name, data.lastname));
    }

    public Emploee FillEmploeeRisk(quizeTemplate data)
    {
        Emploee emploee = findEmploee(data.id);
        if(!emploee.isFill()) {
            emploee.setBio(data.bio);
            emploee.setMoral(data.moral);
            emploee.setPsycho(data.psy);
            emploee.setIntellectual(data.intel);
            emploee.setUnreability(data.unreab);
            emploee.setFill(true);
            emploeeRepo.save(emploee);
        }
        return emploee;
    }

    public void removeEmploee(Long id){
        emploeeRepo.delete(findEmploee(id));
    }
}
