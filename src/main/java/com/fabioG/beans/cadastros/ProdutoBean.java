package com.fabioG.beans.cadastros;
import com.fabioG.beans.BuscaBean;
import com.fabioG.domains.Produto;
import com.fabioG.services.BaseCrud;
import com.fabioG.services.ProdutoService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author Fábio Grando
 */

@Named
@ViewScoped
public class ProdutoBean extends BaseCrud<Produto> {
    
    @EJB
    private ProdutoService produtoService;
    
    @Override
    public void criaObj() {
        crudObj = new Produto();
    }

    @Override
    public void setObjetoCrudPesquisa() {
        Produto produto = BuscaBean.getResultadoPesquisa(Produto.class);

        if (produto == null) {
            return;
        }

        crudObj = produto;
    }

    public Produto getCrudObj() {
        return crudObj;
    }

    public void setCrudObj(Produto crudObj) {
        this.crudObj = crudObj;
    }
    
}
