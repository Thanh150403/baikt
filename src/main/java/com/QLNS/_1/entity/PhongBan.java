package com.QLNS._1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "PhongBan")
@Data
public class PhongBan {
    @Id
    @Column(name = "Ma_Phong",length = 2,nullable = false)
    private String maPhong;

    @Column(name = "Ten_Phong",length = 30,nullable = false)
    private String tenPhong;

    @OneToMany(mappedBy = "phongBan")
    private Set<NhanVien> nhanVien;
}
