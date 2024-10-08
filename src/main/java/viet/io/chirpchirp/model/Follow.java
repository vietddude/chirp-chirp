package viet.io.chirpchirp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "follows")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)  // Tránh cảnh báo callSuper
public class Follow extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;  // Sử dụng UUID thay vì String cho khóa chính

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;  // Người theo dõi

    @ManyToOne
    @JoinColumn(name = "followee_id", nullable = false)
    private User followee;  // Người được theo dõi
}
