package com.fabioG.services;

import com.fabioG.beans.BuscaBean;
import com.fabioG.beans.UsuarioLogadoBean;
import com.fabioG.utils.JsfUtil;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fábio Grando
 */
public abstract class BasePesquisa<T> implements Serializable {

    @Inject
    protected BuscaBean buscaBean;

    @Inject
    private UsuarioLogadoBean usuarioLogadoBean;

    protected List<T> registros = new ArrayList<>();

    protected List<T> registrosSelecionados = new ArrayList<>();
    private boolean multiplaSelecao;

    private final int rowsPadraoDataTable = 10;

    @PostConstruct
    public void init() {
        setMultiplaSelecaoFromParametros();
        iniciaFiltros();
        pesquisar();
    }

    private void setMultiplaSelecaoFromParametros() {
        multiplaSelecao = Boolean.parseBoolean(JsfUtil.getParam("multiplaSelecao"));
    }

    public abstract void pesquisar();

    public abstract void limparFiltros();

    public void finalizarBuscaMultiplaSelecao() {
        if (!registrosSelecionados.isEmpty()) {
            buscaBean.setObjetosSelecionados((List<Object>) registrosSelecionados);
        }

        buscaBean.finalizarBusca();
    }

    protected abstract void iniciaFiltros();

    public List<T> getRegistros() {
        return registros;
    }

    public void setRegistros(List<T> registros) {
        this.registros = registros;
    }

    public boolean isMultiplaSelecao() {
        return multiplaSelecao;
    }

    public void setMultiplaSelecao(boolean multiplaSelecao) {
        this.multiplaSelecao = multiplaSelecao;
    }

    public List<T> getRegistrosSelecionados() {
        return registrosSelecionados;
    }

    public void setRegistrosSelecionados(List<T> registrosSelecionados) {
        this.registrosSelecionados = registrosSelecionados;
    }

    public int getRowsPadraoDataTable() {
        return rowsPadraoDataTable;
    }

    public UsuarioLogadoBean getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoBean usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }
}
