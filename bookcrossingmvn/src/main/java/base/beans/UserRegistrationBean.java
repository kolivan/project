package base.beans;

import base.daos.OracleDBRoleDAO;
import base.daos.OracleDBUserDAO;
import base.daos.RoleDAO;
import base.daos.UserDAO;
import base.entities.Role;
import base.entities.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vadim on 26.04.15.
 */
@ManagedBean(name = "userRegistrationBean",eager = true)
@RequestScoped
public class UserRegistrationBean {
    private User user;

    private UserDAO userDAO;

    private RoleDAO roleDAO;

    public UserRegistrationBean() {
        user = new User();
        userDAO = new OracleDBUserDAO();
        roleDAO = new OracleDBRoleDAO();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String registrateUser() {
        User res = userDAO.createUser(user.getName(),user.getPass(),user.getEmail());
        try {
            if(res == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "User with this name already exists"));
            } else {
                Role roleUser = roleDAO.getRoleNameLike("ROLE_USER").get(0);
                Set<Role> set = new HashSet<Role>();
                set.add(roleUser);
                res.setRoles(set);
                userDAO.updateUser(res);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "You've registered successfully!"));
            }
        } catch (Exception e ) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal Error", "Registration error! Try later."));
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        return "registration?faces-redirect=true";
    }
}

    /*
    private User user;

    private UserDAO userDAO;

    public UserRegistrationBean() {
        user = new User();
        userDAO = new OracleDBUserDAO();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String registrateUser() {
        try{
        User res = userDAO.createUser(user.getName(),user.getPass(),user.getEmail());
        try {
            if(res == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "User with this name already exists"));
            } else {
                Role roleUser = new Role("user_role");
                Set<Role> set = new HashSet<Role>();
                set.add(roleUser);
                res.setRoles(set);
                userDAO.updateUser(res);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "You've registered successfully!"));
            }
        } catch (Exception e ) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal Error", "Registration error! Try later."));
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        return "registration?faces-redirect=true";}
        catch (Exception e)
        {
            int i=0;
        }
        return null;
    }


*/