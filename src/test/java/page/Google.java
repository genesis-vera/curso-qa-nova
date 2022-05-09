package page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.suport.FindBy;

public class Google {

    //Capturar elementos
    @FindBy(className = "gLFyf gsfi")
    WebElement inputBuscador;

    @FindBy(xpath = "//input[contains(@data-ved, '@0ahUKEwjKisOk_czxAhXIH7kGHd1BKoQ4dUDCAs')]")
    WebElement btnBuscar;

    //Constructor:
    public Google(WebDriver webDriver){
        PageFactory.initElements(webDriver, page: this);
    }

    //Crear un método público:
    public void ingresarBusqueda(String busqueda){
        inputBuscador.sendKeys(busqueda);
    }

    //Crear otro método público que presione el botón "buscar"
    public void clickBtnBuscar(){
        btnBuscar.click();
    }
}
