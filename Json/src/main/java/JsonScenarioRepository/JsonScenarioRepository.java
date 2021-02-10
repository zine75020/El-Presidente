package JsonScenarioRepository;

import EventParsers.Event;
import Faction.Faction;
import JsonEventsRepository.JsonEventsRepository;
import ScenarioParsers.GameStartParameters;
import ScenarioParsers.Scenario;
import ScenarioParsers.ScenarioRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Enum.FactionType;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonScenarioRepository implements ScenarioRepository {

    /**
     * retourne la liste des scénarios contenus dans le fichier json dont le chemin est passé en paramètres
     *
     * @return
     */
    @Override
    public List<Scenario> getAllScenarios(String path) {
        List<Scenario> allScenarios = new ArrayList<>();

        //lecture du fichier json
        try (FileReader reader = new FileReader(path)) {
            JsonObject myobject = (JsonObject) new JsonParser().parse(reader);

            //parsing des scénarios (informations suffisantes pour le menu seulement)
            allScenarios = parseScenariosForMenu(myobject);
        } catch (IOException e) {
            System.out.println("Nous ne parvenons pas à récupérer les scénarios du jeu");
        }
        return allScenarios;
    }

    /**
     * récupération de la liste des ids de scénarios contenus dans le fichier json passé en paramètre
     *
     * @param path
     * @return
     */
    @Override
    public List<Integer> getAllScenariosIds(String path) {
        List<Integer> allIds = new ArrayList<>();

        //lecture du fichier json
        try (FileReader reader = new FileReader(path)) {
            JsonObject myobject = (JsonObject) new JsonParser().parse(reader);

            //récupération de tous les ids
            allIds = parseScenariosForIds(myobject);
        } catch (IOException e) {
            System.out.println("Nous ne parvenons pas à récupérer les ids des scénarios du jeu");
        }
        return allIds;
    }

    /**
     * récupération du scénario complètement parsé contenu dans le fichier json passé en paramètre
     * selon l'id et la difficulté choisis
     *
     * @param path
     * @param selectedScenarioId
     * @param difficulty
     * @return
     */
    @Override
    public Scenario getScenarioByIdAndDifficulty(String path, Integer selectedScenarioId, Integer difficulty) {
        Scenario scenario = null;
        JsonEventsRepository jsonEventsRepository = new JsonEventsRepository();

        //lecture du fichier json
        try (FileReader reader = new FileReader(path)) {
            JsonObject myobject = (JsonObject) new JsonParser().parse(reader);
            //récupération du tableau des scénarios
            JsonArray scenarios = (JsonArray) myobject.get("scenarios");
            JsonObject selectedScenario = new JsonObject();
            //on cherche le scénario correspondant à l'id
            for (JsonElement scenar : scenarios) {
                JsonObject sc = scenar.getAsJsonObject();
                //si c'est le bon scénario, on le mémorise et on sort de la boucle
                if (sc.get("id").getAsInt() == selectedScenarioId) {
                    selectedScenario = sc;
                    break;
                }
            }
            //récupération du nom
            String name = selectedScenario.get("name").getAsString();

            //récupération de la description
            String story = selectedScenario.get("story").getAsString();

            //récupération des paramètres de début de jeu
            GameStartParameters gameStartParameters = this.getGameStartParameters(selectedScenario, difficulty);

            //récupération des événements du scénario
            List<Event> scenarioEvents = jsonEventsRepository.getAllEventsByIdAndDifficulty(selectedScenarioId, difficulty);

            //récupération des événements neutres (si jamais on termine le scénario)
            List<Event> neutralEvents = jsonEventsRepository.getNeutralEventsByDifficulty(difficulty);

            scenario = new Scenario(selectedScenarioId, name, story, gameStartParameters, scenarioEvents, neutralEvents);
        } catch (IOException e) {
            System.out.println("Nous ne parvenons pas à récupérer le scénario");
        }
        return scenario;
    }

    /**
     * récupération des paramètres de début de jeu selon la difficulté
     *
     * @param selectedScenario
     * @param difficulty
     * @return
     */
    @Override
    public GameStartParameters getGameStartParameters(JsonObject selectedScenario, Integer difficulty) {
        //récupération de la clé json selon la difficulté
        String difficultyKey = this.getDifficultyKey(difficulty);

        //récupération de l'objet correspondant à la difficulté
        selectedScenario = (JsonObject) selectedScenario.get("gameStartParameters");
        selectedScenario = (JsonObject) selectedScenario.get(difficultyKey);

        //récupération du pourcentage d'agriculture
        int agriculturePercentage = selectedScenario.get("agriculturePercentage").getAsInt();

        //récupération du pourcentage d'industrie
        int industryPercentage = selectedScenario.get("industryPercentage").getAsInt();

        //récupération de la trésorerie
        int treasury = selectedScenario.get("treasury").getAsInt();

        //récupération du nombre d'unités de nourritures
        int foodUnits = selectedScenario.get("foodUnits").getAsInt();

        //récupération de la liste des factions paramétrée
        List<Faction> factions = this.getFactions(selectedScenario);

        return new GameStartParameters(agriculturePercentage, industryPercentage, treasury, foodUnits, factions);
    }

    /**
     * récupération de la liste des factions paramétrée
     *
     * @param selectedScenario
     * @return
     */
    @Override
    public List<Faction> getFactions(JsonObject selectedScenario) {
        List<Faction> factions = new ArrayList<>();

        //récupération de l'objet des factions
        selectedScenario = (JsonObject) selectedScenario.get("factions");

        //récupération et traitement de chaque faction une à une
        JsonObject faction = (JsonObject) selectedScenario.get("CAPITALISTS");
        factions.add(new Faction(FactionType.CAPITALISTS,
                faction.get("numberOfPartisans").getAsInt(),
                faction.get("satisfactionPercentage").getAsInt()));

        faction = (JsonObject) selectedScenario.get("COMMUNISTS");
        factions.add(new Faction(FactionType.COMMUNISTS,
                faction.get("numberOfPartisans").getAsInt(),
                faction.get("satisfactionPercentage").getAsInt()));

        faction = (JsonObject) selectedScenario.get("LIBERALS");
        factions.add(new Faction(FactionType.LIBERALS,
                faction.get("numberOfPartisans").getAsInt(),
                faction.get("satisfactionPercentage").getAsInt()));

        faction = (JsonObject) selectedScenario.get("RELIGIOUS");
        factions.add(new Faction(FactionType.RELIGIOUS,
                faction.get("numberOfPartisans").getAsInt(),
                faction.get("satisfactionPercentage").getAsInt()));

        faction = (JsonObject) selectedScenario.get("MILITARISTS");
        factions.add(new Faction(FactionType.MILITARISTS,
                faction.get("numberOfPartisans").getAsInt(),
                faction.get("satisfactionPercentage").getAsInt()));

        faction = (JsonObject) selectedScenario.get("ECOLOGISTS");
        factions.add(new Faction(FactionType.ECOLOGISTS,
                faction.get("numberOfPartisans").getAsInt(),
                faction.get("satisfactionPercentage").getAsInt()));

        faction = (JsonObject) selectedScenario.get("LOYALISTS");
        factions.add(new Faction(FactionType.LOYALISTS,
                faction.get("numberOfPartisans").getAsInt(),
                faction.get("satisfactionPercentage").getAsInt()));

        faction = (JsonObject) selectedScenario.get("NATIONALISTS");
        factions.add(new Faction(FactionType.NATIONALISTS,
                faction.get("numberOfPartisans").getAsInt(),
                faction.get("satisfactionPercentage").getAsInt()));

        return factions;
    }

    /**
     * récupération de la clé json de difficulté selon la difficulté (int)
     *
     * @param difficulty
     * @return
     */
    @Override
    public String getDifficultyKey(Integer difficulty) {
        String difficultyKey;
        switch (difficulty) {
            case 1:
                difficultyKey = "EASY";
                break;
            case 3:
                difficultyKey = "DIFFICULT";
                break;
            default:
                difficultyKey = "NORMAL";
        }
        return difficultyKey;
    }

    /**
     * récupération de la liste de scénarios contenant uniquement les informations nécéssaires pour le menu
     *
     * @param myobject
     * @return
     */
    private List<Scenario> parseScenariosForMenu(JsonObject myobject) {
        List<Scenario> allScenarios = new ArrayList<>();

        //récupération de l'objet scénarios
        JsonArray scenarios = (JsonArray) myobject.get("scenarios");

        //traitement de chaque scénario
        for (JsonElement scenario : scenarios) {
            //conversion en un json object
            JsonObject sc = scenario.getAsJsonObject();

            //ajout du scénario avec les informations essentielles
            allScenarios.add(new Scenario(sc.get("id").getAsInt(), sc.get("name").getAsString(), sc.get("story").getAsString()));
        }
        return allScenarios;
    }

    /**
     * récupération de la liste les ids des scénarios
     *
     * @param myobject
     * @return
     */
    private List<Integer> parseScenariosForIds(JsonObject myobject) {
        List<Integer> allIds = new ArrayList<>();

        //récupération de l'objet scénarios
        JsonArray scenarios = (JsonArray) myobject.get("scenarios");

        //traitement de chaque scénario
        for (JsonElement scenario : scenarios) {
            //conversion en un json object
            JsonObject sc = scenario.getAsJsonObject();

            //ajout de l'id du scénario dans la liste
            allIds.add(sc.get("id").getAsInt());
        }
        return allIds;
    }
}