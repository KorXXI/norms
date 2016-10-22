package br.ages.crud.dao;

import br.ages.crud.exception.PersistenciaException;
import br.ages.crud.model.Ficha;
import br.ages.crud.model.FichaItem;
import br.ages.crud.util.ConexaoUtil;
import br.ages.crud.util.MensagemContantes;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alessandro
 */

public class FichaSimplificadaDAO {

    private List<Ficha> listarFichasSimplificada;

    public int cadastrarFichaSimplificada(Ficha fichaSimplificada) throws SQLException, PersistenciaException {
        Connection conexao = null;

        try {
            Integer idFichaSimplificada = null;

            conexao = ConexaoUtil.getConexao();
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO TB_FICHA_SIMPLIFICADA (nome, rendimento, foto, modo_preparo, montagem, orientacoes_armazenamento, tipo_ficha)");
            sql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");

            PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, fichaSimplificada.getNome());
            statement.setString(2, fichaSimplificada.getRendimento());
            statement.setString(3, fichaSimplificada.getFoto());
            statement.setString(4, fichaSimplificada.getModoPreparo());
            statement.setString(5, fichaSimplificada.getMontagem());
            statement.setString(6, fichaSimplificada.getOrientacoesArmazenamento());
            statement.setString(7, fichaSimplificada.getTipoFicha());

            statement.executeUpdate();

            ResultSet resultset = statement.getGeneratedKeys();
            if (resultset.first()) {
                idFichaSimplificada = resultset.getInt(1);
            }

            fichaSimplificada.setIdFicha(idFichaSimplificada);

            StringBuilder sql2 = new StringBuilder();
            for (FichaItem fichaItem : fichaSimplificada.getItens()) {
                sql2.append("INSERT INTO TB_FICHA_ITEM (ID_UNIDADE_MEDIDA, ID_MEDIDA_CASEIRA, ID_INGREDIENTE, ID_FICHA, QUANTIDADE_UNIDADE_MEDIDA,QUANTIDADE_MEDIDA_CASEIRA )");
                sql.append("VALUES (?, ?, ?, ?, ?, ?)");

                PreparedStatement statement2 = conexao.prepareStatement(sql2.toString(), Statement.RETURN_GENERATED_KEYS);

                statement2.setInt(1, fichaItem.getIdUnidadeMedida());
                statement2.setInt(2, fichaItem.getIdMedidaCaseira());
                statement2.setInt(3, fichaItem.getIdIngrediente());
                statement2.setInt(4, idFichaSimplificada);
                statement2.setInt(5, fichaItem.getQuantidadeUnidadeMedida());
                statement2.setInt(6, fichaItem.getQuantidadeMedidaCaseira());

                statement2.executeUpdate();

                ResultSet resultset2 = statement.getGeneratedKeys();
                int idFichaItem = 0;
                if (resultset.first()) {
                    idFichaItem = resultset2.getInt(1);
                }
                fichaItem.setIdFicha(idFichaItem);
            }


            return idFichaSimplificada;

        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenciaException(MensagemContantes.MSG_ERR_FICHA_SIMPLIFICADA_JA_EXISTENTE.replace("?", fichaSimplificada.getNome()));

        } finally {
            conexao.close();
        }
    }

    public List<Ficha> listarFichasSimplificada() throws PersistenciaException, SQLException {
        Connection conexao = null;
        listarFichasSimplificada = new ArrayList<>();
        try {

            conexao = ConexaoUtil.getConexao();

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ID_FICHA, NOME, RENDIMENTO, FOTO, MODO_PREPARO, MONTAGEM, ORIENTACOES_ARMAZENAMENTO, TIPO_FICHA FROM TB_FICHA WHERE TIPO_FICHA = 's' ");

            PreparedStatement statement = conexao.prepareStatement(sql.toString());
            ResultSet resultset = statement.executeQuery();
            while (resultset.next()) {
                Ficha dto = new Ficha();
                dto.setIdEmpresa(resultset.getInt("ID_FICHA"));
                dto.setNome(resultset.getString("NOME"));
                dto.setRendimento(resultset.getString("RENDIMENTO"));
                dto.setFoto(resultset.getString("FOTO"));
                dto.setModoPreparo(resultset.getString("MODO_PREPARO"));
                dto.setMontagem(resultset.getString("MONTAGEM"));
                dto.setOrientacoesArmazenamento(resultset.getString("ORIENTACOES_ARMAZENAMENTO"));
                dto.setTipoFicha(resultset.getString("TIPO_FICHA"));

                listarFichasSimplificada.add(dto);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenciaException(e);

        } finally {
            conexao.close();
        }
        return listarFichasSimplificada;
    }

    public boolean editarFichaSimplificada(Ficha fichaSimplificada) throws PersistenciaException {
        boolean okei = false;
        Connection conexao = null;
        try {
            conexao = ConexaoUtil.getConexao();
            StringBuilder sql = new StringBuilder();
            int id = fichaSimplificada.getIdFicha();

            sql.append(
                    "UPDATE TB_FICHA_SIMPLIFICADA set nome = ?, rendimento = ?, foto = ?, modo_preparo = ?, montagem = ?, orientacaoes_armazenamento = ?, tipo_ficha = ? WHERE id_usuario = "
                            + id + ";");

            PreparedStatement statement = conexao.prepareStatement(sql.toString());
        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenciaException(e);
        } finally {
            try {
                conexao.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return okei;
    }

    public boolean removerFichaSimplificada(Integer idFicha) throws PersistenciaException {
        boolean removidoOK = false;
        Connection conexao = null;

        try {

            conexao = ConexaoUtil.getConexao();
            StringBuilder sql = new StringBuilder();

            sql.append("DELETE FROM TB_FICHA WHERE ID_FICHA = ?");
            PreparedStatement statement = conexao.prepareStatement(sql.toString());

            statement.setInt(1, idFicha);
            removidoOK = statement.execute();

        } catch (ClassNotFoundException | SQLException e) {
            throw new PersistenciaException(e);
        } finally {
            try {
                conexao.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return removidoOK;
    }
}