package com.QLNS._1.services;

import com.QLNS._1.entity.NhanVien;
import com.QLNS._1.entity.PhongBan;
import com.QLNS._1.repository.PhongBanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhongBanServices {
    @Autowired
    private PhongBanRepository phongBanRepository;

    public List <PhongBan> findAll() {
        return phongBanRepository.findAll();
    }

    public PhongBan findById(String id) {
        return phongBanRepository.findById(id).orElse(null);
    }

    public PhongBan save(PhongBan phongBan) {
        return phongBanRepository.save(phongBan);
    }
    public void deleteById(String id) {
        phongBanRepository.deleteById(id);
    }
}

