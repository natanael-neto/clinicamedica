/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.clinica.model;

/**
 *
 * @author nneto
 */
public class Prescricao {
    private int id;
    private String medicamento;
    private String formaDeUso;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getFormaDeUso() {
        return formaDeUso;
    }

    public void setFormaDeUso(String formaDeUso) {
        this.formaDeUso = formaDeUso;
    }
    
    
}
