package br.ages.crud.command;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.IdentifierHelper;

import br.ages.crud.bo.FichaSimplificadaBO;
import br.ages.crud.exception.NegocioException;
import br.ages.crud.model.PerfilAcesso;
import br.ages.crud.model.Usuario;
import br.ages.crud.util.MensagemContantes;

public class RemoveFichaSimplificadaCommand implements Command{
	
	private String proximo;

	private FichaSimplificadaBO fichaSimplificadaBO;
	
	
	@Override
	public String execute(HttpServletRequest request) {
		proximo = "main?acao=listFichaSimplificada";
		
		this.fichaSimplificadaBO = new FichaSimplificadaBO();
		
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuarioSessao");
		
		try{
			if( !usuario.getPerfilAcesso().equals(PerfilAcesso.ADMINISTRADOR) ) throw new NegocioException(MensagemContantes.MSG_INF_SEM_PERISSAO);
			
			Integer idFicha = Integer.parseInt(request.getParameter("id_ficha_simplificada"));
			fichaSimplificadaBO.removerFichaSimplificada(idFicha);
			
			request.setAttribute("msgSucesso", MensagemContantes.MSG_SUC_REMOVE_FICHA_SIMPLIFICADA.replace("?", idFicha.toString()).concat("<br/>"));
		}catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
		}
		
		return proximo;
		
	}

}