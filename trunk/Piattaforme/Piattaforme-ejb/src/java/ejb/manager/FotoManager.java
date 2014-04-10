/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Foto;
import exception.IdFotoNonValido;
import facade.FotoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author maidenfp
 */
@Stateless
public class FotoManager implements FotoManagerLocal {

    @PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;
    
    @EJB
    private FotoFacade fotoFacade;

    @Override
    public void creaFoto(Foto f) {
       fotoFacade.create(f);
    }

    @Override
    public Foto cercaFotoDaId(Long id) throws IdFotoNonValido{
        Foto res=fotoFacade.find(id);
        if(res==null){
            throw new IdFotoNonValido();
        }
        res.getFoto();
        return  res;
    }

    @Override
    public List<Foto> trovaFotoPerNome(String nome) {
        List<Foto> res;
        Query q=em.createNamedQuery("foto.cercaPerNome");
        q.setParameter(1, nome);
        res=q.getResultList();
        return res;
    }

    @Override
    public void modificaFoto(Foto foto) {
        fotoFacade.edit(foto);
    }

    @Override
    public void eliminaFoto(Foto foto) {
        fotoFacade.remove(foto);
    }
    
    
    @Override
    public List<Foto> getFotoProdotti() {
        Query q=em.createNamedQuery("foto.fotoNoMarchi");
        return q.getResultList();
    }

    @Override
    public boolean esisteFoto(Foto foto){
        Query q=em.createNamedQuery("foto.esisteFoto");
        q.setParameter(1, foto.getNome());
        q.setParameter(2, foto.getFoto());
        List<Foto> res=q.getResultList();
        if(!res.isEmpty()){
            return true;
        }
        return false;
    }
}
