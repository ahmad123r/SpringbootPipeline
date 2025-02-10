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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="events")
public class Event {

	//Attributes
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=4, max=60, message="Name must be more than 3 characters!")
	private String name;
		
	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date date;
	
	@Size(min=2, max=60, message="Location must be more than 2 characters!")
	private String location;
	
	private String state;
	
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
	//Relationships
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "users_events",
		joinColumns = @JoinColumn(name = "event_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> joinedUsers;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
    @OneToMany(mappedBy="event", fetch = FetchType.LAZY)
	private List<Message> messages;
	
    
	//Constructors
    public Event() {
    }
    
    
    //Getters
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Date getDate() {
		return date;
	}
	public String getLocation() {
		return location;
	}
	public String getState() {
		return state;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public List<User> getJoinedUsers() {
		return joinedUsers;
	}
	public User getUser() {
		return user;
	}
	public List<Message> getMessages() {
		return messages;
	}
	
	
	//Setters
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setJoinedUsers(List<User> joinedUsers) {
		this.joinedUsers = joinedUsers;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	
	// Methods
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}