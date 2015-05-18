package base.beans;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;


@ManagedBean(name = "homeBean",eager = true)
@RequestScoped
public class HomeBean implements Serializable {


    private MenuModel menuModel;


    //input fields

    private String searchInputValue;

    public HomeBean() {
        menuModel = new DefaultMenuModel();




        DefaultMenuItem homeItem = new DefaultMenuItem();
        homeItem.setValue("Home");
        homeItem.setCommand("#{homeBean.showHome}");
        menuModel.addElement(homeItem);

       /* DefaultMenuItem topItem = new DefaultMenuItem();
        topItem.setValue("TOP");
        topItem.setCommand("#{homeBean.showTop}");
        menuModel.addElement(topItem);

        /*if(isAuthorized()) {
            DefaultMenuItem myQuestions = new DefaultMenuItem();
            myQuestions.setValue("My Questions");
            myQuestions.setCommand("#{homeBean.showMyQuestions}");
            menuModel.addElement(myQuestions);
        }

        DefaultMenuItem askItem = new DefaultMenuItem();
        askItem.setValue("Ask");
        askItem.setIcon("ui-icon-plusthick");
        askItem.setCommand("#{homeBean.showAsk}");
        menuModel.addElement(askItem);

        DefaultSubMenu submenu = new DefaultSubMenu();
        submenu.setLabel("Section");


        menuModel.addElement(submenu);


        DefaultMenuItem mostpopularItem = new DefaultMenuItem();
        mostpopularItem.setValue("Most Popular");
        mostpopularItem.setCommand("#{homeBean.showMostPopular}");
        menuModel.addElement(mostpopularItem);

        DefaultMenuItem ratingItem = new DefaultMenuItem();
        ratingItem.setValue("Rating");
        ratingItem.setIcon("ui-icon-star");
        ratingItem.setCommand("#{homeBean.showRating}");
        menuModel.addElement(ratingItem);

//        InputText inputText = new InputText();
//        inputText.setValue("Search");
//        inputText.setStyle("margin-right:10px");
//        menuModel.addElement( inputText);
//
//        MenuButton serchButton = new MenuButton();
//        serchButton.setValue("Search");
//        menuModel.addElement((MenuElement) serchButton);
*/
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public String showHome()  {
        return "index?faces-redirect=true";
    }


    public String showTop() {
        return "top?faces-redirect=true";
    }

    public String showBook() {
        return "book?faces-redirect=true";
    }

    public String showRegistrate() {
        return "registration?faces-redirect=true";
    }

    public String showAsk() {
        if(isAnonymous()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Please, authorize to ask question."));
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "index?faces-redirect=true";
        } else {
            return "ask?faces-redirect=true";
        }
    }

    public String searchData() {
        System.out.println("searchData." + searchInputValue);
        return null;
    }
    public String login() {
        System.out.println("login" + searchInputValue);
        return null;
    }

    public boolean hasUserRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for( GrantedAuthority authority :auth.getAuthorities()) {
            if(authority.getAuthority().equals("ROLE_USER") ) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAdminRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for( GrantedAuthority authority :auth.getAuthorities()) {
            if(authority.getAuthority().equals("ROLE_ADMIN") ) {
                return true;
            }
        }
        return false;
    }

    public boolean isAnonymous() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for( GrantedAuthority authority :auth.getAuthorities()) {
            if(authority.getAuthority().equals("ROLE_ANONYMOUS")) {
                return true;
            }
        }
        return false;
    }

    public boolean isAuthorized() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for( GrantedAuthority authority :auth.getAuthorities()) {
            if(!authority.getAuthority().equals("ROLE_ANONYMOUS")) {
                return true;
            }
        }
        return false;
    }

    public String getSearchInputValue() {
        return searchInputValue;
    }

    public void setSearchInputValue(String searchInputValue) {
        this.searchInputValue = searchInputValue;
    }
}