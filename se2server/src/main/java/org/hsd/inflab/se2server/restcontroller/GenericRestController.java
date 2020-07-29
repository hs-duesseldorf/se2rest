package org.hsd.inflab.se2server.restcontroller;

import java.util.List;

import org.hsd.inflab.se2server.entity.AbstractEntity;
import org.hsd.inflab.se2server.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class GenericRestController<E extends AbstractEntity> {

	protected abstract E updateEntity(E e, E newE);

    @Autowired
    protected GenericRepository<E> repository;

	@GetMapping
	public List<E> list() {
		return repository.findAll();
	}
	
	@PostMapping
	public E create(@RequestBody E entity) {
		return repository.save(entity);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable(value = "id") long id) {
		repository.deleteById(id);
	}
	
	@GetMapping("{id}")
	public E get(@PathVariable(value = "id") long id) {
		return repository.getOne(id);
	}

    @PutMapping("{id}")
    public E update(@PathVariable long id, @RequestBody E newE) {

        return repository.findById(id).map(e -> {
            e = updateEntity(e, newE);

			return repository.save(e);

        }).orElseGet(() -> {
            newE.setId(id);
            return repository.save(newE);
        });
        
    }
}
