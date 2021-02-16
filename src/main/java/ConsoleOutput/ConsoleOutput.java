package ConsoleOutput;

import Core.Faction.Faction;
import Core.Isle.Isle;
import Core.Output.Output;
import Core.Enum.DifficultyChoice;
import Core.ScenarioParsers.Scenario;

import java.util.List;

public class ConsoleOutput implements Output {

    @Override
    public String welcome() {
        return "Bienvenue sur El Presidente\n";
    }

    @Override
    public String startGame() {
        return "Début de la partie !";
    }

    @Override
    public String endGame() {
        return "Votre population vous renverse par coup d'état... Fin de la partie.";
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
        StringBuilder introduction = new StringBuilder("Avec quelle difficulté voulez-vous jouer ?\n");
        int index = 0;
        // ajout de la liste des choix possibles
        for (DifficultyChoice choice : DifficultyChoice.values()) {
            index += 1;
            introduction.append(index).append(": ").append(choice).append("\n");
        }
        return introduction;
    }

    public StringBuilder scenarioMenu(List<Scenario> allScenarios) {
        StringBuilder introduction = new StringBuilder("Quel est le scénario que vous souhaitez dérouler ?\n");
        // ajout de la liste des choix possibles
        for (Scenario scenario : allScenarios) {
            introduction.append(scenario.getId()).append(": ").append(scenario.getName()).append("\n").
                    append(scenario.getStory()).append("\n");
        }
        return introduction;
    }

    @Override
    public StringBuilder printScore(Isle isle) {
        return new StringBuilder("========= Score : ").append(isle.generateScore()).append(" =========");
    }

    public String valueOfMenuError() {
        return "Veuillez saisir une valeur présente dans le menu\n";
    }
}
