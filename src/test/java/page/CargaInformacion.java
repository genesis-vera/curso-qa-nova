package page;

import com.sun.javafx.binding.SelectBinding;
import com.sun.javafx.binding.StringFormatter;
import utils.DriverContext;
import utils.Reporte.EstadoPrueba;
import utils.Reporte.PdfQaNovaReports;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class CargaInformacion {
    @FindBy(id = "imPgTitle")
    private WebElement titulo;
    //WebDriver webDriver;

    @FindBy(xpath = "//input[@id='inObjectForm_1_2']")
    private WebElement campoTexto;

    @FindBy(xpath = "//input[@id='inObjectForm_1_3']")
    private WebElement campoCorreo;

    @FindBy(xpath = "//input[@id='inObjectForm_1_4']")
    private WebElement campoTextArea;

    @FindBy(xpath = "//input[@id='inObjectForm_1_5']")
    private WebElement campoFecha;

    @FindBy(xpath = "//input[@id='inObjectForm_1_6']")
    private WebElement campoLista;

    @FindBy(id = "inObjectForm_1_7_0']")
    private WebElement checkbxSeleccionMultiple1;

    @FindBy(id = "inObjectForm_1_7_1']")
    private WebElement checkbxSeleccionMultiple2;

    @FindBy(id = "inObjectForm_1_7_2']")
    private WebElement checkbxSeleccionMultiple3;

    @FindBy(id = "inObjectForm_1_8_0']")
    private WebElement rdbtnCombo1;

    @FindBy(id = "inObjectForm_1_8_1']")
    private WebElement rdbtnCombo2;

    @FindBy(id = "inObjectForm_1_8_2']")
    private WebElement rdbtnCombo3;

    @FindBy(id = "inObjectForm_1_submit")
    private WebElement btnEnviar;

    @FindBy(xpath = "//*[@id=\"inObjectForm_1_buttonswrap\"]/input[2]")
    private WebElement btnReset;

    WebDriverWait webDriverWait;

    public CargaInformacion(){
        PageFactory.initElements(DriverContext.getDriver(), page: this);
        this.webDriverWait = new WebDriverWait(DriverContext.getDriver(), timeOutlnSeconds: 30);
    }

    //Crear método de espera:
    public String recuperarTitulo(){
        webDriverWait.until(ExpectedConditions.visibilityOf(titulo));
        PdfQaNovaReports.addWebReportImage(nombrePaso: "Despliegue carga de informacion", descripcion: "Carga de información desplegada correctamente", EstadoPrueba.PASSED, fatal:false);
        String texto = titulo.getText();
        return texto;
    }

    //Crear public void para rellenar los campos
    public void rellenarCampoTexto(String texto){
        campoTexto.sendKeys(texto);
    }

    public void rellenarCampoMail(String mail){
        campoCorreo.sendKeys(mail);
    }

    public void rellenarCampoAreaTexto(String areaTexto){
        campoTextArea.sendKeys(areaTexto);
    }

    public void rellenarFecha(String fecha){
        campoFecha.sendKeys(fecha);
    }

    public void rellenarLista(String valor){
        Select select = new Select(campoLista);
        select.selectByVisibleText(valor);
    }

    //Seleccionar una o varias opciones:
    public void seleccionMultiple(String indicador) {
        String[] indicadores = indicador.split(regex:" , ");
        for (String nro : indicadores) {
            int numero = Integer.parseInt(nro);

            switch (numero) {
                case 1:
                    checkbxSeleccionMultiple1.click();
                    break;

                case 2:
                    checkbxSeleccionMultiple2.click();
                    break;

                case 3:
                    checkbxSeleccionMultiple3.click();
                    break;
                default:
                    System.out.println("Valor no procesable");
            }
        }
    }

    public void seleccionRadioButton(int indicador){
        switch (indicador){
            case 1:
                rdbtnCombo1.click();
            case 2:
                rdbtnCombo2.click();
            case 3:
                rdbtnCombo3.click();
            default:
                System.out.println("Valor no procesable");
        }
    }

    public void clickBtnEnviar(){
        PdfQaNovaReports.addWebReportImage(nombrePaso: "Datos formulario", descripcion: "Se ingresan datos al formulario", EstadoPrueba.PASSED, fatal:false);
        btnEnviar.click();
    }

    public void clickBtnReset(){
        btnReset.click();
    }

    public void seleccionarFechaCalendario(String fecha) throws ParseException{
        iconoCalendario.click();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pafers: "yyyy-mm-dd");
        String hoy = simpleDateFormat.format(new Date());
        Date hoyDate = simpleDateFormat.parse(hoy);
        Date fechaDate = simpleDateFormat.parse(fecha);
        long diferencia = ChronoUnit.MONTHS.between(LocalDate.parse(hoy).withMonth(1), LocalDate.parse(fecha).withDayOfMonth(1));
        int dia = Integer.parseInt(fecha.substring(fecha.length()-2));
        int meses;
        if (hoyDate.after(fechaDate)){
            meses = (int)(diferencia * -1);
            for (int x = 0; x <= meses -1; x++){
                btnRetrocederMes.click();
            } else {
                meses = (int) diferencia;
                for (int x = 0; x <= meses -1; x++){
                    btnAvanzarMes.click();
                }
            }
            PdfQaNovaReports.addBDReportImage(nombrePaso: "Seleccion fecha calendario", descripcion: "Se selecciona fecha: " + fecha + " desde calendario", EstadoPrueba.PASSED, fatal:false);
            DriverContext.getDriver().findElement(By.xpath("//div[@id = 'inDPcal']//td[text() = "+ dia + "]")).click();
        }
    }
}
