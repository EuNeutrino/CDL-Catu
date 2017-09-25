package br.cdl.david.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.cdl.david.dao.UnidadeDAO;
import br.cdl.david.domain.UnidadeCdl;

import org.omnifaces.util.Messages;

/**
 *
 * @author David Nogueira
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UnidadeBean implements Serializable {
	private UnidadeCdl unidade;
	private UnidadeDAO dao;
	private List<UnidadeCdl> unidades;

	public UnidadeCdl getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeCdl unidade) {
		this.unidade = unidade;
	}

	public UnidadeDAO getDao() {
		return dao;
	}

	public void setDao(UnidadeDAO dao) {
		this.dao = dao;
	}

	public List<UnidadeCdl> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<UnidadeCdl> unidades) {
		this.unidades = unidades;
	}

	public void novo() {
		unidade = new UnidadeCdl();
	}

	public void salvar() {
		try {
			dao = new UnidadeDAO();
			dao.merge(unidade);
			unidade = new UnidadeCdl();
			unidades = dao.listar();
			Messages.addGlobalInfo("Unidade CDL salvo com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Houve um erro ao tentar salvar a unidade CDL, contate o suporte");
			erro.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			dao = new UnidadeDAO();
			unidades = dao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Houve um erro ao tentar listar as unidade CDL, contate o suporte");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			dao = new UnidadeDAO();
			dao.excluir(unidade);
			unidades = dao.listar();
			Messages.addGlobalInfo("Unidade CDL excluido com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Houve um erro ao tentar exxluir a unidade CDL, contate o suporte");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		unidade = (UnidadeCdl) evento.getComponent().getAttributes().get("unidadeSelecionado");

	}

}
