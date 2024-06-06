package br.edu.ifsp.pep.listener;

import br.edu.ifsp.pep.controller.PessoaController;
import br.edu.ifsp.pep.model.Pessoa;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import jakarta.inject.Inject;
import java.io.IOException;

public class Auth implements PhaseListener {

    @Inject
    private PessoaController pessoaController;

    @Override
    public void afterPhase(PhaseEvent event) {

        FacesContext ctx = event.getFacesContext();
        String pagina = ctx.getViewRoot().getViewId();

        Pessoa pessoaLogada = pessoaController.getPessoaLogada();

        if (pessoaLogada == null && (!pagina.startsWith("/login.xhtml")
            && !pagina.startsWith("/unauthorized.xhtml")
            && !pagina.startsWith("/financeiro/report.xhtml")
            && !pagina.startsWith("/financeiro/list.xhtml")
            && !pagina.startsWith("/chart.xhtml"))) {
            redirect(ctx, "/login.xhtml");
        } else if (pessoaLogada != null && pagina.startsWith("/login.xhtml")) {
            redirect(ctx, "/index.xhtml");
        } else if (pessoaLogada != null
                    && !pessoaLogada.getTipo().getAcessos().contains(pagina)
                    && !pagina.startsWith("/financeiro/report.xhtml")
                    && !pagina.startsWith("/financeiro/list.xhtml")
                    && !pagina.startsWith("/unauthorized.xhtml")
                    && !pagina.startsWith("/chart.xhtml")) {
            redirect(ctx, "/unauthorized.xhtml");
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
      //System.out.println("antes da fase: " + event.getPhaseId());
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
