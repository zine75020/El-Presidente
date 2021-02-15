package JsonEventsRepository;

import EventParsers.Choice;
import EventParsers.Effect;
import EventParsers.Event;
import EventParsers.EventsRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Enum.FactionType;
import Enum.Season;

public class JsonEventsRepository implements EventsRepository {

    //TODO update les chemins après déplacement du fichier json
    //attack on titans
    public static final String EASY_ATTACK_ON_TITANS_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Events/attackOnTitans/easy-titans-events.json";
    public static final String NORMAL_ATTACK_ON_TITANS_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Events/attackOnTitans/normal-titans-events.json";
    public static final String DIFFICULT_ATTACK_ON_TITANS_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Events/attackOnTitans/difficult-titans-events.json";
    public static final int ATTACK_ON_TITANS_ID = 1;
    //cold war URSS
    public static final String EASY_COLD_WAR_URSS_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Events/coldWarURSS/easy-urss.json";
    public static final String NORMAL_COLD_WAR_URSS_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Events/coldWarURSS/normal-urss.json";
    public static final String DIFFICULT_COLD_WAR_URSS_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Events/coldWarURSS/difficult-urss.json";
    public static final int COLD_WAR_URSS_ID = 2;
    //cold war USA
    public static final String EASY_COLD_WAR_USA_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Events/coldWarUSA/easy-usa.json";
    public static final String NORMAL_COLD_WAR_USA_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Events/coldWarUSA/normal-usa.json";
    public static final String DIFFICULT_COLD_WAR_USA_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Events/coldWarUSA/difficult-usa.json";
    public static final int COLD_WAR_USA_ID = 3;
    //the 100
    public static final String EASY_THE_100_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Events/the100/easy-the100-events.json";
    public static final String NORMAL_THE_100_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Events/the100/normal-the100-events.json";
    public static final String DIFFICULT_THE_100_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Events/the100/difficult-the100-events.json";
    public static final int THE_100_ID = 4;
    //jojo's aventure
    public static final String EASY_JOJO_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Events/jojo/easy-jojo-events.json";
    public static final String NORMAL_JOJO_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Events/jojo/normal-jojo-events.json";
    public static final String DIFFICULT_JOJO_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Events/jojo/difficult-jojo-events.json";
    public static final int JOJO_ID = 5;
    //TODO ajouter les chemins quand on aura les scénarios de Gabriel
    //neutral events
    public static final String EASY_NEUTRAL_EVENTS_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Events/neutrals-events/easy-neutrals-events.json";
    public static final String NORMAL_NEUTRAL_EVENTS_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Events/neutrals-events/normal-neutrals-events.json";
    public static final String DIFFICULT_NEUTRAL_EVENTS_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Events/neutrals-events/difficult-neutrals-events.json";
    //bac à sable
    public static final int SANDBOX_ID = 7;

    /**
     * récupère le chemin du fichier json associé à l'id et à la difficulté
     * puis lecture du fichier json pour la récupération de
     * la liste de tous les événements dans une certaine difficulté
     * associés au scénario dont l'id est passé en paramètre
     *
     * @param selectedScenarioId
     * @param difficulty
     * @return
     */
    @Override
    public List<Event> getAllEventsByIdAndDifficulty(Integer selectedScenarioId, Integer difficulty) {
        List<Event> allEvents = new ArrayList<>();

        /*
         * traitement uniquement si ce n'est pas un bac à sable sinon on se retrouve avec 2 listes identiques
         * (événements du scénario bac à sable + événements neutres qui sont les mêmes)
         */
        if (selectedScenarioId != SANDBOX_ID) {
            //récupération du chemin du fichier correspondant à l'id et à la difficulté
            String path = this.getPathByIdAndDifficulty(selectedScenarioId, difficulty);

            //lecture du fichier json
            try (FileReader reader = new FileReader(path)) {
                JsonObject myobject = (JsonObject) new JsonParser().parse(reader);

                //récupération de la liste d'événements
                allEvents = this.parseEvents(myobject);
            } catch (IOException e) {
                System.out.println("Nous ne parvenons pas à récupérer les événements du scénario");
            }
        }
        return allEvents;
    }

    /**
     * récupère le chemin du fichier json d'événements neutres associé à la difficulté
     * puis lecture du fichier json pour la récupération de
     * la liste de tous les événements dans une certaine difficulté
     * associés au scénario dont l'id est passé en paramètre
     *
     * @param difficulty
     * @return
     */
    @Override
    public List<Event> getNeutralEventsByDifficulty(Integer difficulty) {
        List<Event> neultralEvents = new ArrayList<>();

        //récupération du chemin du fichier json d'événements neutres associé à la difficulté
        String path = this.getPathByIdAndDifficulty(SANDBOX_ID, difficulty);

        //lecture du fichier json
        try (FileReader reader = new FileReader(path)) {
            JsonObject myobject = (JsonObject) new JsonParser().parse(reader);

            //récupération de la liste d'événements
            neultralEvents = this.parseEvents(myobject);
        } catch (IOException e) {
            System.out.println("Nous ne parvenons pas à récupérer les événements du scénario");
        }
        return neultralEvents;
    }

