package br.ages.crud.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.ages.crud.bo.IngredienteBO;
import br.ages.crud.dao.IngredienteDAO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.model.Ingrediente;
import br.ages.crud.util.MensagemContantes;


public class EditIngredienteCommand implements Command{
	
	private String proxima;
	
	private IngredienteBO ingredienteBO;
	
	@Override
	public String execute(HttpServletRequest request) throws SQLException {
		ingredienteBO =  new IngredienteBO();
		proxima = "main?acao=telaIngredientes";
		//proxima = "Ingredientes/telaIngredientes.jsp";
		
		String id = request.getParameter("idIngrediente");
		String codigo = request.getParameter("cod");
		String descricao = request.getParameter("descricao");
		String carboidrato = request.getParameter("carboidratos");
		String kcalCarboidrato = request.getParameter("kcalcarboidratos");
		String proteinas = request.getParameter("proteinas");
		String kcalProteinas = request.getParameter("kcalproteinas");
		String lipidios = request.getParameter("lipidios");
		String kcalLipidios = request.getParameter("kcallipidios");
		String fatorCorrecao = request.getParameter("fatorcorrecao"); 
		String indiceCoccao = request.getParameter("indicecoccao");
		String custo = request.getParameter("custo");
		String unidadeMedida = request.getParameter("unidadeMedida");
		
		
		try{				
			
			Ingrediente ingrediente = new Ingrediente();
			ingrediente.setId(Integer.valueOf(id));
			ingrediente.setCodigo(Integer.valueOf(codigo));
			ingrediente.setDescricao(descricao);
			ingrediente.setCarboidratos(Double.valueOf(carboidrato));
			ingrediente.setKcalCarboidratos(Double.valueOf(kcalCarboidrato));
			ingrediente.setProteinas(Double.valueOf(proteinas));
			ingrediente.setKcalProteinas(Double.valueOf(kcalProteinas));
			ingrediente.setLipidios(Double.valueOf(lipidios));
			ingrediente.setKcalLipidios(Double.valueOf(kcalLipidios));
			ingrediente.setFatorCorrecao(Double.valueOf(fatorCorrecao));
			ingrediente.setIndiceCoccao(Double.valueOf(indiceCoccao));
			ingrediente.setCusto(Double.valueOf(custo));
			ingrediente.setUnidadeMedida(unidadeMedida);		
			
			request.setAttribute("ingrediente", ingrediente);
			
				ingredienteBO.editaIngrediente(ingrediente);
				proxima = "main?acao=listIngrediente";
				request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_EDICAO_INGREDIENTE.replace("?", ingrediente.getDescricao()));				
		} catch(Exception e){		
			request.setAttribute("msgErro", e.getMessage());
		}
		return proxima;
	}

}