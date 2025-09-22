package dat250.poll_backend.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false, unique=true)
    private String username;

    @Column(nullable=false)
    private String email;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Poll> created = new LinkedHashSet<>();

    @OneToMany(mappedBy = "votedBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Vote> voted = new LinkedHashSet<>();


    public User() {}

    /**
     * Creates a new User object with given username and email.
     * The id of a new user object gets determined by the database.
     */
    public User(String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.created = new LinkedHashSet<>();
        this.voted = new LinkedHashSet<>();
    }

    /**
     * Creates a new Poll object for this user
     * with the given poll question
     * and returns it.
     */
    public Poll createPoll(String question) {
        Poll poll = new Poll();
        poll.setQuestion(question);
        poll.setPublishedAt(Instant.now());
        poll.setCreatedBy(this);
        this.created.add(poll);
        return poll;
    }

    /**
     * Creates a new Vote for a given VoteOption in a Poll
     * and returns the Vote as an object.
     */
    public Vote voteFor(VoteOption option) {
        Vote vote = new Vote();
        vote.setPublishedAt(Instant.now());
        vote.setVotedBy(this);
        vote.setVotesOn(option);
        option.getVotes().add(vote);
        return vote;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<Poll> getCreated() { return created; }
    public void setCreated(Set<Poll> created) { this.created = created; }

    public Set<Vote> getVoted() { return voted; }
    public void setVoted(Set<Vote> voted) { this.voted = voted; }
}