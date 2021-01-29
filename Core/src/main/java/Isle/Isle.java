package Isle;

import Agriculture.Agriculture;
import Faction.Faction;
import Industry.Industry;
import Enum.Season;
import Enum.FactionType;

import java.util.ArrayList;
import java.util.List;

public class Isle {

    private List<Faction> factionList;
    private Industry industry;
    private Agriculture agriculture;
    private Integer tresorery;
    private Season season;
    private Integer foodUnits;
    private Integer turn;
    private Integer minSatisfactionPercentage;
    private Integer score;

    public Isle(Industry industry, Agriculture agriculture, Integer tresorery,
                Integer minSatisfactionPercentage) {
        this.factionList = this.initialiseFactionList();
        this.industry = industry;
        this.agriculture = agriculture;
        this.tresorery = tresorery;
        this.season = Season.SPRING;
        this.foodUnits = 0;
        this.turn = 0;
        this.minSatisfactionPercentage = minSatisfactionPercentage;
        this.score = 0;
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

    public Integer getTresorery() {
        return this.tresorery;
    }

    public void setTresorery(Integer tresorery) {
        this.tresorery = tresorery;
    }

    public Season getSeason() {
        return this.season;
    }

    public void setSeason(Season season) {
        this.season = season;
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

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    //METHODS

    /**
     * initialise la liste avec toutes les factions à 0
     */
    //TODO ajouter un paramètre pour initialiser selon les paramètres du jeu et modifier la méthode en conséquence
    private List<Faction> initialiseFactionList() {
        List<Faction> factionList = new ArrayList<>();
        factionList.add(new Faction(FactionType.CAPITALISTES, 0, 0));
        factionList.add(new Faction(FactionType.COMMUNISTES, 0, 0));
        factionList.add(new Faction(FactionType.LIBERAUX, 0, 0));
        factionList.add(new Faction(FactionType.RELIGIEUX, 0, 0));
        factionList.add(new Faction(FactionType.MILITARISTES, 0, 0));
        factionList.add(new Faction(FactionType.ECOLOGISTES, 0, 0));
        factionList.add(new Faction(FactionType.NATIONALISTES, 0, 0));
        factionList.add(new Faction(FactionType.LOYALISTES, 0, 100));
        return factionList;
    }

    /**
     * augmente la trésorerie du nombre passé en paramètre
     *
     * @param nbAdd
     */
    public void increaseTresorery(int nbAdd) {
        this.tresorery += nbAdd;
    }

    /**
     * diminue la trésorerie du nombre passé en paramètre
     *
     * @param nbRemove
     */
    public void decreaseTresorery(int nbRemove) {
        this.tresorery -= nbRemove;
        if (this.tresorery < 0) {
            this.tresorery = 0;
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

    //TODO
    public void setScore() {

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
        if (this.tresorery - amount > 0) {
            bribeIsPossible = true;
            this.decreaseTresorery(amount);
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
        if (this.tresorery - 8 * nbUnitsPurchased >= 0) {
            nbUnitsReallyPurchased = nbUnitsPurchased;
            this.decreaseTresorery(8 * nbUnitsReallyPurchased);
        } else {
            //on achète autant que possible, tant que la trésorerie le permet
            while (nbUnitsPurchased > nbUnitsReallyPurchased && this.tresorery - 8 >= 0) {
                nbUnitsReallyPurchased += 1;
                this.decreaseTresorery(8);
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
        this.increaseTresorery(this.industry.getDedicatedPercentage() * 10);
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
