package com.stp.sdweb.service;

import com.stp.sdweb.entity.ImageRecord;
import com.stp.sdweb.repository.ImageRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageRecordService {

    @Autowired
    private ImageRecordRepository repository;

    public ImageRecord saveImageRecord(ImageRecord record) {
        return repository.save(record);
    }

    public List<ImageRecord> getAllRecords() {
        return repository.findAll();
    }
}
