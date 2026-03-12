/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.caso1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Jason
 */
@Data
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Integer idUsuario;
    
    private String nombre;
    private String email;
    private String password;
    private String telefono;
    
    @Enumerated(EnumType.STRING)
    private Rol rol;
    
    public enum Rol {
        admin, vendedor, cliente
    }
    
}
