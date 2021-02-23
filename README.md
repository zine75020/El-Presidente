# El-Presidente

Ce projet est un jeu vidéo à la croisée entre Tropico et Reigns. L'utilisateur incarne un jeune dictateur en
herbe sur une île tropicale, fraîchement élu comme Président. Il aura la lourde tâche de faire prospérer cette nouvelle
minirépublique.

## Le jeu

### Factions

Cette république s'articule autour de factions représentant les aspirations idéologiques différentes et
malheureusement concurrentes qui vont influer sur le mandat du joueur. Selon les décisions, les factions gagneront des
partisans et donc, gagneront de l'influence.\
Voici la liste des factions présentes au départ :\
Les capitalistes\
Les communistes\
Les libéraux\
Les religieux\
Les militaristes\
Les écologistes\
Les nationalistes\
Les loyalistes\
Chacune de ces factions a un pourcentage d’approbation du régime en place. Si une faction tombe à 0% de satisfaction,
alors il ne sera plus possible de remonter ce pourcentage (il restera à zéro jusqu’à la fin de la partie). Si une 
faction tombe à 0 partisans, son pourcentage de satisfaction tombe également à 0.

## Industrie, Agriculture & Trésorerie

L’Industrie et l'Agriculture sont deux autres marqueurs qui se cumulent et donc ne peuvent dépasser 100 % à deux.
Attention : le cumul de ces deux marqueurs peut être à moins de 100%.

### Industrie

L’industrialisation représente le pourcentage de l’île dédié à l’industrie. L’industrie génère de l’argent à chaque fin
d’année correspondant à 10 fois le pourcentage de l’île dédié à l’industrie
(donc de 0$ à 1000$).

### Agriculture

L’agriculture représente le pourcentage de l’île dédié à l’agriculture. L’agriculture génère un nombre d’unités de
nourriture chaque année correspondant à 40 fois le pourcentage de surface dédiée à l’agriculture (donc de 0 à 4000
unités de nourriture), sachant qu’un citoyen consomme 4 unités de nourriture par an.

### Trésorerie

La trésorerie représente l’argent disponible dans les caisses de la république. Cet argent peut servir à acheter des
compléments de nourriture pour se prémunir d’une famine, ou à payer des pots-de-vin aux factions qui deviendraient un
problème pour “El Presidente” !

## Lancement d’une partie

### Bac à sable

Le jeu peut se lancer en mode “bac à sable” : les conditions de départs sont celles fixés par un fichier de
configuration annexe. Par défaut, les valeurs sont :\
Satisfaction : la satisfaction sera à 50 % pour toutes les factions, excepté les loyalistes qui sont à 100 %\
Partisans : il y a 15 partisans par faction\
Industrialisation : l’île est industrialisée à 15 %\
Agriculture : l’île est exploitée à 15 %\
Trésor : 200$\
Dans ce mode de jeu, tous les événements sont aléatoires (à l’exception qu’ils doivent tout de même respecter leurs
propres contraintes saisonnières hypothétiques - voir événements).

### Scénario

Le jeu doit pouvoir se lancer avec des scénarios préétablis. Ici, il n’est pas attendu que chaque événement soit déjà
acté pour chaque saison. En revanche, le nombre d’événements est restreint à un ensemble dédié pour ce scénario (si vous
faites un scénario sur la guerre froide, il faut que les événements soient représentatifs de la guerre froide et
qu’aucun événement sur les GAFA ne sorte…). Lorsqu'un scénario est terminé, les événements passent automatiquement
sur des événements bac à sable.

### Difficulté

Le jeu doit proposer un réglage de sa difficulté, ce qui va modifier l’impact des événements sur votre république (et
non la nature des événements).\
Exemple : Si un événement nécessite la perte de 100$, en facile ce sera 50$, en normal 100$ et en difficile 200$.

## Déroulement d’une partie

### Saisons & Evènements

