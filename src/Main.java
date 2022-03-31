import espacoDeEstados.*;
import estrategiasDeBusca.cega.BuscaEmProfundidadeLimitada;
import estrategiasDeBusca.heuristica.*;

import java.util.List;

public class Main {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {

		char[] cfgIni = {' ','2','3','1','4','6','7','5','8'};
		//char[] cfgIni = {'2','4','3','7','1','6','5',' ','8'};
		//char[] cfgIni = {'2','3',' ','7','4','1','5','8','6'};
		//char[] cfgIni = {'7','2','3','4',' ','1','5','8','6'}; // OutOfMemory
		char[] cgFim = {'2', '3', ' ', '7', '4', '1', '5','8','6'};
		Puzzle8 puzzleInicial = new Puzzle8();
		puzzleInicial.setEstado(cfgIni);
		puzzleInicial.setCusto(0);
		puzzleInicial.setAvaliacao( puzzleInicial.heuristica(Puzzle8.TABULEIRO_ORGANIZADO) );

		Puzzle8 puzzleFinal = new Puzzle8();
		puzzleFinal.setEstado(cgFim);
		puzzleFinal.setCusto(0);
		puzzleFinal.setAvaliacao(0);

		List<Estado<?>> caminhoDaSolucao = null;

		BuscaInformada busca = new AStar();
		BuscaEmProfundidadeLimitada buscaLimitada = new BuscaEmProfundidadeLimitada();
		buscaLimitada.setInicio(puzzleInicial);
		buscaLimitada.setObjetivo(puzzleFinal);
		buscaLimitada.setLimite(0);

		while(caminhoDaSolucao == null) {
			try {
				buscaLimitada.buscar();
				caminhoDaSolucao = buscaLimitada.getCaminhoSolucao();
			} catch (Exception e) {
				System.out.println("O limite mínimo necessário não é: " + buscaLimitada.getLimite());
			}
			buscaLimitada.setLimite(buscaLimitada.getLimite() + 1);
		}

		for(Estado e : caminhoDaSolucao) {
			System.out.println(e);
			System.out.println(" ");
		}

		System.exit(0);
	}

}