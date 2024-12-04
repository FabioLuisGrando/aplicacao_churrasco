package com.fabioG.services;

import com.fabioG.domains.Pedidos;
import com.fabioG.utils.FiltrosPesquisa;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Fábio Grando
 */
@Stateless
@Named
public class PedidosService extends BaseService<Pedidos> {

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        List<FiltrosPesquisa> filtrosPesquisa = new ArrayList<>();
        add(filtrosPesquisa, "p.id = '?id'", "id", filtros.get("id"));
        add(filtrosPesquisa, "p.usuario.id = ?usuarioId", "usuarioId", filtros.get("usuarioId"));
        return filtrosPesquisa;
    }

    public List<Pedidos> filtrar(Map<String, Object> filtros) {
        String sql = "SELECT p FROM Pedidos p";
        sql = adicionarFiltros(sql, getFiltros(filtros));
        Query query = customEntityManager.getEntityManager().createQuery(sql);
        return query.getResultList();
    }
}
