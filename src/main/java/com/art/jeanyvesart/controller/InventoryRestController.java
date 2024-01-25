//package com.art.jeanyvesart.controller;
//
//import com.art.jeanyvesart.model.Artwork;
//import com.art.jeanyvesart.model.Inventory;
//import com.art.jeanyvesart.repository.ArtworkRepository;
//import com.art.jeanyvesart.repository.CustomerCartRepository;
//import com.art.jeanyvesart.repository.CustomerFavoriteRepository;
//import com.art.jeanyvesart.repository.InventoryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping(path = "/data/artworks", produces = "application/json")
//
//public class InventoryRestController {
//    final
//    InventoryRepository repository;
//    @Autowired
//    ArtworkRepository artworkRepository;
//    final
//    CustomerCartRepository cartArtworkRepository;
//    final
//    CustomerFavoriteRepository favoriteArtworkRepository;
//
//    public InventoryRestController(InventoryRepository repository, CustomerCartRepository cartArtworkRepository, CustomerFavoriteRepository favoriteArtworkRepository) {
//        this.repository = repository;
//        this.cartArtworkRepository = cartArtworkRepository;
//        this.favoriteArtworkRepository = favoriteArtworkRepository;
//    }
//
//    @GetMapping("/{id}")
//    ResponseEntity<Inventory> displayArtworkById(@PathVariable String id) {
//        Optional<Inventory> artwork = repository.findById(id);
//        return artwork.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
//    }
//
//    @GetMapping("/{id}/inventory")
//    ResponseEntity<Integer> displayArtworkInventory(@PathVariable String id) {
//        Optional<Inventory> artwork = repository.findById(id);
//
//
//        return artwork.map(value -> new ResponseEntity<>(value.getQuantity(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
//    }
//
//    @PatchMapping(path = "/{inventoryId}", consumes = "application/json")
//    public ResponseEntity<Inventory> patchInventory(@PathVariable String inventoryId,
//                                                    @RequestBody Inventory patch) {
//
//        Optional<Inventory> inventoryOptional = repository.findById(inventoryId);
//
//        if (inventoryOptional.isPresent()) {
//            Inventory inventory = inventoryOptional.get();
//            if (patch.getQuantity() >= 0) {
//                inventory.setQuantity(patch.getQuantity());
//                if(patch.getQuantity()==0){
//                    inventory.getMyProduct().setPrice("Unavailable");
//                }
//            }
//            if (patch.getMyProduct() != null) {
//                inventory.setMyProduct(patch.getMyProduct());
//            }
//            if (patch.getMetadata() != null) {
//                inventory.setMetadata(patch.getMetadata());
//            }
//            if (patch.getCategory() != null) {
//                inventory.setCategory(patch.getCategory());
//            }
//            repository.save(inventory);
//            return ResponseEntity.ok(inventory);
//
//        }
//        return ResponseEntity.notFound().build();
//
//
//    }
//    @PutMapping(path = "/inventory/{inventoryId}", consumes = "application/json")
//    public Inventory putInventory(@PathVariable String inventoryId,
//                                  @RequestBody Inventory put) {
//        put.setId(inventoryId);
//        return repository.save(put);
//    }
//    @PutMapping(path = "/artwork/{inventoryId}", consumes = "application/json")
//    public Artwork putArtwork(@PathVariable int inventoryId,
//                                  @RequestBody Artwork put) {
//        put.setId(inventoryId);
//        return artworkRepository.save(put);
//    }
//}
