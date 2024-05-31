package cadastroee.controller;

import cadastroee.model.Movimento;
import java.util.List;
import jakarta.ejb.Local;

@Local
public interface MovimentoFacadeLocal {

    void adicionar(Movimento movimento);

    void atualizar(Movimento movimento);

    void excluir(Movimento movimento);

    Movimento obterPorId(Object id);

    List<Movimento> obterTodos();

    List<Movimento> obterIntervalo(int[] intervalo);

    int total();
}
