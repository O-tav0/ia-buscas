package espacoDeEstados;

import java.util.List;

/**
 * Esta classe implementa o n�vel mais abstrato de representa��o do problema sob
 * a forma de um espa�o de estados. Pontualmente, modela uma poss�vel situa��o ou
 * configura��o deste problema (estado), al�m de ser poss�vel vincul�-lo com outros
 * estados, que � o quesito fundamental para construir espa�o de estados.
 * 
 * @author Leandro Fernandes
 * 
 * @param <T> tipo utilizado para representar e armazenar uma descri��o do estado,
 *            segundo o problema que estiver sendo modelado. Trata-se de um tipo
 *            abstrato de dados (TAD), que permite a esta classe operar com qualquer
 *            forma de descri��o que for adotada e considerada mais adequada a
 *            representa��o do problema em quest�o.
 */
public abstract class Estado<T> {
	
	private int id = 0;					// identificador do estado
	private int nivel;					// n�vel em que o estado se encontra na �rvore de busca
	private Estado<?> ancestral;		// referencia ao n� pai na �rvore de busca
	
	/**
	 * Define o n�mero identificador do estado.
	 * @param identificador do estado
	 */
	public int setId(int id) {
		return this.id = id;
	}
	
	/**
	 * Recupera o identificador do estado.
	 * @return identificador do estado
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Define o n�vel que o estado (nodo) ocupa na �rvore de busca.
	 * @param n�vel do estado na �rvore de busca
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	/**
	 * M�todo de acesso que permite recuperar qual o n�vel (profundidade em
	 * rela��o a raiz) que este estado ocupa na �rvore de busca.
	 * @return n�vel do nodo na �rvore de busca
	 */
	public int getNivel() {
		return nivel;
	}
	
	/**
	 * Define qual o estado que originou o estado corrente dentro do espa�o
	 * de busca, ou seja, qual � o seu estado predecessor.
	 * @param refer�ncia ao estado ancestral
	 */
	public void setAncestral(Estado<?> estado) {
		ancestral = estado;
	}
	
	/**
	 * Recupera o estado predecessor deste estado no espa�o de busca.
	 * @return refer�ncia ao estado anterior, sob o qual a a��o resultou no estado atual
	 */
	public Estado<?> getAncestral() {
		return ancestral;
	}
	
	/**
	 * Status, descri��o ou configura��o representativa para o estado.
	 * @param informa��es que caracterizam este estado 
	 */
	public abstract void setEstado(T cfg);
	
	/**
	 * Retorna a configura��o do estado 
	 * @return todas as informa��es que caracterizam este estado
	 */
	public abstract T getEstado();
		
	/**
	 * Fun��o que gera os estados sucessores de acordo com o problema
	 * @return lista de estados sucessores (adjacentes) a partir deste estado
	 */
	public abstract List<?> getSucessores();
	
	/**
	 * Permite verificar se este estado � igual a outro.
	 * @param o estado qual se deseja comparar com este
	 * @return true ou false
	 */
	public abstract boolean equals(Object estado);
	
	/**
	 * Retorna uma representa��o do estado numa forma textual e que possa ser
	 * apresentada na console, por exemplo.
	 * @return uma String representativa contendo as informa��es descritivas do estado
	 */
	public abstract String toString();
}