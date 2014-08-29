package converter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import entity.Provincia;
import java.util.StringTokenizer;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(forClass=Provincia.class)
public class ProvinciaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        StringTokenizer st=new StringTokenizer(value, ",");
        Provincia res=new Provincia();
        int i=0;
        while(st.hasMoreTokens()){
            String token=st.nextToken();
            switch(i){
                case 0:
                    res.setId(Long.parseLong(token));
                    break;
                case 1:
                    res.setNome(token);
                    break;
                case 2:
                    res.setSigla(token);
                    break;
            }
            i++;
        }
        return res;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
    
}
