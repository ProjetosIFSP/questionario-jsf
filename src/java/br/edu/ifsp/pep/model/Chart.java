package br.edu.ifsp.pep.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

import br.edu.ifsp.pep.dao.VendaDAO;
import br.edu.ifsp.pep.dto.VendaDTO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class Chart implements Serializable {

    @Inject
    private VendaDAO dao;

    private static final long serialVersionUID = 1L;

    private BarChartModel barModel;

    private int ano;

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @PostConstruct
    public void init() {
        createBarModel();
    }

    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Cachaça");
        barDataSet.setBackgroundColor("rgba(255, 206, 86, 0.6)");

        List<Number> values = new ArrayList<>();

        List<VendaDTO> vendas = new ArrayList<>();

        vendas = dao.findByAno(ano);

        for (int i = 1; i <= 12; i++) {
            if (vendas.contains(new VendaDTO(i, ano, 0.0))) {
                values.add(
                    vendas.get(
                        vendas.indexOf(
                            new VendaDTO(i, ano, 0.0)
                        )
                    ).getValor()
                );
            } else {
                values.add(0.0);
            }
        }

        barDataSet.setData(values);


        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        
        Calendar cal = Calendar.getInstance();

        for (int i = 0; i < 12; i++) {
            cal.set(Calendar.MONTH, i);
            labels.add(new Date(cal.getTimeInMillis()).toLocalDate().getMonth().toString());
        }

        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        linearAxes.setBeginAtZero(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Vendas");
        title.setFontSize(18);
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("bottom");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("italic");
        legendLabels.setFontSize(16);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel.setOptions(options);
    }

    

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public void generateBarModel() {
        createBarModel();
    }
}