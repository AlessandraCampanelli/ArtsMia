package it.polito.tdp.artsmia.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
private Graph<ArtObject,DefaultWeightedEdge>grafo;
private ArtsmiaDAO dao;
private Map<Integer,ArtObject>idMap;
	public Model() {
	 //NON CREO IL GRAFO NEL MODEL MA NEL CREA GRAFO
	  // grafo=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		dao= new ArtsmiaDAO();
		idMap=new HashMap<Integer,ArtObject>();
	}
	
	public void creaGrafo() {
		
		grafo=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		//AGGIUNGO I VERTICI
		//RECUPERO TUTTI GLI ARTOBJECT DAL DB E LI INSERISCO COME I VERTICI
		dao.listObjects(idMap) ;
		Graphs.addAllVertices(grafo, idMap.values());
		//AGGIUNGERE ARCHI
		//APPROCCIO 1 
		//DOPPIO CICLO FOR SUI VERTICI,DATI DUE VERTICI CONTROLLO SE DEVONO ESSERE COLLEGATI
		
		/*for(ArtObject a1:this.grafo.vertexSet()) {
			for(ArtObject a2:this.grafo.vertexSet()) {
			if(!a1.equals(a2) && !this.grafo.containsEdge(a1,a2))//CONTROLLO SE I DUE ELEMENTI NON SONO UGUALIE SE NON C'è GIA UN ARCO 
			{
				 int peso= this.dao.getPeso(a1,a2);
				 if(peso>0)
					 Graphs.addEdge(this.grafo, a1, a2, peso);
			}	
		    }*/
		
		//APPROCCIO 3 
		for(Adiacenza a :dao.getAdiacenze()) {
				Graphs.addEdge(this.grafo, idMap.get(a.getId1()), idMap.get(a.getId2()), a.getPeso());
			
		}
		}
	}

