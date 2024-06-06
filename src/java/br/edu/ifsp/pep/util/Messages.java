package br.edu.ifsp.pep.util;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

/**
 *
 * @author aluno
 */
public class Messages {

    private static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public static void addMessageSuccess(String content) {
        addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", content);
    }

    public static void addMessageWarning(String content) {
        addMessage(FacesMessage.SEVERITY_WARN, "Atenção", content);
    }

    public static void addMessageError(String content) {
        addMessage(FacesMessage.SEVERITY_ERROR, "Erro", content);
    }
}
