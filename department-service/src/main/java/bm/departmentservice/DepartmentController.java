package bm.departmentservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentEntity> saveDepartment(@RequestBody DepartmentEntity department){
        DepartmentEntity savedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentEntity> getDepartmentById(@PathVariable("id") Long departmentId){
        DepartmentEntity department = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(department);
    }
}
