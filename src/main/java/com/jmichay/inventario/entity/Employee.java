package com.jmichay.inventario.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.jmichay.inventario.utils.EcuadorianIdentification;

import lombok.Data;

@Entity
@Data
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Long id;

    @NotNull(message = "La identificación es obligatoria")
    @Pattern(regexp = "[0-9]{10}+$", message = "La identificación debe tener 10 dígitos y solo numeros")
    @EcuadorianIdentification
    @Column(name = "identification", nullable = false, length = 10, unique = true)
    private String identification;

    @NotNull(message = "El nombre es obligatorio")
    @Pattern(regexp = "[A-Za-z ]+$", message = "El nombre solo puede contener letras")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "El apellido es obligatorio")
    @Pattern(regexp = "[A-Za-z ]+$", message = "El apellido solo puede contener letras")
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Pattern(regexp = "(([^<>()\\[\\]\\\\.,;:\\s@”]+(\\.[^<>()\\[\\]\\\\.,;:\\s@”]+)*)|(“.+”))@((\\[[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}])|(([a-zA-Z\\-0–9]+\\.)+[a-zA-Z]{2,}))$", message = "Formato de correo incorrecto")
    @NotNull(message = "El Correo es obligatorio")
    @Email(message = "Debe ser un correo valido")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    @DateTimeFormat(pattern = " yyyy-MM-dd")
    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "address")
    private String address;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "vaccinated")
    private Boolean vaccinated;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_vaccine")
    private Vaccine vaccine;

    @DateTimeFormat(pattern = " yyyy-MM-dd")
    @Column(name = "vaccination_date")
    private Date vaccinationDate;

    @Column(name = "doses")
    private int doses;
}
