package IndexOfFactionRecuperationMethod;

import Core.Isle.Isle;

/**
 * Méthode permettant d'ajouter/supprimer les partisans dans les factions de façon aléatoire
 */
public class RandomRepartitionMethod implements IndexOfFactionRecuperationMethod{

    /**
     * nombre aléatoire entre 0 et 7 (car 8 factions)
     *
     * @return
     */
    @Override
    public int getIndexOfFactionByMethod(Isle isle) {
        return (int) Math.round(Math.random() * (isle.getFactionList().size() - 1));
    }

}
