package com.codingdojo.danaaltier.events.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {

	//Attributes
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="First Name must not be blank!")
	@Size(min=2, max=60, message="First Name must be more than 2 characters!")
	private String firstName;
	
	@NotBlank(message="Last Name must not be blank!")
	@Size(min=2, max=60, message="Last Name must be more than 2 characters!")
	private String lastName;
	
	@NotBlank(message="First Name must not be blank!")
	@Pattern(regexp="^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+.[a-zA-Z0-9.-]+$", message="Invalid email pattern")
	@Email(message="Email must be valid")
	private String email;
	
	@NotBlank(message="Location must not be blank!")
	@Size(min=2, max=60, message="Location must be more than 2 characters!")
	private String location;
	
	private String state;
	
	@NotBlank(message="Password must not be blank!")
	@Size(min=8, message="Password must be greater than 8 characters!")
	private String password;

	@Transient
	@NotBlank(message="Confirm Password must not be blank!")
	private String confirmPassword;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	
	//Relationships
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    	name = "users_events",
    	joinColumns = @JoinColumn(name = "user_id"),
    	inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> joinedevents;
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Event> events;
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Message> messages;
    
    
    // Constructor
    public User() {
    }
    
    public User(String firstName, String lastName, String email, String location, String state, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.location = location;
		this.state = state;
		this.password = password;
	}


    // Getters
    public Long getId() {
		return id;
	}
    public String getFirstName() {
		return firstName;
	}
    public String getLastName() {
		return lastName;
	}
    public String getEmail() {
    	return email;
    }
    public String getLocation() {
    	return location;
    }
    public String getState() {
    	return state;
    }
    public String getPassword() {
    	return password;
    }
    public String getConfirmPassword() {
    	return confirmPassword;
    }
    public Date getCreatedAt() {
    	return createdAt;
    }
    public Date getUpdatedAt() {
    	return updatedAt;
    }
    public List<Event> getJoinedevents() {
    	return joinedevents;
    }
    public List<Event> getEvents() {
    	return events;
    }
    public List<Message> getMessages() {
    	return messages;
    }
    
    
    // Setters
    public void setId(Long id) {
    	this.id = id;
    }
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    public void setLocation(String location) {
    	this.location = location;
    }
    public void setState(String state) {
    	this.state = state;
    }
    public void setPassword(String password) {
    	this.password = password;
    }
    public void setConfirmPassword(String confirmPassword) {
    	this.confirmPassword = confirmPassword;
    }
    public void setCreatedAt(Date createdAt) {
    	this.createdAt = createdAt;
    }
    public void setUpdatedAt(Date updatedAt) {
    	this.updatedAt = updatedAt;
    }
    public void setJoinedevents(List<Event> joinedevents) {
    	this.joinedevents = joinedevents;
    }
    public void setEvents(List<Event> events) {
    	this.events = events;
    }
    public void setMessages(List<Message> messages) {
    	this.messages = messages;
    }
    
    
    // Methods
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
