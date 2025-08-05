import java.util.LinkedList;

public class ParentesesChecker {

	public boolean checkParenteses(String expressao) {
		LinkedList<Character> pilha = new LinkedList<>();

		for (char c : expressao.toCharArray()) {

			// Se for '(', apenas adicione na nossa lista/pilha
			if (c == '(') {
				pilha.push(c);
			}
			// Se for ')', precisamos checar
			else if (c == ')') {
				// Se a pilha estiver vazia, significa que achamos um ')'
				// sem ter um '(' antes. Logo, é inválido.
				if (pilha.isEmpty()) {
					return false;
				}
				// Se não estiver vazia, removemos um '('.
				// Isso forma o par com o ')' que acabamos de encontrar.
				pilha.pop(); // pop remove o último que entrou
			}
		}

		// 2. Verificação Final
		// Se, no final, a pilha estiver vazia, todos os '(' encontraram seu par ')'.
		// Se sobrou algo na pilha, significa que há '(' a mais.
		return pilha.isEmpty();
	}
}