package mbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import entities.Tache;
import entities.User;
import implservices.GroupService;
import implservices.UserService;

@ManagedBean

public class UserBean {

	private int Id;
	private String Nom;
	private String Prenom;
	private String Role;
	private String Login;
	private String Mdp;
	private User user;
	private  boolean loggedIn;
	private int Idgroup;
	private List<Tache> taches;
	
	
	
	
	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}
	@EJB
	UserService userservice; 
	@EJB
	GroupService groupservice; 
	
	
	public List<Tache> getTachesbyuser(int id)
	{
		taches = userservice.getTachesByUser(id) ;		
		return taches ;		
	}	
	public String affUserGroup(){
		String navigateTo="allusers?faces-redirect=true";
	    userservice.affecterUserGroup(Id, Idgroup);;
		return navigateTo;
	}	
	public List<User> getAllUsers() {
		return userservice.getAllUsers();
	}	
	public String addMember()
	{
		String navigateTo="Login?faces-redirect=true";
	userservice.ajouteruser(new User(Nom, Prenom,"M", Login, Mdp));	
	return navigateTo;
	}	
	public String doLogout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Login?faces-redirect=true";
	}		
	public String doLogin(){
		String navigateTo="null";
		user=userservice.getLogin(Login, Mdp);
	    if (user !=null){
	    	Id=user.getId();
	    	loggedIn=true;	    	
	    	if (user.getRole().equals("M")){
	    		Id=user.getId();
	    		navigateTo="/Tacheplan?faces-redirect=true";
	    		}
	    		if (user.getRole().equals("A")){
	    			Id=user.getId();
	    		navigateTo="/allusers?faces-redirect=true";
	    		}	    	
	    }
		else {
			FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("bad credentials"));
		}
	return navigateTo;
	}	
	public String ajouteruser(){
		String navigateTo="Login?faces-redirect=true";			
		userservice.ajouteruser(new User( Nom, Prenom, "A", Login, Mdp));
		return navigateTo;
	}	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public int getIdgroup() {
		return Idgroup;
	}
	public void setIdgroup(int idgroup) {
		Idgroup = idgroup;
	}
	public int getId() {
		return Id;
	}		
	public void setId(int id) {
		Id = id;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	public String getMdp() {
		return Mdp;
	}
	public void setMdp(String mdp) {
		Mdp = mdp;
	}	
}