package com.fabioG.services;

import com.fabioG.domains.Usuarios;
import com.fabioG.utils.FiltrosPesquisa;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Fábio Grando
 */
@Stateless
@Named
public class UsuariosService extends BaseService<Usuarios> {

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        List<FiltrosPesquisa> filtrosPesquisa = new ArrayList<>();
        add(filtrosPesquisa, "u.cpf = '?cpf'", "cpf", filtros.get("cpf"));
        add(filtrosPesquisa, "u.id = '?id'", "id", filtros.get("id"));
        return filtrosPesquisa;
    }

    public List<Usuarios> filtrar(Map<String, Object> filtros) {
        String sql = "SELECT u FROM Usuarios u ";
        sql = adicionarFiltros(sql, getFiltros(filtros));
        Query query = customEntityManager.getEntityManager().createQuery(sql);

        Set<Usuarios> setUsuarios = new HashSet<>();
        setUsuarios.addAll(query.getResultList());
        return new ArrayList<>(setUsuarios);
    }

    public List<Usuarios> getPessoaPorCpf(String cpf) {
       String sql = "SELECT * FROM Usuarios u "
                + "WHERE u.cpf = '" + cpf + "'";
        return customEntityManager.executeNativeQuery(Usuarios.class, sql);
    }
}
