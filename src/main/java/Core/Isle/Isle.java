package Core.Isle;

import Core.Agriculture.Agriculture;
import Core.Faction.Faction;
import Core.Industry.Industry;
import Core.Enum.Season;
import Core.Enum.FactionType;
import Core.Enum.DifficultyChoice;
import IndexOfFactionRecuperationMethod.RandomMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Isle {

    private List<Faction> factionList;
    private Industry industry;
    private Agriculture agriculture;
    private Integer treasury;
    private Season season;
    private DifficultyChoice difficultyOfGame;
    private Integer foodUnits;
    private Integer turn;
    private Integer minSatisfactionPercentage;
    /**
     * pour changer la méthode de gestion d'ajout/suppression des partisans, changer le type de
     * indexOfFactionRecuperationMethod à la méthode souhaitée dans le package IndexOfFactionRecuperationMethod
     */
    private RandomMethod indexOfFactionRecuperationMethod = new RandomMethod();

    public Isle(Industry industry, Agriculture agriculture, Integer treasury,
                DifficultyChoice difficultyChoice, Integer foodUnits, Integer minSatisfactionPercentage) {
        this.factionList = this.initialiseFactionList();
        this.industry = industry;
        this.agriculture = agriculture;
        this.treasury = treasury;
        this.season = Season.SPRING;
        this.difficultyOfGame = difficultyChoice;
        this.foodUnits = foodUnits;
        this.turn = 0;
        this.minSatisfactionPercentage = minSatisfactionPercentage;
    }

    public Isle(Industry industry, Agriculture agriculture, Integer treasury,
                DifficultyChoice difficultyChoice, Integer foodUnits, Integer minSatisfactionPercentage,
                List<Faction> factions) {
        this.factionList = factions;
        this.industry = industry;
        this.agriculture = agriculture;
        this.treasury = treasury;
        this.season = Season.SPRING;
        this.difficultyOfGame = difficultyChoice;
        this.foodUnits = foodUnits;
        this.turn = 0;
        this.minSatisfactionPercentage = minSatisfactionPercentage;
    }

    //GETTERS AND SETTERS

    public List<Faction> getFactionList() {
        return this.factionList;
    }

    public void setFactionList(List<Faction> factionList) {
        this.factionList = factionList;
    }

    public Industry getIndustry() {
        return this.industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public Agriculture getAgriculture() {
        return this.agriculture;
    }

    public void setAgriculture(Agriculture agriculture) {
        this.agriculture = agriculture;
    }

    public Integer getTreasury() {
        return this.treasury;
    }

    public void setTreasury(Integer treasury) {
        this.treasury = treasury;
    }

    public Season getSeason() {
        return this.season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public DifficultyChoice getDifficultyOfGame() {
        return this.difficultyOfGame;
    }

    public void setDifficultyOfGame(DifficultyChoice difficultyOfGame) {
        this.difficultyOfGame = difficultyOfGame;
    }

    public Integer getFoodUnits() {
        return this.foodUnits;
    }

    public void setFoodUnits(Integer foodUnits) {
        this.foodUnits = foodUnits;
    }

    public Integer getTurn() {
        return this.turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public Integer getMinSatisfactionPercentage() {
        return this.minSatisfactionPercentage;
    }

    public void setMinSatisfactionPercentage(Integer minSatisfactionPercentage) {
        this.minSatisfactionPercentage = minSatisfactionPercentage;
    }

    /**
     * génération du score selon les paramètres actuels de la partie
     *
     * @return
     */
    public Integer generateScore() {
        Integer score = 0;
        score += 100 * this.turn;
        score += this.treasury;
        score += this.foodUnits;
        score += this.industry.getDedicatedPercentage();
        score += this.agriculture.getDedicatedPercentage();
        switch (this.difficultyOfGame) {
            case normal:
                score *= 2;
                break;
            case hard:
                score *= 3;
                break;
        }
        return score;
    }

    //METHODS

    /**
     * initialise la liste avec toutes les factions à 0
     */
    private List<Faction> initialiseFactionList() {
        List<Faction> factionList = new ArrayList<>();
        factionList.add(new Faction(FactionType.CAPITALISTS, 0, 0));
        factionList.add(new Faction(FactionType.COMMUNISTS, 0, 0));
        factionList.add(new Faction(FactionType.LIBERALS, 0, 0));
        factionList.add(new Faction(FactionType.RELIGIOUS, 0, 0));
        factionList.add(new Faction(FactionType.MILITARISTS, 0, 0));
        factionList.add(new Faction(FactionType.ECOLOGISTS, 0, 0));
        factionList.add(new Faction(FactionType.NATIONALISTS, 0, 0));
        factionList.add(new Faction(FactionType.LOYALISTS, 0, 100));
        return factionList;
    }

    /**
     * augmente la trésorerie du nombre passé en paramètre
     *
     * @param nbAdd
     */
    public void increaseTreasury(int nbAdd) {
        this.treasury += nbAdd;
    }

    /**
     * diminue la trésorerie du nombre passé en paramètre
     *
     * @param nbRemove
     */
    public void decreaseTreasury(int nbRemove) {
        if(nbRemove < 0) {
            nbRemove *= -1;
        }
        this.treasury -= nbRemove;
        if (this.treasury < 0) {
            this.treasury = 0;
        }
    }

    /**
     * augmente les unités de nourriture du nombre passé en paramètre
     *
     * @param nbAdd
     */
    public void increaseFoodUnits(int nbAdd) {
        this.foodUnits += nbAdd;
    }

    /**
     * diminue les unités de nourriture du nombre passé en paramètre
     *
     * @param nbRemove
     */
    public void decreaseFoodUnits(int nbRemove) {
        if(nbRemove < 0) {
            nbRemove *= -1;
        }
        this.foodUnits -= nbRemove;
        if (this.foodUnits < 0) {
            this.foodUnits = 0;
        }
    }

    /**
     * passe à la saison suivante
     */
    public void nextSeason() {
        switch (this.season) {
            case SPRING:
                this.season = Season.SUMMER;
                break;
            case SUMMER:
                this.season = Season.AUTUMN;
                break;
            case AUTUMN:
                this.season = Season.WINTER;
                break;
            case WINTER:
                this.season = Season.SPRING;
                break;
        }
    }

    /**
     * passe au tour suivant
     */
    public void nextTurn() {
        this.turn += 1;
    }

    /**
     * @return pourcentage global de satisfaction des factions
     */
    public int generateGlobalSatisfactionPercentage() {
        int globalSatisfactionPercentage = 0;
        for (Faction faction : this.factionList) {
            globalSatisfactionPercentage += faction.getPercentageApproval();
        }
        globalSatisfactionPercentage = globalSatisfactionPercentage / this.factionList.size();
        return globalSatisfactionPercentage;
    }

    /**
     * coûte 15$ par partisan dans la faction par tranche, et entraînera une hausse de 10 %
     * de la satisfaction de la faction concernée.
     * possible de le faire plusieurs fois de suite sur la même ou sur plusieurs factions (la seule
     * limite étant la trésorerie).
     * corruption --> effet négatif sur vos Loyalistes => perte de satisfaction des loyalistes à
     * hauteur du montant divisé par 10
     *
     * @param indexFaction
     * @return
     */
    public boolean bribe(int indexFaction) {
        int amount = 15 * this.factionList.get(indexFaction).getNbSupporters();
        boolean bribeIsPossible = false;
        //déduire l'argent pour chaque partisan de la faction
        if (this.treasury - amount > 0) {
            bribeIsPossible = true;
            this.decreaseTreasury(amount);
            //augmenter la satisfaction de la faction
            this.factionList.get(indexFaction).increasePercentageApproval(10);
            //diminuer satisfaction loyalistes
            this.getFactionByFactionType(FactionType.LOYALISTS).decreasePercentageApproval(amount / 10);
        }
        return bribeIsPossible;
    }

    /**
     * acheter des unités de nourriture pour compléter l’année qui
     * s’achève. Chaque unité de nourriture coûte 8$.
     *
     * @param nbUnitsPurchased
     */
    public int foodMart(int nbUnitsPurchased) {
        int nbUnitsReallyPurchased = 0;
        //si il y a suffisamment d'argent pour acheter toutes les unités, on achète la totalité
        if (this.treasury - 8 * nbUnitsPurchased >= 0) {
            nbUnitsReallyPurchased = nbUnitsPurchased;
            this.decreaseTreasury(8 * nbUnitsReallyPurchased);
        } else {
            //on achète autant que possible, tant que la trésorerie le permet
            while (nbUnitsPurchased > nbUnitsReallyPurchased && this.treasury - 8 >= 0) {
                nbUnitsReallyPurchased += 1;
                this.decreaseTreasury(8);
            }
        }
        this.foodUnits += nbUnitsReallyPurchased;
        return nbUnitsReallyPurchased;
    }

    /**
     * ➔ Si l’agriculture et la nourriture achetée ne suffisent pas à nourrir la population -->
     * éliminer des partisans jusqu’à ce que la production agricole soit suffisante pour les partisans restants.
     * Chaque partisan éliminé retire 2 % de satisfaction à toutes les factions.
     * Rappel: 4 unités de nourriture par citoyen sont nécessaires
     * ➔ Si l’agriculture seule est excédentaire, il y aura de la natalité sur l’île entraînant
     * l’apparition de nouveaux partisans correspondant à un pourcentage aléatoire compris entre 1 et
     * 10 % de la population globale de l’île.
     * La répartition des nouveaux partisans dans les différentes factions est aléatoire.
     */
    public HashMap<String,Integer> endOfYearReview() {
        HashMap<String, Integer> reviewOfYear = new HashMap();
        //génère l'argent de l'industrie
        this.increaseTreasury(this.industry.getDedicatedPercentage() * 10);
        reviewOfYear.put("Gains de trésorerie grâce à l'industrie", this.industry.getDedicatedPercentage() * 10);
        //génère la nourriture de l'agriculture
        this.increaseFoodUnits(this.agriculture.getDedicatedPercentage() * 40);
        reviewOfYear.put("Gains d'unités de nourriture grâce à l'agriculture", this.agriculture.getDedicatedPercentage() * 40);
        //vérification des partisants de l'île
        int nbTotalSupporters = this.generateNumberTotalOfPartisans();
        reviewOfYear.put("Nombre de partisans debut année", nbTotalSupporters);
        //s'il n'y a pas assez de nourriture pour nourrir tous les habitants
        if (this.foodUnits / 4 < nbTotalSupporters) {
            //on en élimine aléatoirement
            while (nbTotalSupporters > 0 && this.foodUnits / 4 < nbTotalSupporters) {
                //récupération de l'index d'une faction selon la méthode appliquée
                int indexOfFaction = this.indexOfFactionRecuperationMethod.getIndexOfFactionByMethod(this);
                this.factionList.get(indexOfFaction).decreaseNbSupporters(1);
                nbTotalSupporters -= 1;
                for (Faction faction : this.factionList) {
                    faction.decreasePercentageApproval(2);
                }
            }
            this.decreaseFoodUnits(nbTotalSupporters * 4);
        }
        //si l'agriculture seule est excédente
        else if (this.agriculture.generateFoodUnits() / 4 > nbTotalSupporters) {
            this.decreaseFoodUnits(nbTotalSupporters * 4);
            //pourcentage de natalité entre 1 et 10%
            float randomNatalityPercentage = (float) 1 + (int) Math.round(Math.random() * 9);
            reviewOfYear.put("Taux de natalité (%)", (int)randomNatalityPercentage);
            //on génère le pourcentage de supporters selon le pourcentage
            float nbNewSupporters = nbTotalSupporters * (randomNatalityPercentage / 100);
            reviewOfYear.put("Partisans nés cette année", (int) nbNewSupporters);
            //et on les répartit aléatoirement
            for (int i = 0; i < (int) nbNewSupporters; i += 1) {
                //récupération de l'index d'une faction selon la méthode appliquée
                int indexOfFaction = this.indexOfFactionRecuperationMethod.getIndexOfFactionByMethod(this);
                System.out.println(indexOfFaction);
                this.factionList.get(indexOfFaction).increaseNbSupporters(1);
            }
            nbTotalSupporters += nbNewSupporters;
        } else {
            this.decreaseFoodUnits(nbTotalSupporters * 4);
        }
        reviewOfYear.put("Nourriture consommée cette année", nbTotalSupporters * 4);
        reviewOfYear.put("Nombre de partisans fin année", nbTotalSupporters);
        return reviewOfYear;
    }

    /**
     * génère le nombre total de supporters toutes factions confondues
     *
     * @return
     */
    public int generateNumberTotalOfPartisans() {
        int nbTotalSupporters = 0;
        for (Faction faction : this.factionList) {
            nbTotalSupporters += faction.getNbSupporters();
        }
        return nbTotalSupporters;
    }

    /**
     * le président est renversé par coup d’état s’il tombe en dessous du pourcentage global de satisfaction.
     * Ce calcul global s’effectue en prenant en compte l’ensemble des partisans de toutes les factions
     * confondues.
     *
     * @return
     */
    public boolean triggerCoup() {
        return this.generateGlobalSatisfactionPercentage() < this.minSatisfactionPercentage;
    }

    /**
     * teste si au moins une faction peut bénéficier du pot de vin
     *
     * @return
     */
    public boolean bribeIsPossible() {
        int minimumTreasury = 0;
        for (Faction faction : this.factionList) {
            if (faction.getNbSupporters() > 0 && (minimumTreasury == 0 || minimumTreasury > faction.getNbSupporters() * 15)) {
                minimumTreasury = faction.getNbSupporters() * 15;
            }
        }
        return minimumTreasury <= this.treasury;
    }

    /**
     * teste si le pot de vin est possible pour la faction passée en paramètre
     *
     * @param faction
     * @return
     */
    public boolean bribeIsPossible(Faction faction) {
        return (faction.getNbSupporters() > 0 && faction.getNbSupporters() * 15 <= this.treasury);
    }

    /**
     * teste si le marché alimentaire est possible
     *
     * @return
     */
    public boolean foodMartIsPossible() {
        return this.treasury >= 8;
    }

    /**
     * augmente le nombre le partisans dans les factions de façons aléatoire entre les factions
     *
     * @param nbIncrease
     */
    public void increasePartisans(int nbIncrease) {
        while (nbIncrease > 0) {
            int indexOfFaction = -1;
            do {
                //récupération de l'index d'une faction selon la méthode appliquée
                indexOfFaction = this.indexOfFactionRecuperationMethod.getIndexOfFactionByMethod(this);
            } while (this.factionList.get(indexOfFaction).getNbSupporters() == 0
                    && this.generateNumberTotalOfPartisans() > 0);
            this.factionList.get(indexOfFaction).increaseNbSupporters(1);
            nbIncrease -= 1;
        }
    }

    /**
     * diminue le nombre de partisans dans les factions de façon aléatoire entre les factions
     *
     * @param nbDecrease
     */
    public void decreasePartisans(int nbDecrease) {
        if(nbDecrease < 0) {
            nbDecrease *= -1;
        }
        while (nbDecrease > 0) {
            int indexOfFaction = -1;
            do {
                //récupération de l'index d'une faction selon la méthode appliquée
                indexOfFaction = this.indexOfFactionRecuperationMethod.getIndexOfFactionByMethod(this);
            } while (this.factionList.get(indexOfFaction).getNbSupporters() == 0
                    && this.generateNumberTotalOfPartisans() > 0);
            this.factionList.get(indexOfFaction).decreaseNbSupporters(1);
            nbDecrease -= 1;
        }
    }

    //TODO tests unitaires de toutes les méthodes qui suivent
    /**
     * augmente l'industrie du pourcentage passé en paramètre
     * diminue l'agriculture si l'industrie + l'agriculture dépassent 100% à deux
     * @param nbAdd
     */
    public void increaseIndustry(int nbAdd) {
        this.industry.increaseDedicatedPercentage(nbAdd);
        if (this.industry.getDedicatedPercentage() > 100) {
            this.industry.setDedicatedPercentage(100);
        }
        //vérifier que agriculture + industrie n'est pas suppérieur à 100%
        int percentageTot = this.industry.getDedicatedPercentage() + this.agriculture.getDedicatedPercentage();
        if (percentageTot > 100) {
            this.agriculture.decreaseDedicatedPercentage(percentageTot - 100);
        }
    }

    /**
     * diminue l'industrie du pourcentage passé en paramètre
     * @param nbRemove
     */
    public void decreaseIndustry(int nbRemove) {
        if(nbRemove < 0) {
            nbRemove *= -1;
        }
        this.industry.decreaseDedicatedPercentage(nbRemove);
        if (this.industry.getDedicatedPercentage() < 0) {
            this.industry.setDedicatedPercentage(0);
        }
    }
    /**
     * augmente l'agriculture du pourcentage passé en paramètre
     * diminue l'industrie si l'industrie + l'agriculture dépassent 100% à deux
     * @param nbAdd
     */
    public void increaseAgriculture(int nbAdd) {
        this.agriculture.increaseDedicatedPercentage(nbAdd);
        if (this.agriculture.getDedicatedPercentage() > 100) {
            this.agriculture.setDedicatedPercentage(100);
        }
        //vérifier que agriculture + industrie n'est pas suppérieur à 100%
        int percentageTot = this.industry.getDedicatedPercentage() + this.agriculture.getDedicatedPercentage();
        if (percentageTot > 100) {
            this.industry.decreaseDedicatedPercentage(percentageTot - 100);
        }
    }

    /**
     * diminue l'agriculture du pourcentage passé en paramètre
     * @param nbRemove
     */
    public void decreaseAgriculture(int nbRemove) {
        if(nbRemove < 0) {
            nbRemove *= -1;
        }
        this.agriculture.decreaseDedicatedPercentage(nbRemove);
        if (this.agriculture.getDedicatedPercentage() < 0) {
            this.agriculture.setDedicatedPercentage(0);
        }
    }
    //TODO tests unitaires des fonctions du dessus

    /**
     * retourne la faction selon le Faction type passé en paramètre
     * @param factionType
     * @return
     */
    public Faction getFactionByFactionType(FactionType factionType) {
        Faction faction = null;
        for(Faction fac : this.factionList) {
            if(fac.getName() == factionType) {
                faction = fac;
            }
        }
        return faction;
    }
}
