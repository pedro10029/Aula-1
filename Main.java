import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        // Criando a instância da classe de serviço
        CalculadoraNumeros calculadora = new CalculadoraNumeros();

        int inicio = 10;
        int fim = 20;

        // 1. Executando a soma clássica (corrigida)
        int resultadoSoma = calculadora.somarIntervalo(inicio, fim);
        System.out.println("Soma do intervalo (" + inicio + " a " + fim + "): " + resultadoSoma);        

        // 2. RECURSO EXTRA: Filtrando apenas os números pares do intervalo
        List<Integer> pares = calculadora.obterNumerosPares(inicio, fim);
        System.out.println("Números pares no intervalo: " + pares);

        // 3. RECURSO EXTRA: Média dos números do intervalo
        double media = calculadora.calcularMediaIntervalo(inicio, fim);
        System.out.println("Média do intervalo: " + media);
    }
}

class CalculadoraNumeros {
    // Variáveis privadas (Encapsulamento: ninguém fora da classe altera isso diretamente)
    private int numeroInicial;
    private int numeroFinal;

    // Método principal melhorado (sem o bug de acumular valores entre chamadas)
    public int somarIntervalo(int n1, int n2) {
        this.numeroInicial = n1;
        this.numeroFinal = n2;
        
        // Uso de Streams do Java 8+: Mais limpo, rápido e sem precisar de laço for manual
        return IntStream.rangeClosed(this.numeroInicial, this.numeroFinal).sum();
    }

    // COISA A MAIS 1: Método para listar os números pares do intervalo usando Stream e Filter
    public List<Integer> obterNumerosPares(int n1, int n2) {
        return IntStream.rangeClosed(n1, n2)
                .filter(num -> num % 2 == 0)
                .boxed()
                .collect(Collectors.toList());
    }

    // COISA A MAIS 2: Método para calcular a média aritmética do intervalo
    public double calcularMediaIntervalo(int n1, int n2) {
        return IntStream.rangeClosed(n1, n2)
                .average()
                .orElse(0.0); // Retorna 0.0 caso o intervalo seja inválido
    }

    // Getters (para caso você precise ler os valores em outro lugar do código)
    public int getNumeroInicial() { return numeroInicial; }
    public int getNumeroFinal() { return numeroFinal; }
}