    /**
     * récupération de la partie événements du fichier json
     * pour la récupération de la liste d'événements
     *
     * @param myobject
     * @return
     */
    @Override
    public List<Event> parseEvents(JsonObject myobject) {
        List<Event> allEvents = new ArrayList<>();

        //récupération du tableau events du fichier json
        JsonArray events = (JsonArray) myobject.get("events");

        //parsing des événements
        this.parseEvents(events, allEvents);

        return allEvents;
    }

    /**
     * récupération de la partie relatedEvents du fichier json
     * pour la récupération de la liste d'événements dépendants
     *
     * @param myobject
     * @return
     */
    @Override
    public List<Event> parseRelatedEvents(JsonObject myobject) {
        List<Event> allEvents = new ArrayList<>();

        //récupération du tableau relatedEvents du fichier json
        JsonArray events = (JsonArray) myobject.get("relatedEvents");

        //parsing des événements s'il y en a
        if (myobject.get("relatedEvents") != null) this.parseEvents(events, allEvents);

        return allEvents;
    }

    /**
     * parsing de la liste des événements contenue dans le tableau json passé en paramètres
     *
     * @param events
     * @param allEvents
     */
    @Override
    public void parseEvents(JsonArray events, List<Event> allEvents) {
        //traitement de tous les événements du tableau json
        for (JsonElement event : events) {
            //conversion en un json object
            JsonObject ev = event.getAsJsonObject();

            //récupération du nom
            String name = ev.get("name").getAsString();

            //récupération des saisons (si renseignées)
            JsonObject seas = ev.get("seasons") != null ? ev.get("seasons").getAsJsonObject() : new JsonObject();
            Map<Season, Boolean> seasons = new HashMap<>();
            if (seas.get("spring") != null) seasons.put(Season.SPRING, seas.get("spring").getAsBoolean());
            if (seas.get("summer") != null) seasons.put(Season.SUMMER, seas.get("summer").getAsBoolean());
            if (seas.get("autumn") != null) seasons.put(Season.AUTUMN, seas.get("autumn").getAsBoolean());
            if (seas.get("winter") != null) seasons.put(Season.WINTER, seas.get("winter").getAsBoolean());

            //récupération des choix de l'événement
            JsonArray choices = ev.get("choices").getAsJsonArray();
            List<Choice> allChoices = this.getAllChoices(choices);

            //ajout de l'événement dans la liste
            allEvents.add(new Event(name, seasons, allChoices));
        }
    }

    /**
     * parsing de la liste des choix contenue dans le tableau json passé en paramètres
     *
     * @param choices
     * @return
     */
    @Override
    public List<Choice> getAllChoices(JsonArray choices) {
        List<Choice> allChoices = new ArrayList<>();

        //traitement de tous les choix du tableau json
        for (JsonElement choice : choices) {
            //conversion en un json object
            JsonObject ch = choice.getAsJsonObject();

            //récupération de la description du choix
            String choiceDescription = ch.get("choice").getAsString();

            //récupération des effets du choix
            JsonArray effects = ch.get("effects").getAsJsonArray();
            List<Effect> allEffects = this.getAllEffects(effects);

            //récupération des événements dépendants de ce choix
            List<Event> allRelatedEvents = this.parseRelatedEvents(ch);

            //ajout du choix dans la liste
            allChoices.add(new Choice(choiceDescription, allEffects, allRelatedEvents));
        }

        return allChoices;
    }

