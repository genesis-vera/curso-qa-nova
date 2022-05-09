package testSuite;

import testClass.BusquedaAnimalesGoogle;
import utils.Constants.Navegador;
import utils.DriverContext;
import utils.Reporte.PdfQaNovaReports;

public class Prueba {

    //Crear la variable:
    ChromeDriver webDriver;
    String url = "https://google.cl";

    //Crear los métodos BeforeTest y AfterTest:

    //Crear el método setUp:
    @BeforeTest
    public void setUp(){
        DriverContext.setUp(Navegador.Chrome, url);
        PdfQaNovaReports.createPDF();
    }

    //Crear el public void close
    @AfterTest
    public void closeDriver(){
        DriverContext.closeDriver();
        PdfQaNovaReports.closePDF();
    }

    //Crear la prueba:
    @Test
    public void pruebaLogin(){
        Logeo logeo = new Logeo(webDriver);
        logeo.CasoLogin1(usuario: "nvivas", clave: "qanova");
    }
}
