package TADs;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

import Excepcions.ExisteixVertex;
import Excepcions.NoExisteixAresta;
import Excepcions.NoExisteixVertex;



public class Graf<K, Vertex, Aresta> {
	
	private class NodeVertex{
			
		Vertex vertex;
		ArrayList<NodeAresta> LlistaArestes;
		
	}
	public class NodeAresta{
		K clau;
		Aresta aresta;
	}
	private Hashtable<K, NodeVertex> HashVertex;
	
	/**
	 * Aquesta operació crea una estructura Graf, sense cap element.
	 */
	public Graf(){
		HashVertex=new Hashtable<K, NodeVertex>();
	}
	
	
	/**
	 * Aquesta operació afegeix un nou vèrtex al graf. S’ha de comprovar que si ClauVertex ja existeix
salti una excepció.
	 * @param clau clau
	 * @param vertex vertex
	 * @throws ExisteixVertex Existeix Vertex
	 */
	public void AddVertex(K clau, Vertex vertex) throws ExisteixVertex{
		if(HashVertex.get(clau)==null){
			NodeVertex aux=new NodeVertex();
			aux.vertex=vertex;
			HashVertex.put(clau, aux);
		}
		else throw new ExisteixVertex();
	}
	
	/**
	 * Aquesta operació crea una aresta entre els vèrtexs ClauVertex1 i ClauVertex2, i hi guarda el
contingut de ValorLink. Si ja existia l’aresta es modifica el seu contingut.
	 * @param clau1 equip local
	 * @param clau2 equip visitant
	 * @param aresta partit
	 * @throws NoExisteixVertex No Existeix Vertex
	 */
	public void AddLink (K clau1, K clau2, Aresta aresta) throws NoExisteixVertex{
		NodeVertex nodever= HashVertex.get(clau1);
		if(nodever==null) throw new NoExisteixVertex();
		NodeVertex nodever2= HashVertex.get(clau2);
		if(nodever2==null) throw new NoExisteixVertex();
		
		if(nodever.LlistaArestes==null){
			nodever.LlistaArestes=new ArrayList<NodeAresta>();
		}
		if(nodever2.LlistaArestes==null){
			nodever2.LlistaArestes=new ArrayList<NodeAresta>();
		}
		
		NodeAresta node=new NodeAresta();
		node.aresta=aresta;
		node.clau=clau2;
		boolean afegit=false;
		
		
		
		for(NodeAresta arestaaux: nodever.LlistaArestes){
			if(arestaaux.clau.equals(clau2)){
				arestaaux.aresta=aresta;
				afegit=true;
			}
		}
		
		if(afegit==false){
			
			nodever.LlistaArestes.add(node);
		}
		afegit=true;
		node=new NodeAresta();
		node.aresta=aresta;
		node.clau=clau1;
		
	
		for(NodeAresta arestaaux: nodever2.LlistaArestes){
			if(arestaaux.clau.equals(clau1)){
				arestaaux.aresta=aresta;
				afegit=true;
			}
		}
		
		if(afegit==false){
			
			nodever2.LlistaArestes.add(node);
		}
	}
	
	/**
	 * Aquesta operació retorna el ValorVertex emmagatzemat al vèrtex identificat per la Clau que
passem com a paràmetre. Dóna error si no hi ha cap vèrtex identificat per la Clau.
	 * @param clau clau
	 * @return ValorVertex ValorVertex
	 * @throws NoExisteixVertex NoExisteixVertex
	 */
	public Vertex getVertex(K clau) throws NoExisteixVertex{
		NodeVertex node= HashVertex.get(clau);
		if(node==null) throw new NoExisteixVertex();
		return node.vertex;
		
	}
	
	/**
	 * Aquesta operació retorna el ValorLink emmagatzemat a l’aresta identificada per les Claus que
passem com a paràmetre. Dóna error si no hi ha cap aresta identificada per les Claus.
	 * @param clau1 equip local
	 * @param clau2 equip visitant
	 * @return ValorLink ValorLink
	 * @throws NoExisteixAresta NoExisteixAresta
	 */
	public Aresta getLink(K clau1, K clau2) throws NoExisteixAresta{
		Aresta aresta=null;
		
		for(NodeAresta link: HashVertex.get(clau1).LlistaArestes){
			if(link.clau.equals(clau2)){
				aresta=link.aresta;
			}
		}
		if(aresta==null) throw new NoExisteixAresta();
		return aresta;
		
		
	}
	
