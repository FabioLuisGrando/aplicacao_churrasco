package com.fabioG.beans;

import com.fabioG.enums.TipoAcessoEnum;
import com.fabioG.utils.JsfUtil;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * @author Fábio Grando
 * 
 */
@Named
@SessionScoped
public class GerBean implements Serializable {

    @Inject
    private UsuarioLogadoBean usuarioLogadoBean;

    public void verificarPessoaLogada() {
        if (usuarioLogadoBean.getUsuarioLogada() == null && !JsfUtil.isPaginaPublica()) {
            JsfUtil.redirect("/aplicacao_churrasco/index.xhtml");
            return;
        }

        if (usuarioLogadoBean.getUsuarioLogada() != null && JsfUtil.isPaginaPublica()) {
            JsfUtil.redirect("/aplicacao_churrasco/index.xhtml");
            return;
        }

        if (!isTipoAcessoAdministrador()
                && (JsfUtil.isPage("/cadastros/clinica.xhtml")
                || JsfUtil.isPage("/cadastros/especialidade.xhtml")
                || JsfUtil.isPage("/cadastros/medicamento.xhtml")
                || JsfUtil.isPage("/cadastros/horario-semanal.xhtml")
                || JsfUtil.isPage("/cadastros/administrador.xhtml")
                || JsfUtil.isPage("/consultas/clinica.xhtml")
                || JsfUtil.isPage("/consultas/especialidade.xhtml")
                || JsfUtil.isPage("/consultas/medicamento.xhtml")
                || JsfUtil.isPage("/consultas/horario-semanal.xhtml")
                || JsfUtil.isPage("/consultas/administrador.xhtml"))) {
            JsfUtil.redirect("/aplicacao_churrasco/index.xhtml");
            return;
        }

        if (!isTipoAcessoAdministrador() && !isTipoAcessoFuncionario() && JsfUtil.isPage("/cadastros/funcionario.xhtml")) {
            JsfUtil.redirect("/aplicacao_churrasco/index.xhtml");
            return;
        }

    }

    public String getStyleMenu() {
        return usuarioLogadoBean.getUsuarioLogada() != null ? "" : "display: none;";
    }

    public String getStyleMenuCadastroClinica() {
        return isTipoAcessoAdministrador() ? "" : "display: none;";
    }

    public String getStyleMenuCadastroAdministrador() {
        return isTipoAcessoAdministrador() ? "" : "display: none;";
    }

    public String getStyleMenuCadastroFuncionario() {
        return isTipoAcessoAdministrador() || isTipoAcessoFuncionario() ? "" : "display: none;";
    }

    public String getStyleMenuCadastroEspecialidade() {
        return isTipoAcessoAdministrador() ? "" : "display: none;";
    }

    public String getStyleMenuCadastroHorarioSemanal() {
        return isTipoAcessoAdministrador() ? "" : "display: none;";
    }

    public String getStyleMenuCadastroMedicamento() {
        return isTipoAcessoAdministrador() ? "" : "display: none;";
    }

    public void logout() {
        JsfUtil.getCurrentInstance().getExternalContext().invalidateSession();
        JsfUtil.redirect("/aplicacao_churrasco/login.xhtml");
    }

    public boolean isTipoAcessoAdministrador() {
        return usuarioLogadoBean.getUsuarioLogada() != null;
    }

    public boolean isTipoAcessoFuncionario() {
        return usuarioLogadoBean.getUsuarioLogada() != null;
    }
}
