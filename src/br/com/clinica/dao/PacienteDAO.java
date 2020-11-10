/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.clinica.dao;

import br.com.clinica.jdbc.ConnectionFactory;
import br.com.clinica.model.Paciente;
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
public class PacienteDAO {

    private Connection con;

    public PacienteDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarPaciente(Paciente obj) {
        try {
            String sql = "insert into pacientes (nome_paciente, cpf_paciente, idade_paciente,"
                    + " telefone_paciente, endereco_paciente, email_paciente) "
                    + "values(?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCpf());
            stmt.setInt(3, obj.getIdade());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getEndereco());
            stmt.setString(6, obj.getEmail());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
    }

    public List<Paciente> listarPacientes() {
        try {
            List<Paciente> lista = new ArrayList<>();
            String sql = "SELECT * FROM pacientes";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente obj = new Paciente();
                obj.setId(rs.getInt("id_paciente"));
                obj.setNome(rs.getString("nome_paciente"));
                obj.setIdade(rs.getInt("idade_paciente"));
                obj.setCpf(rs.getString("cpf_paciente"));
                obj.setEmail(rs.getString("email_paciente"));
                obj.setTelefone(rs.getString("telefone_paciente"));
                obj.setEndereco(rs.getString("endereco_paciente"));

                lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    public void alterarCliente(Paciente obj) {
        try {
            String sql = "update pacientes set nome_paciente=?, cpf_paciente=?, idade_paciente=?,"
                    + " telefone_paciente=?, endereco_paciente=?, email_paciente=? where id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCpf());
            stmt.setInt(3, obj.getIdade());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getEndereco());
            stmt.setString(6, obj.getEmail());
            stmt.setInt(7, obj.getId());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
    }

    public void excluirPaciente(Paciente obj) {
        try {
            String sql = "delete from pacientes where id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
    }

    public List<Paciente> buscarPacientes(String nome) {
        try {
            List<Paciente> lista = new ArrayList<>();
            String sql = "SELECT * FROM pacientes WHERE nome_paciente like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente obj = new Paciente();
                obj.setId(rs.getInt("id_paciente"));
                obj.setNome(rs.getString("nome_paciente"));
                obj.setIdade(rs.getInt("idade_paciente"));
                obj.setCpf(rs.getString("cpf_paciente"));
                obj.setEmail(rs.getString("email_paciente"));
                obj.setTelefone(rs.getString("telefone_paciente"));
                obj.setEndereco(rs.getString("endereco_paciente"));

                lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }
}
