/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.clinica.dao;

import br.com.clinica.jdbc.ConnectionFactory;
import br.com.clinica.model.Procedimento;
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
public class ProcedimentoDAO {

    private Connection con;

    public ProcedimentoDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarProcedimento(Procedimento obj) {
        try {
            String sql = "insert into procedimentos (nome_procedimento, descricao_procedimento) "
                    + "values(?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getDescricao());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
    }

    public List<Procedimento> listarProcedimentos() {
        try {
            List<Procedimento> lista = new ArrayList<>();
            String sql = "SELECT * FROM procedimentos";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Procedimento obj = new Procedimento();
                obj.setId(rs.getInt("id_procedimento"));
                obj.setNome(rs.getString("nome_procedimento"));
                obj.setDescricao(rs.getString("descricao_procedimento"));

                lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    public void alterarProcedimento(Procedimento obj) {
        try {
            String sql = "update procedimentos set nome_procedimento=?, descricao_procedimento=?"
                    + " where id_procedimento=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getDescricao());
            stmt.setInt(3, obj.getId());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
    }

    public void excluirProcedimento(Procedimento obj) {
        try {
            String sql = "delete from procedimentos where id_procedimento=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
    }

    public List<Procedimento> buscarProcedimentos(String nome) {
        try {
            List<Procedimento> lista = new ArrayList<>();
            String sql = "SELECT * FROM procedimentos WHERE nome_procedimento like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Procedimento obj = new Procedimento();
                obj.setId(rs.getInt("id_procedimento"));
                obj.setNome(rs.getString("nome_procedimento"));
                obj.setDescricao(rs.getString("descricao_procedimento"));

                lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }
}
