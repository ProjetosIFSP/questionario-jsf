package br.edu.ifsp.pep.listener;

import br.edu.ifsp.pep.controller.UsuarioController;
import br.edu.ifsp.pep.controller.FormularioController;
import br.edu.ifsp.pep.controller.ThemeController;
import br.edu.ifsp.pep.model.Usuario;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import jakarta.inject.Inject;
import java.io.IOException;

public class Auth implements PhaseListener {

    @Inject
    private UsuarioController usuarioController;

    @Inject
    private ThemeController themeController;

    @Inject
    private FormularioController formularioController;

    @Override
    public void afterPhase(PhaseEvent event) {

        FacesContext ctx = event.getFacesContext();
        String pagina = ctx.getViewRoot().getViewId();

        boolean isDark = themeController.getDark();
        Usuario usuarioLogado = usuarioController.getUsuarioLogado();

        if (formularioController.getFormulario() != null) {
            // formularioController.unsetFormulario();
        }

        // Colocar validacao de token
        if (usuarioLogado == null && (!pagina.startsWith("/login.xhtml"))) {
            if (isDark) {
                themeController.setDark(false);
            }
            redirect(ctx, "/login.xhtml");
        } else if (usuarioLogado != null && pagina.startsWith("/login.xhtml")) {
            if (!isDark) {
                themeController.setDark(true);
            }
            redirect(ctx, "/index.xhtml");
        } else if (usuarioLogado != null
                && !pagina.startsWith("/unauthorized.xhtml")
                && !pagina.startsWith("/index.xhtml")) {
            if (isDark) {
                themeController.setDark(false);
            }
            redirect(ctx, "/unauthorized.xhtml");
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        // System.out.println("antes da fase: " + event.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    private void redirect(FacesContext ctx, String page) {
        try {
            String path = ctx.getExternalContext()
                    .getRequestContextPath();

            ctx.getExternalContext().redirect(path + page);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
