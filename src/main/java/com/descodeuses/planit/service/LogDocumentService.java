package com.descodeuses.planit.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.descodeuses.planit.entity.LogDocument;
import com.descodeuses.planit.repository.LogDocumentRepository;

@Service
public class LogDocumentService {
    @Autowired
    private LogDocumentRepository repo;

    public void addLog(String text){
        LogDocument doc = new LogDocument();
        doc.setText(text);
        doc.setTimestamp(LocalDateTime.now());
        repo.save(doc);
    }


}