	/**
	 * Aquesta operació retorna un vector amb tots els vèrtexs introduïts dins de l’estructura.
	 * @return vector amb tots els vèrtexs
	 */
	public Vector<K> getAllVertex(){
		Vector<K> vectorclaus=new Vector<K>();
		Enumeration<K> llistavertex=HashVertex.keys();
		
		while(llistavertex.hasMoreElements()){
			vectorclaus.addElement(llistavertex.nextElement());
		}
		return vectorclaus;
		
	}
	
	/**
	 * Aquesta operació retorna una taula de hashing (hashmap) amb totes les parelles ClauVertex
destí/ValorLink que té el vèrtex que passem com a paràmetre.
	 * @param clau clau
	 * @return retorna una taula de hashing (hashmap) amb totes les parelles ClauVertex
	 * @throws NoExisteixVertex NoExisteixVertex
	 */
	public HashMap<K,Aresta> getAllLinks(K clau) throws NoExisteixVertex{
		NodeVertex node= HashVertex.get(clau);
		if(node==null) throw new NoExisteixVertex();
		
		ArrayList<NodeAresta> arestesllista=node.LlistaArestes;
		HashMap<K, Aresta>	aux=new HashMap<K, Aresta>();
		for(NodeAresta aresta: arestesllista){
			aux.put(aresta.clau, aresta.aresta);
		}
		return aux;		
	}
	
	/**
	 * Aquesta operació elimina del graf el vèrtex identificat per ClauVertex1 que passem per
paràmetre i totes les seves arestes. Retorna error si ClauVertex no existeix.
	 * @param clau clau
	 * @throws NoExisteixVertex NoExisteixVertex
	 */
	public void removeVertex(K clau ) throws NoExisteixVertex{
		NodeVertex node= HashVertex.get(clau);
		if(node==null) throw new NoExisteixVertex();
		HashVertex.remove(clau);
		
		Enumeration<K> llistaclauvertex=HashVertex.keys();
		
	
		while(llistaclauvertex.hasMoreElements()){
			ArrayList<NodeAresta> arestesllista=HashVertex.get(llistaclauvertex.nextElement()).LlistaArestes;
			NodeAresta aresta2=null;
			for(NodeAresta aresta: arestesllista){
				if(aresta.clau.equals(clau))
				aresta2=aresta;
			}
		
			arestesllista.remove(aresta2);
			
		}
		
	}
	
	/**
	 * Aquesta operació elimina del graf l’aresta que uneix ClauVertex1 i ClauVertex2. Retorna error
si l’aresta no existeix o si algun dels dos ClauVertex no existeix.
	 * @param clau1 equip local
	 * @param clau2 equip visitant
	 * @throws NoExisteixVertex NoExisteixVertex
	 * @throws NoExisteixAresta NoExisteixAresta
	 */
	public void removeLink(K clau1, K clau2) throws NoExisteixVertex, NoExisteixAresta{
		NodeVertex node1= HashVertex.get(clau1);
		NodeVertex node2= HashVertex.get(clau2);
		if(node1==null)throw new NoExisteixVertex();
		if(node2==null)throw new NoExisteixVertex();
		boolean borrat=false;
		
		ArrayList<NodeAresta> arestesllista=HashVertex.get(clau1).LlistaArestes;
		NodeAresta arestaborrar=null;
		
		for(NodeAresta aresta: arestesllista){
			if(aresta.clau.equals(clau2)){
				
				arestaborrar=aresta;
				borrat=true;
			}
				
		}
		
		
		if(borrat==false) throw new NoExisteixAresta();	
		
		NodeAresta arestaborrar2=null;
		arestesllista=HashVertex.get(clau2).LlistaArestes;
		for(NodeAresta aresta2: arestesllista){
			if(aresta2.clau.equals(clau1)){
				arestaborrar2=aresta2;
				borrat=true;
			}
		}
		if(borrat==true){
			arestesllista.remove(arestaborrar);
			arestesllista.remove(arestaborrar2);
		}
				
	}
	

}
