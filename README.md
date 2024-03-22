# Evaluation API JAVASpring

## Connexion utilisateur
- **Création d'un utilisateur**: http://localhost:8080/signin
  - Méthode : POST
  - Champs renseigner:
    - Pseudo
    - Mot de passe
    - Role
- **Connexion d'un utilisateur** : http://localhost:8080/login
  - Méthode : POST
    - Champs renseigner :
      - Pseudo
      - Mot de passe

## Permettre de lister les chantiers (réservé au chef de chantier)
- **Liste des chantier en fonction de l'utilisateur**: http://localhost:8080/chantier
  - Méthode : POST
  - Champs récupérés:
    - Nom du chantier
    - Adresse du chantier
    - Le client
    - L'ouvrier du chantier

## Permettre de connaitre le temps total pour un chantier (réservé au chef de chantier)
- **Temps totale du chantier** : http://localhost:8080/chantier/time/{id}
  - Méthode : GET
  - Champs récupérés:
    - Temps du chantier

## Permettre a un employé de connaitre la liste des opération qu'il doit réaliser (réservé au chef de chantier et aux ouvriers) : 
- **Liste des opérations en fonctions de l'utilisateur** : http://localhost:8080/operation/list
  - Méthode : GET
  - Champs récupérés:
    - L'id du chantier
    - Nom du chantier
    - Adresse du chantier
    - Nom du client
    - Nom du chef de chantier
    - Nom de l'ouvrier en charge de l'opération
    - L'id de la tache
    - Nom de la tache
    - Temps de la tache

