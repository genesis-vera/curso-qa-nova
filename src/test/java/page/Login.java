package page;

import utils.DriverContext;
import utils.Reporte.EstadoPrueba;
import utils.Reporte.PdfQaNovaReports;

public class Login {

    @FindBy(id = "imUname")
    private WebElement inputUsuario;

    @FindBy(id = "imPad")
    private WebElement inputClave;

    @FindBy(xpath = "//input[@value='Ingresar a Demo']")
    private WebElement btnIngresar;

    WebDriverWait webDriverWait;

    public Login(){
        PageFactory.initElements(DriverContext.getDriver(), page: this);
        this.webDriverWait = new WebDriverWait(DriverContext.getDriver(), timeOutlnSeconds 30);
    }

    public void ingresarUsuarios(String usuario){
        webDriverWait.until(ExpectedConditions.visibilityOf(inputUsuario));
        PdfQaNovaReports.addWebReportImage(nombrePaso: "Despiegue Login", descripcion: "Login desplegado correctamente", EstadoPrueba.PASSED, fatal: true);
        inputUsuario.sendKeys(usuario);
    }

    public void ingresarClave(String clave){
        inputClave.sendKeys(clave);
    }

    public void clickBtnIngresar(){
        PdfQaNovaReports.addWebReportImage(nombrePaso: "Datos Login", descripcion: "Se ingresa usuario y contraseña", EstadoPrueba.PASSED, fatal: false);
        btnIngresar.click();
    }
}
