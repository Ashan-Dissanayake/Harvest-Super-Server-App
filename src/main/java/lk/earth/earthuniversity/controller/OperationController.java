package lk.earth.earthuniversity.controller;


import lk.earth.earthuniversity.dao.OperationDao;
import lk.earth.earthuniversity.exception.ResourceExistsException;
import lk.earth.earthuniversity.exception.ResourceNotFoundException;
import lk.earth.earthuniversity.model.entity.Employee;
import lk.earth.earthuniversity.model.entity.Operation;
import lk.earth.earthuniversity.model.response.APISuccessResponse;
import lk.earth.earthuniversity.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/operations")
public class OperationController {

    @Autowired private OperationDao operationDao;

    @GetMapping(produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Operation>>> get(@RequestParam HashMap<String, String> params) {

        List<Operation> operations = this.operationDao.findAll();

        if(params.isEmpty())  return APIResponseBuilder.getResponse(operations, operations.size());

        String moduleid= params.get("moduleid");

        Stream<Operation> pstream = operations.stream();

        if(moduleid!=null) pstream = pstream.filter(p -> p.getModule().getId()==Integer.parseInt(moduleid));

        operations = pstream.collect(Collectors.toList());

        return APIResponseBuilder.getResponse(operations, operations.size());


    }

    @GetMapping(path ="/list/{id}", produces = "application/json")
    public ResponseEntity<APISuccessResponse<List<Operation>>> get(@PathVariable Integer id) {

        List<Operation> operations = this.operationDao.findAllByModule(id);

        operations = operations.stream().map(
                operation -> { Operation op = new Operation();
                    op.setId(operation.getId());
                    op.setName(operation.getName());
                    op.setOpetype(operation.getOpetype());
                    return op; }
        ).collect(Collectors.toList());

        return APIResponseBuilder.getResponse(operations, operations.size());

    }

    @PostMapping
    public ResponseEntity<APISuccessResponse<Operation>> add(@RequestBody Operation operation){

        Operation op = this.operationDao.findByNameAndModuleIdAndOpetypeId(
                operation.getName(),
                operation.getModule().getId(),
                operation.getOpetype().getId()
        );

        if (op!=null)
            throw new ResourceExistsException("Operation already exists");

        Operation savedOperation = operationDao.save(operation);

        return APIResponseBuilder.postResponse(savedOperation,savedOperation.getId());


    }

    @PutMapping
    public ResponseEntity<APISuccessResponse<Operation>> update(@RequestBody Operation operation){

        Operation op = this.operationDao.findByNameAndModuleIdAndOpetypeId(
                operation.getName(),
                operation.getModule().getId(),
                operation.getOpetype().getId()
        );

        if (!Objects.equals(op.getId(), operation.getId()))
            throw new ResourceExistsException("Operation already exists");

        Operation updatedOperation = operationDao.save(operation);

        return APIResponseBuilder.putResponse(updatedOperation,updatedOperation.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APISuccessResponse<Operation>> delete(@PathVariable Integer id){

        Operation op = operationDao.findByMyId(id);

        if(op==null)
            throw new ResourceNotFoundException("Operation doesn't exists");

        operationDao.delete(op);

        return APIResponseBuilder.deleteResponse(id);
    }

}


