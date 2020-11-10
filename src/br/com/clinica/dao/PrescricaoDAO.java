/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.clinica.dao;

import br.com.clinica.jdbc.ConnectionFactory;
import br.com.clinica.model.Prescricao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author nneto
 */
public class PrescricaoDAO {

    private Connection con;

    public PrescricaoDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarPrescricao(Prescricao obj) {
        try {
            String sql = "insert into prescricoes (medicamento_prescricao, forma_uso_prescricao) "
                    + "values(?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getMedicamento());
            stmt.setString(2, obj.getFormaDeUso());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
    }

    public List<Prescricao> listarPrescricoes() {
        try {
            List<Prescricao> lista = new ArrayList<>();
            String sql = "SELECT * FROM prescricoes";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Prescricao obj = new Prescricao();
                obj.setId(rs.getInt("id_paciente"));
                obj.setMedicamento(rs.getString("medicamento_prescricao"));
                obj.setFormaDeUso(rs.getString("forma_uso_prescricao"));

                lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    public void alterarPrescricao(Prescricao obj) {
        try {
            String sql = "update pacientes set medicamento_prescricao=?, forma_uso_prescricao=? where id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getMedicamento());
            stmt.setString(2, obj.getFormaDeUso());
            stmt.setInt(3, obj.getId());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
    }

    public void excluirPrescricao(Prescricao obj) {
        try {
            String sql = "delete from prescricoes where id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
    }

    public List<Prescricao> buscarPrescricoes(String medicamento) {
        try {
            List<Prescricao> lista = new ArrayList<>();
            String sql = "SELECT * FROM prescricoes WHERE medicamento_prescricao like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, medicamento);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Prescricao obj = new Prescricao();
                obj.setId(rs.getInt("id_paciente"));
                obj.setMedicamento(rs.getString("medicamento_prescricao"));
                obj.setFormaDeUso(rs.getString("forma_uso_prescricao"));

                lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }
}
