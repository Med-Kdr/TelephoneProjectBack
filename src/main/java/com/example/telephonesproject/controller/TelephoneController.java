package com.example.telephonesproject.controller;


import com.example.telephonesproject.model.entity.Telephone;
import com.example.telephonesproject.service.TelephoneService;
import com.example.telephonesproject.service.TextExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/api/telephones")
@CrossOrigin("*")
public class TelephoneController {

    private final TelephoneService telephoneService;
    private final TextExportService textExportService;

    @Autowired
    public TelephoneController(TelephoneService telephoneService, TextExportService textExportService) {
        this.telephoneService = telephoneService;
        this.textExportService = textExportService;
    }

    // Créer un nouveau téléphone
    @PostMapping("/addTelephone")
    public ResponseEntity<Telephone> createTelephone(@RequestBody Telephone telephone) throws IOException {
        Telephone savedTelephone = telephoneService.createTelephone(telephone);
        this.updateFile();
        return new ResponseEntity<>(savedTelephone, HttpStatus.CREATED);
    }

    // Récupérer tous les téléphones
    @GetMapping("/getAllTelephones")
    public ResponseEntity<List<Telephone>> getAllTelephones() throws IOException {
        List<Telephone> telephones = telephoneService.getAllTelephones();
        textExportService.exportToTextFile(telephones, "telephones.txt");
        return new ResponseEntity<>(telephones, HttpStatus.OK);
    }

    // Récupérer un téléphone par son IMEI
    @GetMapping("/imei/{imei}")
    public ResponseEntity<Telephone> getTelephoneByImei(@PathVariable String imei) {
        Optional<Telephone> telephone = telephoneService.getByImei(imei);
        return telephone.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Supprimer un téléphone par son ID
    @DeleteMapping("/deleteTelephoneById/{id}")
    public ResponseEntity<Void> deleteTelephone(@PathVariable Long id) throws IOException {
        telephoneService.deleteTelephone(id);

        this.updateFile();


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @PutMapping("/updateTelephoneById/{id}")
    public Telephone updateTelephone(@PathVariable Long id, @RequestBody Telephone telephone) throws IOException {
        telephone.setId(id);
        Telephone telephone1 = telephoneService.updateTelephone(telephone);
        this.updateFile();
        return telephone1;
    }












    @GetMapping("/export-text")
    public ResponseEntity<Resource> exportToTextFile() throws IOException {
        List<Telephone> telephones = telephoneService.getAllTelephones();
        String fileName = "telephones.txt";

        textExportService.exportToTextFile(telephones, fileName);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(fileName));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.TEXT_PLAIN)
                .body(resource);
    }

    public void updateFile() throws IOException {
        List<Telephone> telephones = telephoneService.getAllTelephones();
        String fileName = "telephones.txt";

        textExportService.exportToTextFile(telephones, fileName);
    }


}