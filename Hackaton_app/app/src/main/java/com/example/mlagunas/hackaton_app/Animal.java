package com.example.mlagunas.hackaton_app;

/**
 * Created by razvan on 11/07/15.
 */
public class Animal {

    private int id;
    private String ficha, raza, fechaIngreso, sexo, edad, tamanio, foto,
    nombre, especie, color, perdido, disponible, observaciones, fechaNacimiento;

    public Animal(String ficha, String fechaNacimiento,
                  String perdido, String tamanio,
                  String sexo, String fechaIngreso, String observaciones,
                  String disponible, int id, String raza,
                  String nombre, String color,
                  String edad, String foto, String especie) {
        this.id = id;
        this.ficha = ficha;
        this.raza = raza;
        this.fechaIngreso = fechaIngreso;
        this.sexo = sexo;
        this.edad = edad;
        this.tamanio = tamanio;
        this.foto = foto;
        this.nombre = nombre;
        this.especie = especie;
        this.color = color;
        this.perdido = perdido;
        this.disponible = disponible;
        this.observaciones = observaciones;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }

    public String getFicha() {
        return ficha;
    }

    public String getRaza() {
        return raza;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEdad() {
        return edad;
    }

    public String getTamanio() {
        return tamanio;
    }

    public String getFoto() {
        return foto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public String getColor() {
        return color;
    }

    public String getPerdido() {
        return perdido;
    }

    public String getDisponible() {
        return disponible;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
}
