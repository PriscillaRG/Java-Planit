package com.descodeuses.planit.exercice_tp;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercice1 {

    public static void main(String[] args) {

        // Initialisation de Scanner
        Scanner scanner = new Scanner(System.in);

        // Initialisation de la liste des tâches
        ArrayList<String> tasks = new ArrayList<>();

        boolean running = true;
        while (running) {
            // Méthode pour afficher le menu
            System.out.println("\n========= MENU =========");
            System.out.println("1. Ajouter une tâche");
            System.out.println("2. Voir les tâches");
            System.out.println("3. Marquer une tâche comme faite");
            System.out.println("4. Supprimer une tâche");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    ajouterTache(scanner, tasks);
                    break;
                case 2:
                    afficherTaches(tasks);
                    break;
                case 3:
                    marquerTacheFaite(scanner, tasks);
                    break;
                case 4:
                    supprimerTache(scanner, tasks);
                    break;
                case 5:
                    running = false;
                    break;
            }

            System.out.println("Vous avez choisi l'option : " + choix);

        }
        scanner.close();
    }

    private static void ajouterTache(Scanner scanner, ArrayList<String> tasks) {

        System.out.print("Entrez la tâche à ajouter : ");
        String tache = scanner.next();
        tasks.add(tache);
        System.out.println("Tâche ajoutée : " + tache);
    }

    private static void afficherTaches(ArrayList<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Aucune tâche à afficher.");
        } else {
            System.out.println("Liste des tâches :");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void marquerTacheFaite(Scanner scanner, ArrayList<String> tasks) {
        afficherTaches(tasks);
        System.out.print("Entrez le numéro de la tâche à marquer comme faite : ");
        int index = scanner.nextInt() - 1;
        if (index >= 0 && index < tasks.size()) {
            String tache = tasks.get(index);
            tasks.set(index, tache + " (faite)");
            System.out.println("Tâche marquée comme faite : " + tache);
        } else {
            System.out.println("Numéro de tâche invalide.");
        }
    }

    private static void supprimerTache(Scanner scanner, ArrayList<String> tasks) {
        afficherTaches(tasks);
        System.out.print("Entrez le numéro de la tâche à supprimer : ");
        int index = scanner.nextInt() - 1; 
        if (index >= 0 && index < tasks.size()) {
            String tache = tasks.remove(index);
            System.out.println("Tâche supprimée : " + tache);
        } else {
            System.out.println("Numéro de tâche invalide.");
        }

    }
}
