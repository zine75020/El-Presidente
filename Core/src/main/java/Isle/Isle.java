package Isle;

import Agriculture.Agriculture;
import Faction.Faction;
import Industry.Industry;
import Enum.Season;
import Enum.FactionType;
import Enum.DifficultyChoice;

import java.util.ArrayList;
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
            case normal -> score *= 2;
            case hard -> score *= 3;
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
    //TODO gérer quand la faction n'a plus de partisants
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
            this.factionList.get(7).decreasePercentageApproval(amount / 10);
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
    public void endOfYearReview() {
        //génère l'argent de l'industrie
        this.increaseTreasury(this.industry.getDedicatedPercentage() * 10);
        //génère la nourriture de l'agriculture
        this.increaseFoodUnits(this.agriculture.getDedicatedPercentage() * 40);
        //vérification des partisants de l'île
        int nbTotalSupporters = this.generateNumberTotalOfSupporters();
        //s'il n'y a pas assez de nourriture pour nourrir tous les habitants
        if(this.foodUnits/4 < nbTotalSupporters) {
            //on en élimine aléatoirement
            while(nbTotalSupporters > 0 && this.foodUnits/4 < nbTotalSupporters) {
                int randomIndexFaction = this.getRandomIndexOfFaction();
                this.factionList.get(randomIndexFaction).decreaseNbSupporters(1);
                nbTotalSupporters -= 1;
                for(Faction faction : this.factionList) {
                    faction.decreasePercentageApproval(2);
                }
            }
            this.decreaseFoodUnits(nbTotalSupporters * 4);
        }
        //si l'agriculture seule est excédente
        else if(this.agriculture.generateFoodUnits()/4 > nbTotalSupporters) {
            this.decreaseFoodUnits(nbTotalSupporters * 4);
            //pourcentage de natalité entre 1 et 10%
            float randomNatalityPercentage = (float) 1 + (int) Math.round(Math.random() * 9);
            //on génère le pourcentage de supporters selon le pourcentage
            float nbNewSupporters = nbTotalSupporters * (randomNatalityPercentage/100);
            //et on les répartit aléatoirement
            for(int i = 0 ; i < (int) nbNewSupporters ; i += 1) {
                int randomIndexFaction = this.getRandomIndexOfFaction();
                this.factionList.get(randomIndexFaction).increaseNbSupporters(1);
            }
        }
        else {
            this.decreaseFoodUnits(nbTotalSupporters * 4);
        }
    }

    /**
     * nombre aléatoire entre 0 et 7 (car 8 factions)
     * @return
     */
    public int getRandomIndexOfFaction() {
        return (int) Math.round(Math.random() * 7);
    }

    /**
     * génère le nombre total de supporters toutes factions confondues
     * @return
     */
    public int generateNumberTotalOfSupporters() {
        int nbTotalSupporters = 0;
        for(Faction faction : this.factionList) {
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

}
