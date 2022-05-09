package testClass;

import page.CargaInformacion;

public class Login {

    private Login login;
    private CargaInformacion cargaInformacion;

    public Login(){

    }

    public void CasoLogin1(String usuario, String clave){
        login = new Login();
        cargaInformacion = new CargaInformacion();
        login.ingresarUsuario(usuario);
        login.ingresarClave(clave);
        login.clickBtnIngresar();
        cargaInformacion.recuperarTitulo();
        cargaInformacion.rellenarCampoTexto("hola");
        cargaInformacion.rellenarCampoMail("lvenegas@qanova.cl");
        cargaInformacion.rellenarCampoAreaTexto("Este es un texto muy largo");
        cargaInformacion.seleccionarFechaCalendario("2022-03-19");
        cargaInformacion.rellenarLista(valor: "valor 3");
        cargaInformacion.seleccionMultiple(indicador: "2,3");
        cargaInformacion.seleccionRadioButton(indicador: 2);
        cargaInformacion.clickBtnEnviar();
    }
}
