package ConsoleOutput;

import Core.Enum.FactionType;
import Core.Faction.Faction;
import Core.Isle.Isle;
import Core.Output.Output;
import Core.Enum.DifficultyChoice;
import Core.ScenarioParsers.Scenario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsoleOutput implements Output {

    @Override
    public String welcome() {
        return "Bienvenue sur El Presidente\n";
    }

    @Override
    public String startGame() {
        return "Début de la partie !\n";
    }

    @Override
    public String endGame() {
        return "Votre population vous renverse par coup d'état... Fin de la partie.\n";
    }

    @Override
    public String impossibleGame() {
        return "Aucun événements disponibles. Fin de la partie.";
    }

    @Override
    public StringBuilder gameInformations(Isle isle) {
        StringBuilder gameInformations = new StringBuilder("============== Informations Île | tour : ").append(isle.getTurn()).append(" ==============\n");
        gameInformations.append("=== Trésorerie : ").append(isle.getTreasury()).append("\n");
        gameInformations.append("=== Unités de nourriture : ").append(isle.getFoodUnits()).append("\n");
        gameInformations.append("=== Pourcentage d'industrie : ").append(isle.getIndustry().getDedicatedPercentage()).append("%\n");
        gameInformations.append("=== Pourcentage d'agriculture : ").append(isle.getAgriculture().getDedicatedPercentage()).append("%\n");
        gameInformations.append("=== Saison actuelle : ").append(isle.getSeason()).append("\n");
        gameInformations.append("=== Satisfaction globale : ").append(isle.generateGlobalSatisfactionPercentage()).
                append("% (partie perdue à ").append(isle.getMinSatisfactionPercentage()).append("%)\n\n");
        gameInformations.append("======== Factions : ========\n");
        //Alignement de l'affichage des factions
        for (Faction faction : isle.getFactionList()) {
            gameInformations.append("=== ").append(faction.getName());
            for (int i = 0; i < 12; i += 1) {
                if (i >= faction.getName().name().length()) {
                    gameInformations.append(" ");
                }
            }
            gameInformations.append(" : ");
            gameInformations.append(faction.getNbSupporters()).
                    append(" partisans | ").append(faction.getPercentageApproval()).append("% d'approbation\n");
        }
        return gameInformations;
    }

    @Override
    public StringBuilder difficultyMenu() {
        StringBuilder menu = new StringBuilder("Avec quelle difficulté voulez-vous jouer ?\n");
        int index = 0;
        // ajout de la liste des choix possibles
        for (DifficultyChoice choice : DifficultyChoice.values()) {
            index += 1;
            menu.append(index).append(": ").append(choice).append("\n");
        }
        return menu;
    }

    public StringBuilder scenarioMenu(List<Scenario> allScenarios) {
        StringBuilder menu = new StringBuilder("Quel est le scénario que vous souhaitez dérouler ?\n");
        // ajout de la liste des choix possibles
        for (Scenario scenario : allScenarios) {
            menu.append(scenario.getId()).append(": ").append(scenario.getName()).append("\n").
                    append(scenario.getStory()).append("\n");
        }
        return menu;
    }

    @Override
    public StringBuilder printScore(Isle isle) {
        return new StringBuilder("========= Score : ").append(isle.generateScore()).append(" =========");
    }

    @Override
    public StringBuilder endOfYearInfo() {
        return new StringBuilder("C'est la fin de l'année !\n");
    }

    @Override
    public StringBuilder endOfYearMenu(Isle isle) {
        StringBuilder menu = new StringBuilder("Que souhaitez-vous faire ?\n");
        // ajout de la liste des choix possibles
        int i = 1;
        //on affiche seulement les choix qui sont faisables
        if(isle.bribeIsPossible()) {
            menu.append(i).append(": Pot de vin\n");
        }
        i += 1;
        if(isle.foodMartIsPossible()) {
            menu.append(i).append(": Marché Alimentaire\n");
        }
        i += 1;
        menu.append(i).append(": Bilan de fin d'année\n");

        return menu;
    }

    @Override
    public StringBuilder bribeMenu(Isle isle) {
        StringBuilder menu = new StringBuilder("Pour quelle faction voulez-vous faire le pot de vin ?\n");
        // ajout de la liste des choix possibles
        int i = 1;
        for(Faction faction : isle.getFactionList()) {
            //on affiche seulement les choix qui sont faisables
            if(isle.bribeIsPossible(faction)) {
                menu.append(i).append(": ").append(faction.getName()).append("\n");
            }
            i += 1;
        }

        return menu;
    }

    @Override
    public StringBuilder foodMarksAsk(Isle isle) {
        return new StringBuilder("Combien d'unités de nourriture souhaitez-vous acheter ?\n");
    }

    @Override
    public StringBuilder bribeCoast(int coast) {
        return new StringBuilder("Le pot de vin vous a coûté ").append(coast).append(" de la trésorerie\n");
    }

    @Override
    public StringBuilder loyalistsDiminutionSatisfaction(int coast) {
        return new StringBuilder("La satisfaction des LOYALISTS diminue de ").append(coast/10).append("%");
    }

    public StringBuilder nbUnitSet(int nbFood) {
        int priceFood = nbFood * 8;
        return new StringBuilder("Vous avez acheté "+ nbFood +" unités de nourriture, vous avez donc dépensé "+ priceFood +" de la trésorerie au marché alimentaire\n");
    }

    public StringBuilder bribeInfos(FactionType factionName) {
        return new StringBuilder("Vous venez de fournir un pot de vin à la faction "+ factionName +", sa satisfaction augmente de 10%");
    }
    public StringBuilder bribeEchec(FactionType factionName) {
        return new StringBuilder("Vous ne pouvez pas fournir de pot de vin à la faction "+ factionName +"\n");
    }
    public StringBuilder endOfYearReviewInfos(HashMap<String, Integer> reviewOfYear) {
        StringBuilder bilanOfYears = new StringBuilder("============== Bilan de Fin d'année : ==============\n");
        for(Map.Entry<String, Integer> entry : reviewOfYear.entrySet()) {
            bilanOfYears.append("==== "+ entry.getKey() +" : "+ entry.getValue() +" ====\n");
        }
        return bilanOfYears;
    }

    public String valueError() {
        return "Veuillez saisir une valeur numérique supérieure à 0\n";
    }

    public String valueOfMenuError() {
        return "Veuillez saisir une valeur présente dans le menu\n";
    }
}
