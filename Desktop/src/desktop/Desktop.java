/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop;

import BLL.LinguaBLL;
import DAL.Lingua;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class Desktop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        List<Lingua> lingua = LinguaBLL.readAll();
        
        lingua.forEach((lng) -> {
            System.out.println("id: " + lng.getIdLingua() + "\nlingua: " + lng.getNome());
        });
        
    }
    
}
