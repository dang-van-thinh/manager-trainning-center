package thinh.manager.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "class_name")
    private String className; // ten lop
    private String description; // mo ta lop
    private String subject; // mon hoc
    @Column(name = "number_student")
    private Integer numberStudent; // so hoc vien trong lop hien tai
    @Column(name = "time_start")
    private LocalDate timeStart;
    @Column(name = "time_end")
    private LocalDate timeEnd;

    @Column(name = "course_id")
    private String course;

    @Column(name = "day_id")
    private Integer dayOfWeek; // ngay hoc trong tuan

    @Column(name = "session_id")
    private Integer session; // ca hoc

    @Column(name = "teacher_id")
    private String teacher; // giao vien day

    @Column(name = "class_room_id")
    private String classRoom; // lop day
}
