package com.fabioG.beans;

import com.fabioG.domains.Usuarios;
import com.fabioG.services.UsuariosService;
import com.fabioG.utils.JsfUtil;
import com.fabioG.utils.StringUtil;
//import com.fabioG.utils.WhatsAppUtil;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fábio Grando
 */
@Named
@ViewScoped
public class LoginBean implements Serializable {

    @EJB
    private UsuariosService usuariosService;

    @Inject
    private UsuarioLogadoBean usuarioLogadoBean;

    private String nome;
    private String senha;
    private String cpf;
    private String telefone;
    private String endereco;
    private Integer tipoUsuario;
    private String novaSenha;
    private String confirmacaoNovaSenha;
    private List<Usuarios> usuariosEncontrados = new ArrayList<>();
    
    @PostConstruct
    private void init() {
    }

    public void doLogin() {

        if (StringUtil.isNullOrEmpty(cpf)) {
            JsfUtil.warn("É necessário informar seu CPF");
            return;
        }

        if (!StringUtil.isCPFValido(cpf)) {
            JsfUtil.warn("CPF inválido");
            return;
        }

        if (StringUtil.isNullOrEmpty(senha)) {
            JsfUtil.warn("É necessário informar sua Senha");
            return;
        }

        String cpfOnlyNumbers = StringUtil.getOnlyNumbers(cpf);

        List<Usuarios> usuarios = usuariosService.getPessoaPorCpf(cpfOnlyNumbers);
        
        if (usuarios.isEmpty()) {
            JsfUtil.warn("Nenhum usuário encontrado com o CPF informado");
            return;
        }

        Usuarios usuario = usuarios.get(0);

        boolean mesmaSenha = StringUtil.getMD5(senha).equals(usuario.getSenha());

        if (!mesmaSenha) {
            JsfUtil.warn("Senha inválida");
            return;
        }

        usuarioLogadoBean.setUsuarioLogada(usuario);
        JsfUtil.redirect("/aplicacao_churrasco/index.xhtml");
    }

    public void redirecionarParaLogin() {
        JsfUtil.redirect("/aplicacao_churrasco/login.xhtml");
    }

    public void abrirDialogEsqueciMinhaSenha() {
        JsfUtil.pfShowDialog("wvDlgEsqueciMinhaSenha");
    }

//    public void enviarCodigoConfirmacao() {
//        String telefoneOnlyNumbers = StringUtil.getOnlyNumbers(telefone);
//        String cpfOnlyNumbers = StringUtil.getOnlyNumbers(cpf);
//
//        Map<String, Object> filtros = new HashMap<>();
//        filtros.put("cpf", cpfOnlyNumbers);
//        filtros.put("telefone", telefoneOnlyNumbers);
//        pessoasEncontradas = pessoaService.filtrar(filtros);
//
//        if (pessoasEncontradas.isEmpty()) {
//            JsfUtil.warn("Nenhuma pessoa encontrado com o cpf e o telefone informado");
//            return;
//        }
//
//        codigoConfirmacao = StringUtil.getSenhaAleatoria();
//        String mensagem = "Código de Confirmação: " + codigoConfirmacao;
//        WhatsAppUtil.enviarMensagem(telefone, mensagem);
//        JsfUtil.info("Código de confirmação enviado para o telefone informado");
//    }

//    public void validarCodigoConfirmacao() {
//        exibirSenha = codigoConfirmacao.equals(codigoConfirmacaoInformado);
//
//        if (!exibirSenha) {
//            JsfUtil.error("Código de confirmação inválido");
//        }
//    }

    public void alterarSenha() {
        if (novaSenha.length() < 8) {
            JsfUtil.error("A senha deve conter ao menos 8 caracteres");
            return;
        }

        if (!novaSenha.equals(confirmacaoNovaSenha)) {
            JsfUtil.error("A senha e a confirmação da senha não conferem");
            return;
        }

        for (Usuarios usuario : usuariosEncontrados) {
            String novaSenhaMD5 = StringUtil.getMD5(novaSenha);
            usuario.setSenha(novaSenhaMD5);
            usuario = usuariosService.salvar(usuario);
        }

        JsfUtil.info("Senha Alterada com Sucesso");
        JsfUtil.pfHideDialog("wvDlgEsqueciMinhaSenha");
        limparCamposDialog();
    }

    private void limparCamposDialog() {
        cpf = null;
        telefone = null;
        novaSenha = null;
        confirmacaoNovaSenha = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmacaoNovaSenha() {
        return confirmacaoNovaSenha;
    }

    public void setConfirmacaoNovaSenha(String confirmacaoNovaSenha) {
        this.confirmacaoNovaSenha = confirmacaoNovaSenha;
    }

    public List<Usuarios> getUsuariosEncontrados() {
        return usuariosEncontrados;
    }

    public void setUsuariosEncontrados(List<Usuarios> usuariosEncontrados) {
        this.usuariosEncontrados = usuariosEncontrados;
    }
    
}