    /**
     * parsing de la liste des effets contenue dans le tableau json passé en paramètres
     *
     * @param effects
     * @return
     */
    @Override
    public List<Effect> getAllEffects(JsonArray effects) {
        List<Effect> allEffects = new ArrayList<>();

        //traitement de tous les effets du tableau json
        for (JsonElement effect : effects) {
            //conversion en un json object
            JsonObject eff = effect.getAsJsonObject();

            //récupération des actions sur chaque factions individuellement (si renseignée)
            Map<FactionType, Integer> actionsOnFaction = new HashMap<>();
            if (eff.get("CAPITALISTS") != null)
                actionsOnFaction.put(FactionType.CAPITALISTS, eff.get("CAPITALISTS").getAsInt());
            if (eff.get("COMMUNISTS") != null)
                actionsOnFaction.put(FactionType.COMMUNISTS, eff.get("COMMUNISTS").getAsInt());
            if (eff.get("LIBERALS") != null) actionsOnFaction.put(FactionType.LIBERALS, eff.get("LIBERALS").getAsInt());
            if (eff.get("RELIGIOUS") != null)
                actionsOnFaction.put(FactionType.RELIGIOUS, eff.get("RELIGIOUS").getAsInt());
            if (eff.get("MILITARISTS") != null)
                actionsOnFaction.put(FactionType.MILITARISTS, eff.get("MILITARISTS").getAsInt());
            if (eff.get("ECOLOGISTS") != null)
                actionsOnFaction.put(FactionType.ECOLOGISTS, eff.get("ECOLOGISTS").getAsInt());
            if (eff.get("LOYALISTS") != null)
                actionsOnFaction.put(FactionType.LOYALISTS, eff.get("LOYALISTS").getAsInt());
            if (eff.get("NATIONALISTS") != null)
                actionsOnFaction.put(FactionType.NATIONALISTS, eff.get("NATIONALISTS").getAsInt());

            //récupération des actions sur les facteurs (si renseigné)
            Map<String, Integer> actionsOnFactor = new HashMap<>();
            if (eff.get("TREASURY") != null) actionsOnFactor.put("TREASURY", eff.get("TREASURY").getAsInt());
            if (eff.get("INDUSTRY") != null) actionsOnFactor.put("INDUSTRY", eff.get("INDUSTRY").getAsInt());
            if (eff.get("AGRICULTURE") != null) actionsOnFactor.put("AGRICULTURE", eff.get("AGRICULTURE").getAsInt());

            //récupération de l'effet sur les partisans (si renseigné)
            Integer partisans = eff.get("partisans") != null ? eff.get("partisans").getAsInt() : 0;

            //ajout de l'effet dans la liste
            allEffects.add(new Effect(actionsOnFaction, actionsOnFactor, partisans));
        }
        return allEffects;
    }

    /**
     * récupération du chemin du fichier selon l'id du scénario et la difficulté choisis
     *
     * @param selectedScenarioId
     * @param difficulty
     * @return
     */
    @Override
    public String getPathByIdAndDifficulty(Integer selectedScenarioId, Integer difficulty) {
        String path;
        switch (selectedScenarioId) {
            case ATTACK_ON_TITANS_ID:
                switch (difficulty) {
                    case 1:
                        path = EASY_ATTACK_ON_TITANS_FILE_PATH;
                        break;
                    case 3:
                        path = DIFFICULT_ATTACK_ON_TITANS_FILE_PATH;
                        break;
                    default:
                        path = NORMAL_ATTACK_ON_TITANS_FILE_PATH;
                }
                break;
            case COLD_WAR_URSS_ID:
                switch (difficulty) {
                    case 1:
                        path = EASY_COLD_WAR_URSS_FILE_PATH;
                        break;
                    case 3:
                        path = DIFFICULT_COLD_WAR_URSS_FILE_PATH;
                        break;
                    default:
                        path = NORMAL_COLD_WAR_URSS_FILE_PATH;
                }
                break;
            case COLD_WAR_USA_ID:
                switch (difficulty) {
                    case 1:
                        path = EASY_COLD_WAR_USA_FILE_PATH;
                        break;
                    case 3:
                        path = DIFFICULT_COLD_WAR_USA_FILE_PATH;
                        break;
                    default:
                        path = NORMAL_COLD_WAR_USA_FILE_PATH;
                }
                break;
            case THE_100_ID:
                switch (difficulty) {
                    case 1:
                        path = EASY_THE_100_FILE_PATH;
                        break;
                    case 3:
                        path = DIFFICULT_THE_100_FILE_PATH;
                        break;
                    default:
                        path = NORMAL_THE_100_FILE_PATH;
                }
                break;
            case JOJO_ID:
                switch (difficulty) {
                    case 1:
                        path = EASY_JOJO_FILE_PATH;
                        break;
                    case 3:
                        path = DIFFICULT_JOJO_FILE_PATH;
                        break;
                    default:
                        path = NORMAL_JOJO_FILE_PATH;
                }
                break;
            //TODO ajout de la récupération des chemins quand on aura les scénarios de Gabriel
            //événements neutres par défaut (bac à sable)
            default:
                switch (difficulty) {
                    case 1:
                        path = EASY_NEUTRAL_EVENTS_FILE_PATH;
                        break;
                    case 3:
                        path = DIFFICULT_NEUTRAL_EVENTS_FILE_PATH;
                        break;
                    default:
                        path = NORMAL_NEUTRAL_EVENTS_FILE_PATH;
                }
        }
        return path;
    }
}
