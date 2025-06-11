/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.package1.atividade1;

/**
 *
 * @author okmen
 */
import java.util.Random;

public class Atividade1 {

    public static void main(String[] args) {
        int[] vetor = new int[1000];
        Random rand = new Random();

        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = rand.nextInt(19999) - 9999;
        }

        System.out.println("Vetor gerado:");
        for (int num : vetor) {
            System.out.print(num + " ");
        }
        System.out.println("\n");

        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();

        for (int num : vetor) {
            lista.inserirOrdenado(num);
        }

        System.out.println("Lista em ordem crescente:");
        lista.imprimirCrescente();

        System.out.println("\nLista em ordem decrescente:");
        lista.imprimirDecrescente();

        lista.removerPrimos();

        System.out.println("\nLista após remoção dos primos (ordem crescente):");
        lista.imprimirCrescente();
    }
}

class No {
    int valor;
    No anterior;
    No proximo;

    No(int valor) {
        this.valor = valor;
        this.anterior = null;
        this.proximo = null;
    }
}

class ListaDuplamenteEncadeada {
    No inicio;
    No fim;

    public void inserirOrdenado(int valor) {
        No novo = new No(valor);
        if (inicio == null) {
            inicio = fim = novo;
            return;
        }

        if (valor < inicio.valor) {
            novo.proximo = inicio;
            inicio.anterior = novo;
            inicio = novo;
            return;
        }

        if (valor >= fim.valor) {
            fim.proximo = novo;
            novo.anterior = fim;
            fim = novo;
            return;
        }

        No atual = inicio;
        while (atual != null && atual.valor < valor) {
            atual = atual.proximo;
        }

        novo.anterior = atual.anterior;
        novo.proximo = atual;
        atual.anterior.proximo = novo;
        atual.anterior = novo;
    }

    public void imprimirCrescente() {
        No atual = inicio;
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    public void imprimirDecrescente() {
        No atual = fim;
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.anterior;
        }
        System.out.println();
    }

    public void removerPrimos() {
        No atual = inicio;

        while (atual != null) {
            if (ehPrimo(atual.valor)) {
                No anterior = atual.anterior;
                No proximo = atual.proximo;

                if (anterior != null) {
                    anterior.proximo = proximo;
                } else {
                    inicio = proximo;
                }

                if (proximo != null) {
                    proximo.anterior = anterior;
                } else {
                    fim = anterior;
                }
            }

            atual = atual.proximo;
        }
    }

    private boolean ehPrimo(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }

        return true;
    }
}

    

