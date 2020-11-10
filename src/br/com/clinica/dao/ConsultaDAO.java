/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.clinica.dao;

import br.com.clinica.jdbc.ConnectionFactory;
import br.com.clinica.model.Consulta;
import br.com.clinica.model.Medico;
import br.com.clinica.model.Paciente;
import br.com.clinica.model.Prescricao;
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
public class ConsultaDAO {

    private Connection con;

    public ConsultaDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarConsulta(Consulta obj) {
        try {
            String sql = "insert into consultas (data_consulta, valor_consulta, status_consulta,"
                    + " descricao_consulta, paciente_consulta, medico_consulta, procedimento_consulta, "
                    + " prescricao_consulta, horario_consulta) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getData());
            stmt.setDouble(2, obj.getValor());
            stmt.setString(3, obj.getStatus());
            stmt.setString(4, obj.getDescricao());
            stmt.setInt(5, obj.getPaciente().getId());
            stmt.setInt(6, obj.getMedico().getId());
            stmt.setInt(7, obj.getProcedimento().getId());
            stmt.setInt(8, obj.getPrescricao().getId());
            stmt.setString(9, obj.getHorario());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
    }

    public List<Consulta> listarConsultas() {
        try {
            List<Consulta> lista = new ArrayList<>();
            String sql = "SELECT c.* FROM consultas as c "
                    + "inner join medicos as m on(c.medico_consulta = m.id_medico)";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta obj = new Consulta();
                Medico medico = new Medico();
                Paciente paciente = new Paciente();
                Procedimento procedimento = new Procedimento();
                Prescricao prescricao = new Prescricao();

                medico.setId(rs.getInt("medico_consulta"));
                paciente.setId(rs.getInt("paciente_consulta"));
                procedimento.setId(rs.getInt("procedimento_consulta"));
                prescricao.setId(rs.getInt("prescricao_consulta"));

                obj.setId(rs.getInt("id_consulta"));
                obj.setData(rs.getString("data_consulta"));
                obj.setValor(rs.getDouble("valor_consulta"));
                obj.setStatus(rs.getString("status_consulta"));
                obj.setDescricao(rs.getString("descricao_consulta"));
                obj.setMedico(medico);
                obj.setPaciente(paciente);
                obj.setProcedimento(procedimento);
                obj.setPrescricao(prescricao);
                obj.setHorario("horario_consulta");

                lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    public void alterarConsulta(Consulta obj) {
        try {
            String sql = "update consultas set data_consulta=?, valor_consulta=?, status_consulta=?,"
                    + " descricao_consulta=?, paciente_consulta=?, medico_consulta=?, "
                    + "procedimento_consulta=?, prescricao_consulta=?, horario_consulta=? where id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getData());
            stmt.setDouble(2, obj.getValor());
            stmt.setString(3, obj.getStatus());
            stmt.setString(4, obj.getDescricao());
            stmt.setInt(5, obj.getPaciente().getId());
            stmt.setInt(6, obj.getMedico().getId());
            stmt.setInt(7, obj.getProcedimento().getId());
            stmt.setInt(8, obj.getPrescricao().getId());
            stmt.setString(9, obj.getHorario());
            stmt.setInt(10, obj.getId());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
    }

    public void excluirConsulta(Consulta obj) {
        try {
            String sql = "delete from consultas where id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
    }

    public List<Consulta> buscarConsultas(String data) {
        try {
            List<Consulta> lista = new ArrayList<>();
            String sql = "SELECT * FROM consultas WHERE data_consulta like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta obj = new Consulta();
                Medico medico = new Medico();
                Paciente paciente = new Paciente();
                Procedimento procedimento = new Procedimento();
                Prescricao prescricao = new Prescricao();

                medico.setId(rs.getInt("medico_consulta"));
                paciente.setId(rs.getInt("paciente_consulta"));
                procedimento.setId(rs.getInt("procedimento_consulta"));
                prescricao.setId(rs.getInt("prescricao_consulta"));

                obj.setId(rs.getInt("id_consulta"));
                obj.setData(rs.getString("data_consulta"));
                obj.setValor(rs.getDouble("valor_consulta"));
                obj.setStatus(rs.getString("status_consulta"));
                obj.setDescricao(rs.getString("descricao_consulta"));
                obj.setMedico(medico);
                obj.setPaciente(paciente);
                obj.setProcedimento(procedimento);
                obj.setPrescricao(prescricao);
                obj.setHorario("horario_consulta");

                lista.add(obj);
            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }
}
