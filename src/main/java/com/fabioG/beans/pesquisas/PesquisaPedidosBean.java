package com.fabioG.beans.pesquisas;

import com.fabioG.beans.UsuarioLogadoBean;
import com.fabioG.domains.Pedidos;
import com.fabioG.services.BasePesquisa;
import com.fabioG.services.PedidosService;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Fábio Grando
 */
@Named
@ViewScoped
public class PesquisaPedidosBean extends BasePesquisa<Pedidos> {

    @EJB
    private PedidosService pedidosService;
    
//    @Inject
//    private UsuarioLogadoBean ul;

    private Pedidos filtroPedidos;

    @Override
    protected void iniciaFiltros() {
        filtroPedidos = new Pedidos();
//        if (ul.getTipoAcessoEnum().getCodigo() ==  4){
//            filtroPedidos.setUsuario(ul.getPessoaLogada());
//        }
    }

    @Override
    public void pesquisar() {
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("id" , filtroPedidos.getId());
        if (filtroPedidos.getUsuario() != null){
            filtros.put("usuarioId" , filtroPedidos.getUsuario().getId());
        }
        registros = pedidosService.filtrar(filtros);
    }

    @Override
    public void limparFiltros() {
        filtroPedidos = new Pedidos();
//        if (ul.getTipoAcessoEnum().getCodigo() ==  4){
//            filtroPedidos.setUsuario(ul.getPessoaLogada());
//        }
        registros = new ArrayList<>();
    }
    
    @Override
    public List<Pedidos> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Pedidos> registros) {
        this.registros = registros;
    }

    public List<Pedidos> getRegistrosSelecionados() {
        return registrosSelecionados;
    }

    public void setRegistrosSelecionados(List<Pedidos> registrosSelecionados) {
        this.registrosSelecionados = registrosSelecionados;
    }

    public Pedidos getFiltroPedidos() {
        return filtroPedidos;
    }

    public void setFiltroPedidos(Pedidos filtroPedidos) {
        this.filtroPedidos = filtroPedidos;
    }
}
