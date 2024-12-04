package com.fabioG.services;

import com.fabioG.domains.Produto;
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
public class ProdutoService extends BaseService<Produto> {

    @Override
    protected List<FiltrosPesquisa> getFiltros(Map<String, Object> filtros) {
        List<FiltrosPesquisa> filtrosPesquisa = new ArrayList<>();
        add(filtrosPesquisa, "p.id_produto = '?id_produto'", "id_produto", filtros.get("id_produto"));
        return filtrosPesquisa;
    }

    public List<Produto> filtrar(Map<String, Object> filtros) {
        String sql = "SELECT p FROM Produto p";
        sql = adicionarFiltros(sql, getFiltros(filtros));
        Query query = customEntityManager.getEntityManager().createQuery(sql);
        return query.getResultList();
    }
}