Une saison correspond à un tour de jeu, donc chaque année sera composée de 4 tours de jeu. Lors de chaque saison un
événement se produira (mouvement social, catastrophe naturelle, visite diplomatique d’un pays tiers, erreur fiscale en
votre faveur…) et invitera le joueur à prendre une décision ce qui va influer sur les curseurs du pays (satisfaction,
partisans, industrialisation…).\
À chaque saison, un événement aléatoire sera tiré correspondant à la saison (pas de canicule en hiver donc !) et “El
Presidente” devra choisir parmi diverses réponses, celle qu'il souhaite utiliser pour répondre à la problématique qui se
pose à lui. Un événement peut disposer de 1 choix (donc pas de choix en fait) à 4 choix (il ne faut pas se tromper…).\
Les événements doivent pouvoir influer sur l’ensemble des paramètres du jeu (satisfaction des factions, satisfaction
globale, le trésor, l’industrie, l’agriculture ou le nombre de partisans).\
Il est nécessaire que des événements climatiques soient associés aux bonnes saisons.\
Il existe des événements liés entre eux. Un événement lié à un autre sera forcément succédé par un second (pas
nécessairement immédiat). Par exemple, un événement “L’Assemblée républicaine propose d’augmenter de 5000% le salaire
d’El Presidente”, si le joueur a choisi “oui” alors un événement “manifestation sociale” aura lieu.

## Fin d’année

Toutes les 4 saisons, lors du bilan de fin d’année, “El Presidente” aura plusieurs options qui se présenteront à lui
pour tenter de sauver sa république de l'insurrection.

### Pot de vin

Le joueur aura la possibilité de soudoyer une faction pour augmenter sa satisfaction.\
Cette action coûte 15$ par partisan dans la faction par tranche, et cette opération entraînera une hausse de 10 % de la
satisfaction de la faction concernée. Il est possible de le faire plusieurs fois de suite sur la même faction ou même
sur plusieurs factions (la seule limite étant la trésorerie).\
Par exemple une faction de 8 partisans dont nous voulons augmenter de 20 % (deux fois 10 %) la satisfaction coûtera 15$
x 8 x 2 = 240$).\
En revanche, la corruption a un effet négatif sur vos Loyalistes, cela engendre une perte de satisfaction des loyalistes
à hauteur du montant divisé par 10 (dans l'exemple précédent, 240$/10 = 24% de perte de satisfaction chez les
Loyalistes).\
Après tout… ce n’est pas digne d’un leader aussi charismatique qu’El Presidente!

### Le Marché alimentaire

Le joueur aura la possibilité d’acheter des unités de nourriture pour compléter l’année qui s’achève. Chaque unité de
nourriture coûte 8$.

### Bilan de fin d'année

Lorsque El Presidente a pris ses décisions de fin d’année, alors le bilan tombe !\
➔ Si l’agriculture et la nourriture achetée ne suffisent pas à nourrir la population, alors il faut éliminer des
partisans aléatoirement correspondant à la différence, c’est à dire éliminer des partisans jusqu’à ce que la production
agricole soit suffisante pour les partisans restants. Chaque partisan éliminé retire 2 % de satisfaction à toutes les
factions.\
Rappel: 4 unités de nourriture par citoyen sont nécessaires\
➔ Si l’agriculture seule est excédentaire, alors il y aura de la natalité sur l’île entraînant l’apparition de nouveaux
partisans correspondant à un pourcentage aléatoire compris entre 1 et 10 % de la population globale de l’île (et oui, ça
gonfle vite).\
La répartition des nouveaux partisans dans les différentes factions est aléatoire.

## Conditions de défaite

Selon le niveau de difficulté, à votre convenance, le président est renversé par coup d’état s’il tombe en dessous d’un
pourcentage global de satisfaction. Par exemple 10 % pour une partie facile, ou 50 % pour une partie difficile.\
Ce calcul global s’effectue en prenant en compte l’ensemble des partisans de toutes les factions confondues.\
Pour un exemple simplifié, une partie de cette sorte :\
Les capitalistes 15 % (15 partisans)\
Les communistes 65 % (35 partisans)\
Les libéraux 80 % (2 partisans)\
Donne le calcul suivant : (15 x 15 + 65 x 35 + 80 x 2)/(15+35+2) = 51,15 % de satisfaction globale\
Cette difficulté doit être définie par le mode de jeu en début de partie.

## Conditions de victoire

Il n’y en a pas ! C’est un jeu où il faut tenir le plus longtemps, donc un jeu où le score est important.

## Difficulté par événement

La difficulté des événements d’un scénario varie selon le niveau de jeu en ajoutant ou en supprimant des choix
prédéfinis : en plus avoir le multiplicateur pour la difficulté, certains événements vont avoir des choix en moins, ou
des choix en plus pour rendre encore la tâche bien plus ardue.

## Tests

Des tests unitaires sont ajoutés auprojet pour permettre d’en valider la robustesse.