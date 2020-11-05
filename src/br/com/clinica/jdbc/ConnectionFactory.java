/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.clinica.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author nneto
 */
public class ConnectionFactory {

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicamedica?useTimeZone=true&serverTimezone=UTC", "root", "1a3fbd457e");
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }
}
