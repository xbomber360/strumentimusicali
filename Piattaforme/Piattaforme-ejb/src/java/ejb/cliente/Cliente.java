/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.cliente;

import ejb.manager.ClienteManagerLocal;
import ejb.shopping.CarrelloLocal;
import entity.Comune;
import entity.GestoreMagazzino;
import entity.Ordine;
import entity.Provincia;
import entity.Utente;
import facade.ClienteFacadeLocal;
import facade.ComuneFacadeLocal;
import facade.GestoreMagazzinoFacadeLocal;
import facade.ProvinciaFacadeLocal;
import facade.UtenteFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import manager.StatoClienti;

/**
 *
 * @author maidenfp
 */


@Stateful
public class Cliente implements ClienteLocal {
    @EJB
    private UtenteFacadeLocal utenteFacade;

    @EJB
    private CarrelloLocal carrelloEJB;
    @EJB
    private ComuneFacadeLocal comuneFacade;
    @EJB
    private ProvinciaFacadeLocal provinciaFacade;
    
    
    
    @PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;
    
    private Long id;
    private String nome; 
    private Long idProvincia;
    private Long idComune;
    @EJB
    private ClienteFacadeLocal cl;
    @EJB
    private ClienteManagerLocal gl;
    @EJB
    private GestoreMagazzinoFacadeLocal gfl;

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void setID(Long id) {
        this.id = id;
    }

    @Override
    public Long getID() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setComune (Comune c) {
        this.setIDComune(c.getId());
    }
    
    @Override
    public Comune getComune() {
        if (idComune != null) {
            return this.comuneFacade.find(idComune);
        }
        return null;
    }

    @Override
    public void setProvincia(Provincia p) {

        this.setIDProvincia(p.getId());
    }

    @Override
    public Provincia getProvincia() {
        if (idProvincia != null) {
            return this.provinciaFacade.find(idProvincia);
        }
        return null;

    }    
    
    public void setIDProvincia(Long idProvincia) {
        if (provinciaFacade.find(idProvincia) == null) {
            throw new IllegalArgumentException("La provincia selezionata non è presente nel database");
        }
        this.idProvincia = idProvincia;
        
    }

    
    public void setIDComune(Long idComune) {
        if (idProvincia == null) {
            throw new IllegalStateException("Bisogna settare prima la provincia");
        }
        this.idComune = idComune;
        
    }
    
      @Override
    public CarrelloLocal getCarrelloEJB() {
        return carrelloEJB;
    }
    
    @Override
    public boolean isLogged() {
        return this.id != null && this.nome != null;
    }
    
     @Override
    public List<Ordine> getOrdiniCliente() {
        if(id==null){
            return null;
        }
        Query q=em.createNamedQuery("ordine.cercaOrdinePerId");
        q.setParameter(1, id);
        return q.getResultList();
    }

    @Override
    public List <entity.Cliente> cercaTuttiClienti() {
        List<entity.Cliente> res = cl.findAll();
        if(res==null){
           System.out.println("[ClienteManager] Non sono presenti clienti" ); 
        }
        return res;
    }
    
    @Override
    public void attivaCliente(entity.Cliente c) {

        if (c == null) {
            System.out.println("[attivaCliente] null");
            return;
        }
        if (c.getStato().equals(StatoClienti.Attivo) ) {
            System.out.println("[attivaCliente] cliente già attivo");
            return;
        }

        c.setStato(StatoClienti.Attivo);
        cl.edit(c);
        System.out.println("[attivaCliente] stato cambiato");
    }
    
    @Override
    public void bloccaCliente(entity.Cliente c) {

        if (c == null) {
            System.out.println("[bloccaCliente] null");
            return;            
        }
        if (c.getStato().equals(StatoClienti.Bloccato) ) {
            System.out.println("[attivaCliente] cliente già bloccato");
            return;
        }

        c.setStato(StatoClienti.Bloccato);
        cl.edit(c);
        System.out.println("[attivaCliente] stato cambiato bloccato");
    }

    @Override
    public void rimuoviCliente(entity.Cliente c ) {
        if (c == null)
            return;
        Query q = em.createNamedQuery("EsisteCliente");
        q.setParameter(1, c.getId());
        if (q.getResultList().isEmpty()) {
            System.out.println("[ClienteManager] Impossibile eliminare cliente "+ c.getId() + " ciente non trovato" );
            return;
        }
        cl.remove(c); 
        System.out.println("[ClienteManager] Il cliente numero "+ c.getId() + " è stato rimosso con successo" );
        
    }
    
    @Override
    public void promuoviCliente (entity.Cliente c) {
        
        if ( c == null )
            return;
        
        if (c.getStato().equals(StatoClienti.Bloccato) ) {
            System.out.println("Non è possibile promuovere un utente bloccato");
            return;
        }
        Query q = em.createNamedQuery("EsisteCliente");
        q.setParameter(1, c.getId());
        if (q.getResultList().isEmpty()) {
            System.out.println("[ClienteManager] Impossibile trovare cliente da promuovere "+ c.getId() + " ciente non trovato" );
            return;
        }
        GestoreMagazzino gm = new GestoreMagazzino();
        String s = c.getUsername();
        gm.setCognome(c.getCognome());
        gm.setComune(c.getComune());
        gm.setEmail(c.getEmail());
        gm.setNome(c.getNome());
        gm.setUsername("aaaaaa");
        gm.setPassword(c.getPassword());
        gm.setMetodopagamento(c.getMetodopagamento());
        gm.setNascita(c.getNascita());
        gm.setListaOrdini(c.getListaOrdini());
        rimuoviCliente(c);
        System.out.println("[ClienteManager] cliente rimosso");
        
        gfl.create(gm);
           
        System.out.println("[ClienteManager] cliente promosso a gestore magazzino");
        
        
    }
    
    @Override
    public void aggiornaGestore (entity.Cliente c) {
        String s = c.getUsername();
        Utente u = gl.ottieniUtenteEmail (c.getEmail());
        u.setUsername(s);
        gfl.edit((GestoreMagazzino)u);
        
    }
  
    }

