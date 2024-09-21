package thinh.manager.backend.model.dto.enrollment;

import lombok.Data;

@Data
public class UpdateEnrollment {
    private String oldClassesId;
    private String newClassesId;
}
