package com.fabioG.services;

import com.fabioG.beans.BuscaBean;
import com.fabioG.beans.GerBean;
import com.fabioG.beans.UsuarioLogadoBean;
import com.fabioG.utils.JsfUtil;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import java.io.Serializable;

/**
 *
 * @author Fábio Grando
 */
public abstract class BaseCrud<T> implements Serializable {

    @EJB
    protected CustomEntityManager customEntityManager;

    @Inject
    private GerBean gerBean;
    @Inject
    private BuscaBean buscaBean;
    @Inject
    private UsuarioLogadoBean usuarioLogadoBean;

    protected T crudObj;

    @PostConstruct
    public void init() {
        criaObj();
    }

    public abstract void criaObj();

    public void salvar() {
        customEntityManager.salvar(crudObj);
        criaObj();
        JsfUtil.info("Registro salvo com sucesso");
    }

    public void cancelar() {
        JsfUtil.refresh();
    }

    public void deletar() {
        try {
            customEntityManager.deletar(crudObj);
            criaObj();
            JsfUtil.info("Registro excluido com sucesso");
        } catch (Exception e) {
            JsfUtil.error("Não foi possível excluir o registro");
        }
    }

    public abstract void setObjetoCrudPesquisa();

    public GerBean getGerBean() {
        return gerBean;
    }

    public void setGerBean(GerBean gerBean) {
        this.gerBean = gerBean;
    }

    public BuscaBean getBuscaBean() {
        return buscaBean;
    }

    public void setBuscaBean(BuscaBean buscaBean) {
        this.buscaBean = buscaBean;
    }

    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }
}
