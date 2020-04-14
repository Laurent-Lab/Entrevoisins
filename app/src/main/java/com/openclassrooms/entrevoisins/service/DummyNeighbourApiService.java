package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    ///
    private List<Neighbour> fNeighbours = DummyNeighbourGenerator.generateFavorisNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    ///Récupérer ma liste de favoris
    public List<Neighbour> getFneighbours(){return fNeighbours;}

    ///Ajouter un favori dans ma liste de favoris
    public void addFavorisNeighbour(Neighbour fNeighbour){fNeighbours.add(fNeighbour);}

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    ///
    public void deleteFneighbour(Neighbour neighbour){fNeighbours.remove(neighbour);}

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }


}
