package com.QLNS._1.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Name")
@Data

public class NhanVien {
    @Id
    @Column(name = "Ma_NV",length = 3,nullable = false)
    private String maNV;

    @Column(name = "Ten_NV",length = 100,nullable = false )
    private String tenNV;

    @Column(name = "Phai",length = 3)
    private String phai;

    @Column(name = "Noi_Sinh",length = 200)
    private String noiSinh;

    @ManyToOne
    @JoinColumn(name = "Ma_Phong",nullable = false)
    private PhongBan phongBan;

    @Column(name = "luong")
    private String luong;
}
