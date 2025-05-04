package com.example.telephonesproject.service;

import com.example.telephonesproject.model.entity.Telephone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class TextExportService {


    public void exportToTextFile(List<Telephone> telephones, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // En-tête
            writer.write("ID | iPhone | État Batterie | IMEI | Stockage | Prix | Nom Client | Statut | Date Création");
            writer.newLine();
            writer.write("------------------------------------------------------------");
            writer.newLine();

            // Données
            for (Telephone tel : telephones) {
                writer.write(String.format(
                        "%d | %s | %s | %s | %s | %d | %s | %s | %s",
                        tel.getId(),
                        tel.getIphone(),
                        tel.getEtatBatterie(),
                        tel.getImei(),
                        tel.getStockage(),
                        tel.getPrix() != null ? tel.getPrix() : 0,
                        tel.getNom(),
                        tel.getStatut(),
                        tel.getDateCreation()
                ));
                writer.newLine();
            }
        }
    }


}