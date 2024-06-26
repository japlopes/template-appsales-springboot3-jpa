package com.modelmono.appwholesale.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.modelmono.appwholesale.entities.Category;
import com.modelmono.appwholesale.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> list = categoryService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{idCateg}")
	public ResponseEntity<Category> findById(@PathVariable Long idCateg){
		Category obj = categoryService.findById(idCateg);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@PostMapping
	public ResponseEntity<Category> insert(@RequestBody Category obj){
		obj = categoryService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCateg}")
				.buildAndExpand(obj.getIdCateg()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{idCateg}")
	public ResponseEntity<Void> delete(@PathVariable Long idCateg){
		categoryService.delete(idCateg);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{idCateg}")
	public ResponseEntity<Category> update(@PathVariable Long idCateg, @RequestBody Category obj){
		obj = categoryService.update(idCateg, obj);
		return ResponseEntity.ok().body(obj);
	}
	

}
