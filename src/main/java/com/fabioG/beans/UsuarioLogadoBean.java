package com.fabioG.beans;

import com.fabioG.domains.Usuarios;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * @author Fábio Grando
 */
@Named
@SessionScoped
public class UsuarioLogadoBean implements Serializable {

    private Usuarios usuarioLogada;

    public Usuarios getUsuarioLogada() {
        return usuarioLogada;
    }

    public void setUsuarioLogada(Usuarios usuarioLogada) {
        this.usuarioLogada = usuarioLogada;
    }


}
