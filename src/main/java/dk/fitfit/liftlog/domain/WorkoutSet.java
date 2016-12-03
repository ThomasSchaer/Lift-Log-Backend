package dk.fitfit.liftlog.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class WorkoutSet implements DomainObject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int repetition;
	private double weight;
	private LocalDateTime timestamp = LocalDateTime.now();
	@ManyToOne
	private Exercise exercise;
	@ManyToOne
	private User user;
	@ManyToOne
	private Session session;

	public WorkoutSet() {
		this.timestamp = LocalDateTime.now();
	}

	public boolean olderThan(int minutes) {
		return timestamp.isBefore(LocalDateTime.now().minusMinutes(minutes));
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRepetition() {
		return repetition;
	}

	public void setRepetition(int repetition) {
		this.repetition = repetition;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}
