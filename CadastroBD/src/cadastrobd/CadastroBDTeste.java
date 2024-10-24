package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridicaDAO;

        
public class CadastroBDTeste {

    public static void main(String[] args) {
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

        PessoaFisica pessoaFisica = new PessoaFisica(0, "Joao Silva", "Rua A", "Cidade A", "Estado A", "123456789", "joao@example.com", "123.456.789-00");
        pessoaFisicaDAO.incluir(pessoaFisica);

        pessoaFisica.setNome("Joao Silva Alterado");
        pessoaFisicaDAO.alterar(pessoaFisica);

        for (PessoaFisica pf : pessoaFisicaDAO.getPessoas()) {
            pf.exibir();
        }

        pessoaFisicaDAO.excluir(pessoaFisica.getId());

        PessoaJuridica pessoaJuridica = new PessoaJuridica(0, "Empresa ABC", "Avenida B", "Cidade B", "Estado B", "987654321", "contato@empresa.com", "12.345.678/0001-99");
        pessoaJuridicaDAO.incluir(pessoaJuridica);

        pessoaJuridicaDAO.alterar(pessoaJuridica);

        for (PessoaJuridica pj : pessoaJuridicaDAO.getPessoas()) {
            pj.exibir();
        }

        pessoaJuridicaDAO.excluir(pessoaJuridica.getId());
    }
}
