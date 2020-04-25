package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    //Lister, ajouter un voisin un voisin /
    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }
    //Supprimer un voisin
    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }
    //Ajouter un voisin dans la liste de favoris
    //Vérifier si le voisin est bien ajouté à la liste de favoris
    @Test
    public void addFneighbourWithSucces() {
        Neighbour neighbourToAdd = service.getNeighbours().get(0);
        service.addFavorisNeighbour(neighbourToAdd);
        assertTrue(service.getFneighbours().contains(neighbourToAdd));
    }
    //Vérifier si mon voisin ne fait plus partie des favoris lorsque l'on utilise à nouveau la méthode addFavorisNeighbour
    @Test
    public void deleteFneighbourWitchSucces() {
        Neighbour neighbour = service.getNeighbours().get(0);
        service.addFavorisNeighbour(neighbour);
        Neighbour neighbourToDelete = service.getFneighbours().get(0);
        service.addFavorisNeighbour(neighbourToDelete);
        assertFalse(service.getFneighbours().contains(neighbourToDelete));
    }
    //Ajouter une liste de voisins
    @Test
    public void getfNeighboursWithSucces(){
        List<Neighbour> fNeighbours = service.getFneighbours();
        List<Neighbour> fNeighboursExpected = DummyNeighbourGenerator.FAVORIS_NEIGHBOURS;
        assertThat(fNeighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(fNeighboursExpected.toArray()));
    }


}
