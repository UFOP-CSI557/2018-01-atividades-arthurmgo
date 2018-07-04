package ag;

import java.util.Random;

public class Individuo {


    private char[] genes;
    private float fitness;

    public final static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz .123456789.+-/*,:";

    Random r = new Random();

    // Constructor (makes a random Individuo)
    public Individuo(int num) {
        genes = new char[num];
        for (int i = 0; i < genes.length; i++) {
            genes[i] = alphabet.charAt(r.nextInt(alphabet.length())); // Seleciona um caractere do alfabeto
        }
    }

    // Converts character array to a String
    String getPhrase() {
        return new String(genes);
    }

    // Fitness function (returns floating point % of "correct" characters)
    void fitness (String target) {
        int score = 0;
        for (int i = 0; i < genes.length; i++) {
            if (genes[i] == target.charAt(i)) {
                score++;
            }
        }


        fitness = (float)score / (float)target.length();
    }

    // Crossover
    Individuo crossover(Individuo partner) {
        // A new child
        Individuo child = new Individuo(genes.length);

        int midpoint = r.nextInt(genes.length); // Pick a midpoint

        // Half from one, half from the other
        for (int i = 0; i < genes.length; i++) {
            if (i > midpoint) child.genes[i] = genes[i];
            else              child.genes[i] = partner.genes[i];
        }
        return child;
    }

    // Mutação gerando um caractere aleatorio
    void mutate(float mutationRate) {
        for (int i = 0; i < genes.length; i++) {
            if (r.nextFloat() < mutationRate) {
                genes[i] = alphabet.charAt(r.nextInt(alphabet.length()));
            }
        }
    }

    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }
}