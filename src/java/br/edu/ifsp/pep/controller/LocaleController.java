package br.edu.ifsp.pep.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Named
@SessionScoped
public class LocaleController implements Serializable {

    private Locale locale;
    private List<Locale> locales = List.of(Locale.US, new Locale("pt", "BR"));

    public Locale getLocale() {
        return locale;
    }

    public List<Locale> getLocales() {
        return locales;
    }

    public void setLocale(Locale locale) {
      this.locale = locale;
    }

    public void setLocaleUS() {
        //Alterar o locale da aplicação
        FacesContext
                .getCurrentInstance()
                .getViewRoot()
                .setLocale(Locale.US);
        
        //Guarda o locale atual
        this.locale = Locale.US;
    }

    public void setLocalePTBR() {
        FacesContext
                .getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("pt", "BR"));
        
        this.locale = new Locale("pt", "BR");
    }
}
