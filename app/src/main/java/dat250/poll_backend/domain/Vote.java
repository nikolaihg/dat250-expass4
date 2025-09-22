package dat250.poll_backend.domain;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Instant publishedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voted_by_id")
    private User votedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "votes_on_id")
    private VoteOption votesOn;

    public Vote() {}
    public Vote(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public User getVotedBy() { return votedBy; }
    public void setVotedBy(User votedBy) { this.votedBy = votedBy; }

    public VoteOption getVotesOn() { return votesOn; }
    public void setVotesOn(VoteOption votesOn) { this.votesOn = votesOn; }

    public Instant getPublishedAt() { return publishedAt; }
    public void setPublishedAt(Instant publishedAt) { this.publishedAt = publishedAt; }
}
