/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.clinica.dao;

import br.com.clinica.jdbc.ConnectionFactory;
import br.com.clinica.model.Medico;
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
public class MedicoDAO {

    private Connection con;

    public MedicoDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarMedico(Medico obj) {
        try {
            String sql = "insert into medicos (nome_medico, crm_medico, telefone_medico,"
                    + " endereco_medico, especialidade_medico, email_medico, idade_medico) "
                    + "values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCrm());
            stmt.setString(3, obj.getTelefone());
            stmt.setString(4, obj.getEndereco());
            stmt.setString(5, obj.getEspecialidade());
            stmt.setString(6, obj.getEmail());
            stmt.setInt(7, obj.getIdade());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
    }

    public List<Medico> listarMedicos() {
        try {
            List<Medico> lista = new ArrayList<>();
            String sql = "SELECT * FROM medicos";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Medico obj = new Medico();
                obj.setId(rs.getInt("id_medico"));
                obj.setNome(rs.getString("nome_medico"));
                obj.setIdade(rs.getInt("idade_medico"));
                obj.setCrm(rs.getString("crm_medico"));
                obj.setEmail(rs.getString("email_medico"));
                obj.setTelefone(rs.getString("telefone_medico"));
                obj.setEndereco(rs.getString("endereco_medico"));
                obj.setEspecialidade(rs.getString("especialidade_medico"));

                lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    public void alterarMedico(Medico obj) {
        try {
            String sql = "update medicos set nome_medico=?, crm_medico=?, telefone_medico=?,"
                    + " endereco_medico=?, especialidade_medico=?, email_medico=?, idade_medico=? where id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCrm());
            stmt.setString(3, obj.getTelefone());
            stmt.setString(4, obj.getEndereco());
            stmt.setString(5, obj.getEspecialidade());
            stmt.setString(6, obj.getEmail());
            stmt.setInt(7, obj.getIdade());
            stmt.setInt(8, obj.getId());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
    }

    public void excluirMedico(Medico obj) {
        try {
            String sql = "delete from medicos where id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
    }

    public List<Medico> buscarMedicos(String nome) {
        try {
            List<Medico> lista = new ArrayList<>();
            String sql = "SELECT * FROM medicos WHERE nome_medico like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Medico obj = new Medico();
                obj.setId(rs.getInt("id_medico"));
                obj.setNome(rs.getString("nome_medico"));
                obj.setIdade(rs.getInt("idade_medico"));
                obj.setCrm(rs.getString("crm_medico"));
                obj.setEmail(rs.getString("email_medico"));
                obj.setTelefone(rs.getString("telefone_medico"));
                obj.setEndereco(rs.getString("endereco_medico"));
                obj.setEspecialidade(rs.getString("especialidade_medico"));

                lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }
}
