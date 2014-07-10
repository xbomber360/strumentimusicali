/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import ejb.manager.ProdottoManagerLocal;
import entity.Prodotto;
import javax.ejb.EJB;

/**
 *
 * @author siciliano
 */
public class GestioneProdottoBean {
    @EJB
    private ProdottoManagerLocal prodottoManager;
    private Long id=null;
    private String marca=null;
    private String nome=null;
    private String urlFoto=null;
    private String descrizione=null;
    private int prezzo=0;
    private int quantità=0;
    

    /**
     * Creates a new instance of GestioneProdottoBean
     */
    public GestioneProdottoBean() {
        
    }
    
    public void cercaProdotto(Long id){
        Prodotto p =prodottoManager.cercaProdottoPerId(id);
        this.id = p.getId();
        marca=p.getMarca().getNome();
        nome=p.getNome();
        urlFoto=p.getFoto();
        descrizione=p.getDescrizione();
        prezzo=p.getPrezzo();
        quantità=p.getQuantita();
        
    }

    public ProdottoManagerLocal getProdottoManager() {
        return prodottoManager;
    }

    public String getMarca() {
        return marca;
    }

    public String getNome() {
        return nome;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public int getQuantità() {
        return quantità;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }
    
    
    
    
}
