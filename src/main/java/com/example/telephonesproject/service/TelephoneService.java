package com.example.telephonesproject.service;


import com.example.telephonesproject.model.entity.Telephone;
import com.example.telephonesproject.repository.TelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TelephoneService {

    private final TelephoneRepository telephoneRepository;
    private final TextExportService textExportService;

    @Autowired
    public TelephoneService(TelephoneRepository telephoneRepository, TextExportService textExportService) {
        this.telephoneRepository = telephoneRepository;
        this.textExportService = textExportService;
    }

    // Créer un nouveau téléphone (la date sera automatiquement ajoutée)
    public Telephone createTelephone(Telephone telephone) {
        return telephoneRepository.save(telephone);
    }

    // Récupérer tous les téléphones
    public List<Telephone> getAllTelephones() {
        return telephoneRepository.findAll();
    }

    public Optional<Telephone> getByImei(String imei) {
        return telephoneRepository.findByImei(imei);
    }

    // Supprimer un téléphone
    public void deleteTelephone(Long id) {
        telephoneRepository.deleteById(id);
    }

    public Telephone updateTelephone(Telephone telephone) {
        return telephoneRepository.save(telephone);
    }




}