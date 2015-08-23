/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author ajain5
 */
@FacesValidator(value = "userIdValidator")
public class UserIdValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String userid = (String) value;

        // Obtain the component and submitted value in the database
        UIInput confirmComponent = (UIInput) component.getAttributes().get("userID");
        //String confirm = (String) confirmComponent.getSubmittedValue();
        
        
        
        if (userid == null || userid.isEmpty()) {
            return; // Let required="true" do its job.
        }
        ProfileDAOImpl imp = new ProfileDAOImpl();
        
        if (imp.checkUserExist(userid)) {
            //confirmComponent.setValid(false); // So that it's marked invalid.
            throw new ValidatorException(new FacesMessage("User name is already taken!! "));
        }
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
