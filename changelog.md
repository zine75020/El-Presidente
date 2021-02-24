# Change Log
Toutes les modifications notables apportées à ce projet seront documentées dans ce fichier.

## [Unreleased]
### Changed
- Gestion de l'ajout/suppression des partisans via différents algorithmes
  - récupération de l'id de la Faction où l'ajout/suppression sera effectué selon l'algorithme sélectionné

## [1.0.1] - 2021-02-21
### Added
- Affichage du bilan lors de la fin d'année
### Changed
- Correction de l'application des effets défaillante
  - Remise à decreased lorsqu'il fallait réduire
  - Repassage des paramètres en positif s'ils étaient négatifs pour les réductions de valeurs 
    (car enlever un nombre négatif revenait à l'ajouter)

## [1.0.0] - 2021-02-20
### Added
#### Classes
- Application
  - Game (main)
- ConsoleInput (contrôle des saisies)
- ConsoleOutput (gestion des sorties)
- Classes métier : Core
    - Activity
    - Agriculture
    - Enum
        - DifficultyChoice, FactionType, Season
    - EventParsers
        - Choice, Effect, Event, EventsRepository
    - Faction
    - Industry
    - Input
    - Isle
    - Output
    - ScenarioParser, GameStartParameters, Scenario, ScenarioRepository
- JsonEventsRepository
- JsonScenarioRepository
- Json (fichiers de configuration Json)

#### Fonctionnalités
- Lancement de partie
- Sélection de difficulté (affichage menu + choix de l'utilisateur)
- Parsing des fichiers Json
- Choix du scénario (affichage menu + choix de l'utilisateur)
- Récupération de la satisfaction minimale de la partie
- Création de l'île
  - Gestion des Factions
  - Gestion de la trésorerie
  - Gestion des Activités (Industrie + Agriculture)
  - Gestion de la saison
  - Gestion des unités de nourriture
  - Gestion des tours de la partie
- Affichage bilan (informations importantes sur la partie)
- Choix événement (affichage événement et ses choix ainsi que ses effets + choix de l'utilisateur)
- Événements dépendants
- Application choix événement (application des effets du choix de l'utilisateur)
- Traitement de fin d'année (affichage dans le menu uniquement des choix possibles)
  - Pot de vin
  - Marché alimentaire
  - Bilan de fin d'année
- Coup d'état (lorsque satisfaction globale trop basse)
- Calcul du score (prenant en compte tous les facteurs du jeu)
- Gestion des erreurs