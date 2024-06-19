package com.QLNS._1.services;

import com.QLNS._1.entity.NhanVien;
import com.QLNS._1.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienServices {
    @Autowired
    private NhanVienRepository nhanvienRepository;

    public List<NhanVien> findAll() {
        return nhanvienRepository.findAll();
    }
    public NhanVien findById(String id) {
        return nhanvienRepository.findById(id).orElse(null);
    }

    public NhanVien save(NhanVien nhanVien) {
        return nhanvienRepository.save(nhanVien);
    }
    public void deleteById(String id) {
        nhanvienRepository.deleteById(id);
    }
}
