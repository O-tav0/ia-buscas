import espacoDeEstados.*;
import estrategiasDeBusca.cega.BuscaEmProfundidadeLimitada;
import estrategiasDeBusca.heuristica.*;

import java.util.List;
import java.util.Scanner;

public class Main {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

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

		System.out.println("Qual método de busca deseja usar ?");

		System.out.println("1 - Busca cega - Limitada manual");
		System.out.println("2 - Busca cega - Limitada dinâmica");
		System.out.println("3 - Busca Informada - AStar");
		System.out.println("4 - Busca Informada - BestFirst");

		Integer escolhaMetodo = scanner.nextInt();
		scanner.nextLine();

		switch(escolhaMetodo) {
			case 1:
				System.out.println("Qual vai ser o limite ?");
				BuscaEmProfundidadeLimitada buscaLimitada = new BuscaEmProfundidadeLimitada();

				buscaLimitada.setInicio(puzzleInicial);
				buscaLimitada.setObjetivo(puzzleFinal);
				buscaLimitada.setLimite(scanner.nextInt());
				buscaLimitada.buscar();
				scanner.nextLine();

				mostrarCaminho(buscaLimitada.getCaminhoSolucao());

				break;

			case 2:
				BuscaEmProfundidadeLimitada buscaLimitadaDinamica = new BuscaEmProfundidadeLimitada();

				buscaLimitadaDinamica.setInicio(puzzleInicial);
				buscaLimitadaDinamica.setObjetivo(puzzleFinal);

				buscaLimitadaDinamica.setLimite(0);

				List<Estado<?>> caminhoDaSolucao = null;

				while(caminhoDaSolucao == null) {
					try {
						buscaLimitadaDinamica.buscar();
						caminhoDaSolucao = buscaLimitadaDinamica.getCaminhoSolucao();
					} catch (Exception e) {
						System.out.println("O limite mínimo necessário não é: " + buscaLimitadaDinamica.getLimite());
					}
					buscaLimitadaDinamica.setLimite(buscaLimitadaDinamica.getLimite() + 1);
				}

				mostrarCaminho(buscaLimitadaDinamica.getCaminhoSolucao());

				break;

			case 3:
				AStar buscaAstar = new AStar();
				buscaAstar.setInicio(puzzleInicial);
				buscaAstar.setObjetivo(puzzleFinal);

				buscaAstar.buscar();

				mostrarCaminho(buscaAstar.getCaminhoSolucao());

				break;

			case 4:
				BestFirst bestFirst = new BestFirst();
				bestFirst.setInicio(puzzleInicial);
				bestFirst.setObjetivo(puzzleFinal);

				bestFirst.buscar();

				mostrarCaminho(bestFirst.getCaminhoSolucao());
		}
		System.exit(0);
	}

	public static void mostrarCaminho(List<Estado<?>> caminhoDaSolucao) {
		for(Estado e : caminhoDaSolucao) {
			System.out.println(e);
			System.out.println(" ");
		}
	}
}