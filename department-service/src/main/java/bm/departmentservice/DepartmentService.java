package bm.departmentservice;

import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public DepartmentEntity saveDepartment(DepartmentEntity department) {
        return departmentRepository.save(department);
    }

    public DepartmentEntity getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).get();
    }
}
